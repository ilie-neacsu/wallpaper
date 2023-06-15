package com.epam.wallpaper.infrastructure.fs;

import com.epam.wallpaper.domain.Room;
import com.epam.wallpaper.domain.repository.RoomsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class FileSystemRoomsRepository implements RoomsRepository {

    private List<Room> rooms;

    @Override
    public List<Room> findAll() {
        rooms = rooms == null ? getRoomsFromFile(): rooms;
        return rooms;
    }

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
                    BigDecimal length = new BigDecimal(values[0]);
                    BigDecimal width = new BigDecimal(values[1]);
                    BigDecimal height = new BigDecimal(values[2]);
                    roomsFromFile.add(new Room(length, width, height));
                } catch (NumberFormatException ex) {
                    log.warn("Invalid room format ignoring entry");
                }

            }

        } catch (IOException ex) {
            log.error("IOException thrown: '{}'", ex.getMessage());
            return null;
        }

        return roomsFromFile;
    }
}
