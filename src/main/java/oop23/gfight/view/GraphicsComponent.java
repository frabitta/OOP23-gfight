package oop23.gfight.view;

import oop23.gfight.common.Position2D;
import oop23.gfight.common.Rotation2D;

public interface GraphicsComponent {
    
    enum EngineColor {
        Blue, Red, Black, Yellow
    }
    
    EngineColor getColor();

    Position2D getPosition();

    Rotation2D getRotation();

}
