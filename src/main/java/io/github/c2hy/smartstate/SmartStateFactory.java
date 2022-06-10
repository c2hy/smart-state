package io.github.c2hy.smartstate;

public class SmartStateFactory {
    public static SmartState of(int number) {
        return new ArraySmartState(number);
    }

    public static SmartState of(String binaryString) {
        return new ArraySmartState(Integer.parseInt(binaryString, 2));
    }
}
