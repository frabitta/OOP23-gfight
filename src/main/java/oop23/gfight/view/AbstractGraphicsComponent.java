package oop23.gfight.view;

import oop23.gfight.common.Position2D;
import oop23.gfight.common.Rotation2D;

public class AbstractGraphicsComponent implements GraphicsComponent {

    private EngineColor color;
    private Position2D pos;
    private Rotation2D rot;

    AbstractGraphicsComponent(EngineColor color, Position2D pos, Rotation2D rot) {
        this.color = color;
        this.pos = pos;
        this.rot = rot;
    }

    @Override
    public EngineColor getColor() {
        return this.color;
    }

    @Override
    public Position2D getPosition() {
        return this.pos;
    }

    @Override
    public Rotation2D getRotation() {
        return this.rot;
    }

    public void setColor(EngineColor color) {
        this.color = color;
    }

    public void setPos(Position2D pos) {
        this.pos = pos;
    }

    public void setRot(Rotation2D rot) {
        this.rot = rot;
    }

}
