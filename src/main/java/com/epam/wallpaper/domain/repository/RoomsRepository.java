package com.epam.wallpaper.domain.repository;

import com.epam.wallpaper.domain.Room;

import java.util.List;

public interface RoomsRepository {
    List<Room> findAll();
}
