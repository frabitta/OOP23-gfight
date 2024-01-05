package oop23.gfight.engine;

public class EngineImpl implements Engine{

    @Override
    public void initialize() {
        System.out.println("Initialized");
    }

    @Override
    public void mainLoop() {
        System.out.println("Hello world");
    }

}
