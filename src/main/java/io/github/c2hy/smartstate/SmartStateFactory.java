package io.github.c2hy.smartstate;

public class SmartStateFactory {
    public static SmartState create(int number) {
        return new ArraySmartState(number);
    }

    public static SmartState create(String binaryString) {
        return new ArraySmartState(Integer.parseInt(binaryString, 2));
    }

    public static SmartState create() {
        return new ArraySmartState(0);
    }
}
