package gfight.world.api;

public interface Character extends ActiveEntity {
    /**
     * Perform the action of rotating.
     * 
     * @param theta the angle of rotation
     */
    void rotate(double theta);

    /**
     * Perform the action of shooting.
     */
    void shoot();

}
