package io.github.c2hy.smartstate;

public interface SmartState {
    int number();

    boolean isTrue(int position);

    boolean isFalse(int position);

    boolean expects(PositioningState... states);

    SmartState setTrue(int position);

    SmartState setFalse(int position);

    SmartState setState(PositioningState... states);
}
