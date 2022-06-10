package io.github.c2hy.smartstate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartStateFactoryTest {

    @Test
    void of() {
        SmartState smartState1 = SmartStateFactory.create(2);
        assertEquals(2, smartState1.number());

        SmartState smartState2 = SmartStateFactory.create("00000000000000000000000000000010");
        assertEquals(2, smartState2.number());

        SmartState smartState3 = SmartStateFactory.create("0000000000000010");
        assertEquals(2, smartState3.number());

        SmartState smartState4 = SmartStateFactory.create("00000000000000001111111111111111");
        assertEquals(65535, smartState4.number());

        SmartState smartState5 = SmartStateFactory.create("1111111111111111");
        assertEquals(65535, smartState5.number());

        SmartState smartState6 = SmartStateFactory.create("01111111111111111111111111111111");
        assertEquals(Integer.MAX_VALUE, smartState6.number());
    }
}