package com.epam.wallpaper.infrastructure.fs;

public class NoValidRoomsException  extends RuntimeException {
    public NoValidRoomsException(String errorMessage) {
        super(errorMessage);
    }
}
