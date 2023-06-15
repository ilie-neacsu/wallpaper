package com.epam.wallpaper.domain.service;

import com.epam.wallpaper.domain.Room;

import java.math.BigDecimal;
import java.util.List;

public interface RoomsService {
    BigDecimal getTotalWallpaperSurface();
    List<Room> getCubicRoomsList();
    List<Room> getDuplicateRoomsList();

}
