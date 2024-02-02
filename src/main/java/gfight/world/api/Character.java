package gfight.world.api;

public interface Character extends ActiveEntity{
    /**
     * Perform the action of rotating.
     */
    void rotate(double theta);

    /**
     * Perform the action of shooting.
     */
    void shoot();

}
