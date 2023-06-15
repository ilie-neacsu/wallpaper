package com.epam.wallpaper.domain.service;

import com.epam.wallpaper.domain.Room;
import com.epam.wallpaper.domain.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository roomsRepository;

    @Autowired
    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public BigDecimal getTotalWallpaperSurface() {
        return roomsRepository.findAll().stream()
                .reduce(BigDecimal.ZERO,
                        (total, room) -> total.add(room.getTotal()),
                        BigDecimal::add);

    }

    @Override
    public List<Room> getCubicRoomsList() {
        List<Room> rooms = roomsRepository.findAll();

        return rooms
                .stream()
                .filter(Room::isCubic)
                .sorted(Comparator.comparing(Room::getTotal).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getDuplicateRoomsList() {
        List<Room> rooms = roomsRepository.findAll();
        Map<Room, Integer> roomIntegerMap = getRoomsToIntegerMap(rooms);

        List<Room> duplicateRoomsList = new ArrayList<>();

        for(Map.Entry<Room, Integer> entry : roomIntegerMap.entrySet()) {
            if(entry.getValue() > 1) {
                duplicateRoomsList.addAll(Collections.nCopies(entry.getValue(), entry.getKey()));
            }
        }

        return duplicateRoomsList;
    }

    private Map<Room, Integer> getRoomsToIntegerMap(List<Room> rooms) {

        Map<Room, Integer> roomsToInteger = new HashMap<>();

        for (Room room : rooms) {
            if (roomsToInteger.containsKey(room)) {
                roomsToInteger.computeIfPresent(room, (k, v) -> v + 1);
            } else {
                roomsToInteger.put(room, 1);
            }
        }

        return roomsToInteger;

    }
}


