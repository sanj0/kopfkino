package de.sanj0.kopfkino.physics;

import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.scene.Scene;

import java.util.List;

/**
 * A world that houses pyhsics entities, more specifically
 * {@link Rigidbody}s.
 */
public class World {
    private final Scene environment;
    private Vector2f gravity;
    private float friction;

    public World(Scene environment, Vector2f gravity, float friction) {
        this.environment = environment;
        this.gravity = gravity;
        this.friction = friction;
    }

    public void update() {
        final List<Entity> entities = environment.getEntities();
        final int numEntities = entities.size();

        for (int i = 0; i < numEntities; i++) {
            final Entity e = entities.get(i);
        }
    }
}
