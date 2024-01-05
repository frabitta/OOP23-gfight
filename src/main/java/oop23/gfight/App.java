package oop23.gfight;

import oop23.gfight.engine.Engine;
import oop23.gfight.engine.EngineImpl;

public class App {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.initialize();
        engine.mainLoop();
    }
}