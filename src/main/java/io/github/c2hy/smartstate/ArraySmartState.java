package io.github.c2hy.smartstate;

import static io.github.c2hy.smartstate.Position.validatePositionArrayLength;
import static io.github.c2hy.smartstate.State.FALSE;
import static io.github.c2hy.smartstate.State.TRUE;

class ArraySmartState implements SmartState {
    public final int number;
    public final byte[] bytes;

    ArraySmartState(Integer number) {
        this.number = number;
        if (number == 0) {
            this.bytes = ArraySmartStates.zero32ByteArray();
        } else {
            this.bytes = ArraySmartStates.to32ByteArray(number);
        }
    }

    ArraySmartState(byte[] bytes) {
        this.bytes = bytes;
        this.number = ArraySmartStates.toNumber(bytes);
    }

    @Override
    public int number() {
        return this.number;
    }

    @Override
    public boolean isTrue(int position) {
        return this.bytes[Position.of(position).index] == TRUE;
    }

    @Override
    public boolean isFalse(int position) {
        return this.bytes[Position.of(position).index] == FALSE;
    }

    @Override
    public boolean expects(PositioningState... states) {
        validatePositionArrayLength(states.length);
        for (PositioningState expectedState : states) {
            if (this.bytes[expectedState.index()] != expectedState.byteState()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public SmartState setTrue(int position) {
        byte[] newBytes = this.bytes.clone();
        newBytes[Position.of(position).index] = TRUE;
        return new ArraySmartState(newBytes);
    }

    @Override
    public SmartState setFalse(int position) {
        byte[] newBytes = this.bytes.clone();
        newBytes[Position.of(position).index] = FALSE;
        return new ArraySmartState(newBytes);
    }

    @Override
    public SmartState setState(PositioningState... states) {
        byte[] newBytes = this.bytes.clone();
        for (PositioningState state : states) {
            newBytes[state.index()] = state.byteState();
        }
        return new ArraySmartState(newBytes);
    }

    @Override
    public String toString() {
        return ArraySmartStates.toBinaryString(this.bytes);
    }
}
