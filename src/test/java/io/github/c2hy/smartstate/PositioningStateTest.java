package io.github.c2hy.smartstate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositioningStateTest {
    @Test
    void of() {
        assertThrows(IllegalArgumentException.class,
                () -> PositioningState.of(0, false),
                "Illegal position 0");
        assertThrows(IllegalArgumentException.class,
                () -> PositioningState.of(32, false),
                "Illegal position 32");

        PositioningState state = PositioningState.of(1, false);
        assertEquals(1, state.position());
        assertFalse(state.state());
    }
}