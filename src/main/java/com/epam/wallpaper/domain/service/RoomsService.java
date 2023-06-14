package com.epam.wallpaper.domain.service;

import com.epam.wallpaper.domain.Room;
import com.epam.wallpaper.infrastructure.RoomsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoomsService {

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    public double getTotalWallpaperSurface() {

        List<Room> rooms = roomsRepository.getAll();

        double totalWallpaperSurface = 0.0;

        for(Room room : rooms) {
            totalWallpaperSurface += room.getSurface();
            totalWallpaperSurface += room.getExtra();
        }

        return totalWallpaperSurface;
    }

    public List<Room> getCubicRooms() {
        List<Room> rooms = roomsRepository.getAll();

        return rooms
                .stream()
                .filter(Room::isCubic)
                .sorted(Comparator.comparingDouble(Room::getTotal).reversed())
                .collect(Collectors.toList());
    }

    public List<Room> getDuplicateRooms() {
        List<Room> rooms = roomsRepository.getAll();
        List<Room> duplicateRooms = new ArrayList<>();
        Set<Room> roomsSet = new HashSet<>();
        for(Room room: rooms) {
            if(roomsSet.contains(room)) {
                duplicateRooms.add(room);
            }
            roomsSet.add(room);
        }

        return duplicateRooms;
    }
}
