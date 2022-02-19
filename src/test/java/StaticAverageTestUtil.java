/*
 *    Copyright 2022 ***REMOVED*** ***REMOVED***
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StaticAverageTestUtil {
    private final static List<Integer> VALUES = new ArrayList<>();
    public static int REPORT_INTERVAL = 10;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(StaticAverageTestUtil::report));
    }
    public static synchronized void push(final int i) {
        VALUES.add(i);
        if (VALUES.size() % REPORT_INTERVAL == 0) {
            report();
        }
    }

    public static synchronized void report() {
        final int size = VALUES.size();
        BigInteger SUM = new BigInteger("0");
        for (int i = 0; i < size; i++) {
            SUM = SUM.add(new BigInteger(String.valueOf(VALUES.get(i))));
        }
        System.out.println("average: " + SUM.divide(new BigInteger(String.valueOf(size))));
    }
}
