package gfight.engine.input.api;

/**
 * Input event to describe events from the keyboard.
 */
public interface InputEventValue extends InputEvent {

    enum Value {
        UP('W'),
        DOWN('S'),
        LEFT('A'),
        RIGHT('D'),
        RESET(-1);

        int key;
        Value(int key) {
            this.key = key;
        }
        public int getKey() {
            return this.key;
        }
    }

    /**
     * Returns the key pressed on the keyboard.
     * @return char pressed
     */
    Value getValue();
}
