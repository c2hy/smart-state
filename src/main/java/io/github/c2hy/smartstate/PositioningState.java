package io.github.c2hy.smartstate;

public class PositioningState {
    private final Position position;
    private final State state;

    PositioningState(Position position, State state) {
        this.position = position;
        this.state = state;
    }

    int index() {
        return this.position.index;
    }

    byte byteState() {
        return this.state.byteState;
    }

    public static PositioningState of(int position, boolean state) {
        return new PositioningState(Position.of(position), State.of(state));
    }

    public int position() {
        return this.position.position;
    }

    public boolean state() {
        return this.state.state;
    }

    @Override
    public String toString() {
        return "PositioningState{" +
                "position=" + position.position +
                ", state=" + state.state +
                '}';
    }
}
