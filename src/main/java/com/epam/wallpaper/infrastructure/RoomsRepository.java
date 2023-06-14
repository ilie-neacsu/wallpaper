package com.epam.wallpaper.infrastructure;

import com.epam.wallpaper.domain.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class RoomsRepository {

    private List<Room> rooms;

    private List<Room> getRoomsFromFile() {

        List<Room> roomsFromFile = new ArrayList<>();

        try {
            File file = ResourceUtils.getFile("classpath:input.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while((line = bufferedReader.readLine()) != null) {

                try {
                    String[] values = line.split("x");

                    double length = Double.parseDouble(values[0]);
                    double width = Double.parseDouble(values[1]);
                    double height = Double.parseDouble(values[2]);

                    roomsFromFile.add(new Room(length, width, height));
                } catch (NumberFormatException ex) {
                    log.warn("Invalid room format ignoring line");
                }

            }

        } catch (IOException ex) {
            log.error("IOException thrown: '{}'", ex.getMessage());
            return null;
        }

        return roomsFromFile;
    }

    public List<Room> getAll() {
        rooms = rooms == null ? getRoomsFromFile(): rooms;
        return rooms;
    }
}
