package de.sanj0.kopfkino.physics;

import de.sanj0.kopfkino.Directions;
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Time;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.collision.AABBHitbox;
import de.sanj0.kopfkino.collision.CircleHitbox;
import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.scene.Scene;

import java.util.List;

/**
 * A world that houses physics entities, more specifically {@link Rigidbody}s.
 */
public class World {

    public static final Vector2f DEFAULT_G = new Vector2f(0, 0.0981f);
    public static final float DEFAULT_FRICTION = .01f;
    private final Scene environment;
    private Vector2f g;
    /**
     * 0 means no friction, 1 means no motion
     */
    private float friction;
    /**
     * Velocity that falls below this threshold will not be applied
     */
    private float stoppingThreshold = .03f;

    public World(final Scene environment, final Vector2f g, final float friction) {
        this.environment = environment;
        this.g = g;
        this.friction = friction;
    }

    public void update() {
        final List<Entity> entities = environment.getEntities();
        final int numEntities = entities.size();

        final int dt = Time.getFixedUpdateRate();
        for (int i = 0; i < numEntities; i++) {
            final Entity e = entities.get(i);
            final Rigidbody body = e.getRigidbody();
            final Vector2f v = body.getV();
            v.add(body.getF().divBy(body.getM()).times(dt));
            body.setF(Vector2f.zero());
            v.add(g.times(dt));
            v.subtract(v.times(v).times(new Vector2f(Math.signum(v.getX()), Math.signum(v.getY()))).times(friction));
            if (Math.abs(v.getX()) > stoppingThreshold) {
                if ((v.getX() > 0 && !e.getBlockedDirections().contains(Directions.Direction.RIGHT))
                        || (v.getX() < 0 && !e.getBlockedDirections().contains(Directions.Direction.LEFT))) {
                    e.setX(e.getX() + v.getX());
                }
            }
            if (Math.abs(v.getY()) > stoppingThreshold) {
                if ((v.getY() > 0 && !e.getBlockedDirections().contains(Directions.Direction.DOWN))
                        || (v.getY() < 0 && !e.getBlockedDirections().contains(Directions.Direction.UP))) {
                    e.setY(e.getY() + v.getY());
                }
            }
        }
    }

    public void handleCollision(final Entity a, final Collision collision) {
        final Entity b = collision.getPartner();
        final Rigidbody bodyA = a.getRigidbody();
        final Rigidbody bodyB = b.getRigidbody();
        // circle to x
        if (a.getHitbox() instanceof CircleHitbox) {
            // circle to circle
            if (b.getHitbox() instanceof CircleHitbox) {
                circle_to_circle(bodyA, bodyB);
                // circle to aabb
            } else if (b.getHitbox() instanceof AABBHitbox) {
                circle_to_aabb(bodyA, bodyB);
            }
        } else if (a.getHitbox() instanceof AABBHitbox) {
            if (b.getHitbox() instanceof CircleHitbox) {
                circle_to_aabb(bodyB, bodyA);
            } else if (b.getHitbox() instanceof AABBHitbox) {
                aabb_to_aabb(a, b);
            }
        }
    }

    private void circle_to_circle(final Rigidbody a, final Rigidbody b) {
        a.getV().times(Vector2f.num(-b.getBounciness()));
        a.getF().times(0);
        b.getV().times(Vector2f.num(-a.getBounciness()));
        b.getF().times(0);
    }

    private void circle_to_aabb(final Rigidbody circle, final Rigidbody aabb) {

    }

    private void aabb_to_aabb(final Entity a, final Entity b) {
        a.getRigidbody().setF(Vector2f.zero());
        b.getRigidbody().setF(Vector2f.zero());
        final Vector2f collisionNormalA = a.getHitbox().getBoundingBox().collisionNormal(b.getBoundingBox());
        if (collisionNormalA.getX() != 0) {
            a.getRigidbody().getV().setX(-a.getRigidbody().getV().getX() * b.getRigidbody().getBounciness());
            b.getRigidbody().getV().setX(-b.getRigidbody().getV().getX() * a.getRigidbody().getBounciness());
        } else {
            a.getRigidbody().getV().setY(-a.getRigidbody().getV().getY() * b.getRigidbody().getBounciness());
            b.getRigidbody().getV().setY(-b.getRigidbody().getV().getY() * a.getRigidbody().getBounciness());
        }
    }

    /**
     * Gets {@link #g}.
     *
     * @return the value of {@link #g}
     */
    public Vector2f getG() {
        return g;
    }

    /**
     * Sets {@link #g}.
     *
     * @param g the new value of {@link #g}
     */
    public void setG(final Vector2f g) {
        this.g = g;
    }

    /**
     * Gets {@link #friction}.
     *
     * @return the value of {@link #friction}
     */
    public float getFriction() {
        return friction;
    }

    /**
     * Sets {@link #friction}.
     *
     * @param friction the new value of {@link #friction}
     */
    public void setFriction(final float friction) {
        this.friction = friction;
    }
}
