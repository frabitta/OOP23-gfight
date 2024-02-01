package gfight.world.api;

public interface Director {
    /**
     * Constructor of chest enitities
     * @param builder
     */
    void chestContructor(EntityBuilder builder);

    /**
     * Constructor of enemy entities
     * @param builder
     */
    void enemyContructor(EntityBuilder builder);

    /**
     * Constructor of player entities
     * @param builder
     */
    void playerContructor(EntityBuilder builder);
}
