package io.github.c2hy.smartstate;

class State {
    public final boolean state;
    final byte byteState;
    final static byte TRUE = 1;
    final static byte FALSE = 0;

    public State(boolean state) {
        this.state = state;
        this.byteState = state ? TRUE : FALSE;
    }

    public static State of(boolean state) {
        return new State(state);
    }
}
