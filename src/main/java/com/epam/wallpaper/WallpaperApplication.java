package com.epam.wallpaper;

import com.epam.wallpaper.application.cli.CliRoomsController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WallpaperApplication implements CommandLineRunner {

	private final CliRoomsController cliRoomsController;

	@Autowired
	public WallpaperApplication(CliRoomsController cliRoomsController) {
		this.cliRoomsController = cliRoomsController;
	}

	public static void main(String[] args) {
		SpringApplication.run(WallpaperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application started");
		cliRoomsController.getTotalWallpaper();
		cliRoomsController.getCubicShapeRooms();
		cliRoomsController.getDuplicateRooms();
	}
}
