package oop23.gfight.view;

import oop23.gfight.common.Position2D;
import oop23.gfight.common.Rotation2D;

public class TextGraphicsComponent extends AbstractGraphicsComponent {

    private String text;

    TextGraphicsComponent(EngineColor color, Position2D pos, Rotation2D rot, String text) {
        super(color, pos, rot);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
