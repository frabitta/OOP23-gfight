package gfight.engine.input.api;

/**
 * Input event to describe events from the keyboard.
 */
public interface InputEventValue extends InputEvent {

    /**
     * Values that can be received by the world with the corrisponding key binding.
     */
    enum Value {
        /**
         * Player up.
         */
        UP('W'),
        /**
         * Player down.
         */
        DOWN('S'),
        /**
         * Player left.
         */
        LEFT('A'),
        /**
         * Player right.
         */
        RIGHT('D'),
        /**
         * reset inputs received.
         */
        RESET(-1),
        /**
         * pause game.
         */
        PAUSE(27);

        private int key;
        Value(final int key) {
            this.key = key;
        }
        /**
         * Get the binded key from the corrisponding Value.
         * @return ascii code of the key
         */
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
