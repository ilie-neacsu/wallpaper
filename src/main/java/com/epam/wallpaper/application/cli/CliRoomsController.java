package com.epam.wallpaper.application.cli;

import com.epam.wallpaper.domain.service.RoomsService;
import com.epam.wallpaper.domain.service.RoomsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CliRoomsController {
    private final RoomsService roomsService;

    @Autowired
    public CliRoomsController(RoomsService roomsServiceImpl) {
        this.roomsService = roomsServiceImpl;
    }

    public void getTotalWallpaper() {
        log.info("Number of total square feet of wallpaper: {}", roomsService.getTotalWallpaperSurface());
    }

    public void getCubicShapeRooms() {
        log.info("Rooms from input that have a cubic shape (order by total needed wallpaper descending): ");
        roomsService.getCubicRoomsList().forEach(room -> log.info("Cubic shape room entry: {}", room));
    }

    public void getDuplicateRooms() {
        log.info("Rooms from input that are appearing more than once: ");
        roomsService.getDuplicateRoomsList().forEach(room -> log.info("Duplicate room entry: {}", room));
    }
}
