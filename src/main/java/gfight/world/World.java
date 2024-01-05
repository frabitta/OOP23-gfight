package gfight.world;

import java.util.List;

import gfight.view.GraphicsComponent;

public interface World {
    void instantiate();

    void update(long deltaTime);

    List<GraphicsComponent> getGraphicsComponents();
}
