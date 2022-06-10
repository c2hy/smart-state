package io.github.c2hy.smartstate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySmartStateTest {

    @Test
    void isTrue() {
        SmartState smartState = new ArraySmartState(2);
        assertTrue(smartState.isTrue(2));
    }

    @Test
    void isFalse() {
        SmartState smartState = new ArraySmartState(2);
        assertFalse(smartState.isFalse(2));
    }

    @Test
    void expects() {
        SmartState smartState = new ArraySmartState(2);
        assertTrue(smartState.expects(
                PositioningState.of(1, false),
                PositioningState.of(2, true)
        ));
        PositioningState[] stateList = new PositioningState[32];
        assertThrows(IllegalArgumentException.class, () -> smartState.expects(stateList), "Illegal number of args 32");
    }

    @Test
    void setTrue() {
        SmartState originalSmartState = new ArraySmartState(0);
        SmartState smartState = originalSmartState.setTrue(2);
        assertEquals(2, smartState.number());
    }

    @Test
    void setFalse() {
        SmartState originalSmartState = new ArraySmartState(2);
        SmartState smartState = originalSmartState.setFalse(2);
        assertEquals(0, smartState.number());
    }

    @Test
    void setState() {
        SmartState originalSmartState = new ArraySmartState(2);
        SmartState smartState = originalSmartState.setState(
                PositioningState.of(2, false),
                PositioningState.of(1, true)
        );
        assertEquals(1, smartState.number());
        PositioningState[] stateList = new PositioningState[32];
        assertThrows(IllegalArgumentException.class, () -> smartState.expects(stateList), "Illegal number of args 32");
    }
}