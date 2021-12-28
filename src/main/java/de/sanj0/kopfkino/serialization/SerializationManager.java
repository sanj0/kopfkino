/*
 *    Copyright 2021 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.sanj0.kopfkino.serialization;

import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.edgelord.sanjo.SanjoFile;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class SerializationManager {
    private static final Map<String, List<Field>> serializedFields = new HashMap<>();

    public static void deserializeStatic(final File sourceFile) throws IOException, IllegalAccessException {
        final SanjoFile file = new SanjoFile(sourceFile.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }
        serializedFields.clear();
        final SJClass dataRoot = file.parser().parse();
        final Reflections reflections = new Reflections(new ConfigurationBuilder().
                setUrls(ClasspathHelper.forJavaClassPath()).setScanners(
                        new SubTypesScanner(), new TypeAnnotationsScanner()));
        final Set<Class<?>> serializables = reflections.getTypesAnnotatedWith(Serializable.class);
        for (final Class<?> clazz : serializables) {
            String id = clazz.getAnnotation(Serializable.class).id();
            if (id.isEmpty()) id = clazz.getName();
            final Optional<SJClass> classDataOpt = dataRoot.getChild(id);
            final SJClass classData;
            if (classDataOpt.isPresent()) {
                classData = classDataOpt.get();
            } else {
                System.out.println("no serialized data present for " + clazz.getName() + "@id=" + id);
                classData = new SJClass("");
            }
            final List<Field> serializedFieldsList = new ArrayList<>();
            for (final Field field : clazz.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers()) && field.isAnnotationPresent(Serialized.class)) {
                    serializedFieldsList.add(field);
                    String key = field.getAnnotation(Serialized.class).key();
                    if (key.isEmpty()) key = field.getName();
                    final Optional<SJValue> fieldDataOpt = classData.getValue(key);
                    if (fieldDataOpt.isPresent()) {
                        field.set(null, fieldDataOpt.get().getValue());
                    } else {
                        System.out.println("no serialized data present for " + field.getName() + "@key=" + key);
                    }
                }
            }
            serializedFields.put(id, serializedFieldsList);
        }
    }

    public static void serializeStatic(final File file) throws IOException, IllegalAccessException {
        if (serializedFields.isEmpty()) {
            System.out.println("no fields indexed to serialize!");
            return;
        }
        final SJClass dataRoot = SJClass.defaultClass();
        for (final Map.Entry<String, List<Field>> clazz : serializedFields.entrySet()) {
            final SJClass clazzData = dataRoot.addChild(clazz.getKey());
            for (final Field f : clazz.getValue()) {
                String key = f.getAnnotation(Serialized.class).key();
                if (key.isEmpty()) key = f.getName();
                clazzData.addValue(key, f.get(null));
            }
        }
        Files.write(file.toPath(), dataRoot.write().getBytes(StandardCharsets.UTF_8));
    }
}
