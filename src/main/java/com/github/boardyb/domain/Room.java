package com.github.boardyb.domain;

public record Room(int length, int width, int height) {

    public Room {
        if (length <= 0 || width <= 0 || height <= 0) {
            throw new IllegalArgumentException("A dimension of the room is negative: "
                    + length + ", " + width + ", " + height);
        }
    }
}
