package gfight.world.entity.impl;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import gfight.common.api.Position2D;
import gfight.common.api.Vect;
import gfight.common.impl.VectorImpl;
import gfight.engine.graphics.api.GraphicsComponent;
import gfight.world.weapon.api.Projectile;
import gfight.world.weapon.api.Weapon;
import gfight.world.collision.api.CollisionCommand;
import gfight.world.collision.impl.PushAwayCommand;
import gfight.world.collision.impl.SlideCommand;
import gfight.world.entity.api.Character;
import gfight.world.entity.api.GameEntity;
import gfight.world.entity.api.MovingEntity;
import gfight.world.hitbox.api.Hitboxes;
import gfight.world.hitbox.impl.HitboxesImpl;

/**
 * Class that represents the concept of main character (Player, Enemies).
 */
public final class CharacterImpl extends AbstractActiveEntity implements Character {
    private Optional<Weapon> weapon = Optional.empty();
    private Vect pointingDirection = new VectorImpl(getPosition(), getPosition2Ds().get(0));
    private CharacterType role;

    /**
     * Constructor of abstract character.
     * 
     * @param vertexes          of the shape
     * @param position          the centre of the character
     * @param graphicsComponent the color of the character
     * @param health            life point of the character
     * @param role              the type of the character (ENEMY or PLAYER)
     */
    public CharacterImpl(final List<Position2D> vertexes, final Position2D position,
            final GraphicsComponent graphicsComponent, final int health, final CharacterType role) {
        super(vertexes, position, graphicsComponent, health);
        this.role = role;
    }

    @Override
    public void pointTo(final Position2D target) {
        final Hitboxes rotation = new HitboxesImpl();
        setCoordinates(rotation.rotateTo(getPosition2Ds(), this.pointingDirection, getPosition(), target));
        this.pointingDirection = new VectorImpl(getPosition(), getPosition2Ds().get(2));
    }

    @Override
    public void setWeapon(final Weapon weapon) {
        this.weapon = Optional.ofNullable(weapon);
        if (this.weapon.isPresent()) {
            this.weapon.get().setParentEntity(this);
        }
    }

    @Override
    public void makeDamage() {
        if (this.weapon.isPresent()) {
            this.weapon.get().shoot();
        }
    }

    @Override
    public Vect getPointedDirection() {
        return this.pointingDirection;
    }

    @Override
    public void setPointingDirection(final Vect pointingDirection) {
        this.pointingDirection = pointingDirection;
    }

    @Override
    public CharacterType getType() {
        return this.role;
    }

    @Override
    protected void applyCollisions(final Set<? extends GameEntity> gameobjects) {
        getAllCollided(gameobjects).stream().forEach(el -> {
            if (el instanceof Character) {
                CollisionCommand<Character, Character> coll = new PushAwayCommand<>(this,
                        (Character) el);
                coll.execute();
            } else if (el instanceof Projectile) {
            } else if (el instanceof GameEntity) {
                CollisionCommand<MovingEntity, GameEntity> coll = new SlideCommand<>(this, el);
                coll.execute();
            }
        });
    }

}
