package de.sanj0.kopfkino.physics;

import de.sanj0.kopfkino.Directions;
import de.sanj0.kopfkino.Entity;
import de.sanj0.kopfkino.Time;
import de.sanj0.kopfkino.Vector2f;
import de.sanj0.kopfkino.collision.AABBHitbox;
import de.sanj0.kopfkino.collision.CircleHitbox;
import de.sanj0.kopfkino.collision.Collision;
import de.sanj0.kopfkino.Scene;

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
     * If the velocity of an entity squared falls below this value, it is reset
     * to 0|0.
     */
    private float stoppingThreshold = .03f;

    public World(final Scene environment, final Vector2f g, final float friction) {
        this.environment = environment;
        this.g = g;
        this.friction = friction;
    }

    public void update() {
        final int dt = Time.getFixedUpdateRate();
        environment.forEach(e -> {
            if (!e.isAffectedByPhysics()) return;
            final Rigidbody body = e.getRigidbody();
            final Vector2f v = body.getV();
            v.add(body.getF().divBy(body.getM()).times(dt));
            body.setF(Vector2f.zero());
            v.add(g.times(dt));
            v.subtract(v.times(v).times(new Vector2f(Math.signum(v.getX()), Math.signum(v.getY()))).times(friction));
            if (v.magnitudeSquared() < stoppingThreshold) {
                v.set(Vector2f.zero());
            } else {
                // only move in directions that aren't blocked.
                if ((v.getX() > 0 && !e.getBlockedDirections().contains(Directions.Direction.RIGHT))
                        || (v.getX() < 0 && !e.getBlockedDirections().contains(Directions.Direction.LEFT))) {
                    e.setX(e.getX() + v.getX());
                }
                if ((v.getY() > 0 && !e.getBlockedDirections().contains(Directions.Direction.DOWN))
                        || (v.getY() < 0 && !e.getBlockedDirections().contains(Directions.Direction.UP))) {
                    e.setY(e.getY() + v.getY());
                }
            }
        });
    }

    public void handleCollision(final Entity a, final Collision collision) {
        final Entity b = collision.getPartner();
        // circle to x
        if (a.getHitbox() instanceof CircleHitbox) {
            // circle to circle
            if (b.getHitbox() instanceof CircleHitbox) {
                circle_to_circle(a, b);
                // circle to aabb
            } else if (b.getHitbox() instanceof AABBHitbox) {
                circle_to_aabb(a, b);
            }
        } else if (a.getHitbox() instanceof AABBHitbox) {
            if (b.getHitbox() instanceof CircleHitbox) {
                circle_to_aabb(b, a);
            } else if (b.getHitbox() instanceof AABBHitbox) {
                aabb_to_aabb(a, b);
            }
        }
    }

    private void circle_to_circle(final Entity a, final Entity b) {
        /*
        * final rigidbody ab = a.getRigidbody();
        * final rigidbody bb = b.getRigidbody();
        * ab.getV().times(Vector2f.num(-b.getRigidbody().getBounciness()));
        * ab.getF().times(0);
        * bb.getV().times(Vector2f.num(-a.getRigidbody().getBounciness()));
        * bb.getF().times(0);
        */
        // for now, just assume aabb_to_aabb
        aabb_to_aabb(a, b);
    }

    private void circle_to_aabb(final Entity circle, final Entity aabb) {
        // 1. Find the face the circle collides with
        // 2. ...?
        // For now tho, just assume aabb_to_aabb
        aabb_to_aabb(circle, aabb);
    }

    private void aabb_to_aabb(final Entity a, final Entity b) {
        final Directions.Direction d = a.getHitbox().getBoundingBox().collisionDirection(b.getHitbox().getBoundingBox());
        if (d == null) return;
        final Rigidbody bodyA = a.getRigidbody();
        final Rigidbody bodyB = b.getRigidbody();
        switch (d) {
            case UP: 
                if (bodyA.getF().getY() < 0) bodyA.getF().setY(0);
                if (bodyB.getF().getY() > 0) bodyB.getF().setY(0);
                break;
            case DOWN:
                if (bodyA.getF().getY() > 0) bodyA.getF().setY(0);
                if (bodyB.getF().getY() < 0) bodyB.getF().setY(0);
                break;
            case RIGHT:
                if (bodyA.getF().getX() < 0) bodyA.getF().setX(0);
                if (bodyB.getF().getX() > 0) bodyB.getF().setX(0);
                break;
            case LEFT:
                if (bodyA.getF().getX() > 0) bodyA.getF().setX(0);
                if (bodyB.getF().getX() < 0) bodyB.getF().setX(0);
                break;
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

    /**
     * Gets {@link #environment}.
     *
     * @return the value of {@link #environment}
     */
    public Scene getEnvironment() {
        return environment;
    }

    /**
     * Gets {@link #stoppingThreshold}.
     *
     * @return the value of {@link #stoppingThreshold}
     */
    public float getStoppingThreshold() {
        return stoppingThreshold;
    }

    /**
     * Sets {@link #stoppingThreshold}.
     *
     * @param stoppingThreshold the new value of {@link #stoppingThreshold}
     */
    public void setStoppingThreshold(final float stoppingThreshold) {
        this.stoppingThreshold = stoppingThreshold;
    }
}
