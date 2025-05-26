package kopfkino;

/**
 * A simple utility class to modulate the fixed update loop.
 * The fixed update loop is modulated up to a target frequency
 * (that's actually 1/frequency!) that's either in milliseconds
 * or ticks. {@link #poll()} is used to advance the counter by one
 * and return if it is time yet.
 * Example usage:
 * <pre>
 * {@code
 * class EnemySpawner extends Entity {
 *     // We want to spawn an enemy ever 500 millis
 *     private TickModulator spawnModulator = new TickModulator(500, TickModulator.Unit.Millis);
 *     // ...
 *     @Override
 *     public void fixedUpdate() {
 *         if (spawnModulator.poll()) {
 *             spawnEnemy();
 *         }
 *     }
 * }
 * }
 * </pre>
 * Both the target frequency and the unit can be changed in-flight.
 */
public class TickModulator {
    private int targetFreq;
    private Unit unit;
    private int counter = 0;

    public TickModulator(int targetFreq, Unit unit) {
        this.targetFreq = targetFreq;
        this.unit = unit;
    }

    /**
     * Advanced the tick counter by one and returns if it's time yet.
     * @return if it's time yet
     */
    public boolean poll() {
        int target = requiredTicks();
        if (counter >= target) {
            counter = 0;
            return true;
        } else {
            counter += 1;
            return false;
        }
    }

    private int requiredTicks() {
        return unit == Unit.Ticks ? targetFreq : (int) (targetFreq / Game.getInstance().getFixedUpdatePeriod());
    }

    public void reset() {
        counter = 0;
    }

    public float getProgress() {
        return counter / (float) requiredTicks();
    }

    public int getTargetFreq() {
        return targetFreq;
    }

    public void setTargetFreq(int targetFreq) {
        this.targetFreq = targetFreq;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public enum Unit {
        Millis,
        Ticks
    }
}
