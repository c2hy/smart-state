package io.github.c2hy.smartstate;

class Position {
    private final static String POSITION_VALIDATE_EXCEPTION_DESCRIPTION = "Illegal position %s";
    private final static String POSITION_ARRAY_LENGTH_VALIDATE_EXCEPTION_DESCRIPTION = "Illegal number of args %s";
    public final int position;
    final int index;

    Position(int position) {
        this.position = position;
        this.index = position - 1;
    }

    public static Position of(int position) {
        validateRange(position, POSITION_VALIDATE_EXCEPTION_DESCRIPTION);
        return new Position(position);
    }

    static void validatePositionArrayLength(int length) {
        validateRange(length, POSITION_ARRAY_LENGTH_VALIDATE_EXCEPTION_DESCRIPTION);
    }

    private static void validateRange(int range, String exceptionDescription) {
        if (range < 1 || range > 31) {
            throw new IllegalArgumentException(String.format(exceptionDescription, range));
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "position=" + position +
                ", index=" + index +
                '}';
    }
}
