package com.epam.wallpaper.application.cli;

import com.epam.wallpaper.domain.Room;
import com.epam.wallpaper.domain.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CliRoomsController {
    private final RoomsService roomsService;

    @Autowired
    public CliRoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    public void getTotalWallpaper() {
        System.out.println("Number of total square feet of wallpaper: " +
                roomsService.getTotalWallpaperSurface());
        System.out.println("\n");
    }

    public void getCubicShapeRooms() {
        System.out.println("Rooms from input that have a cubic shape (order by total needed wallpaper descending): ");
        List<Room> rooms = roomsService.getCubicRooms();
        for(Room room: rooms) {
            System.out.println(room);
        }
        System.out.println("\n");
    }

    public void getDuplicateRooms() {
        System.out.println("Rooms from input that are appearing more than once:");
        List<Room> duplicateRooms = roomsService.getDuplicateRooms();
        for(Room room: duplicateRooms) {
            System.out.println(room);
        }
        System.out.println("\n");
    }
}
