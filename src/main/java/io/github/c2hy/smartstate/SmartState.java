package io.github.c2hy.smartstate;

public interface SmartState {
    default String deployTest() {
        return "success";
    }
}
