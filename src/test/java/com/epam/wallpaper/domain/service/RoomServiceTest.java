package com.epam.wallpaper.domain.service;

import com.epam.wallpaper.domain.Room;
import com.epam.wallpaper.domain.repository.RoomsRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoomServiceTest {
    private RoomsRepository roomsRepository;
    private RoomsServiceImpl roomsServiceImpl;

    @BeforeEach
    void setUp() {
        roomsRepository = mock(RoomsRepository.class);
        roomsServiceImpl = new RoomsServiceImpl(roomsRepository);
    }

    @Test
    void shouldCalculateNumberOfTotalSquareFeetOfWallpaperToBeOrdered() {

        List<Room> roomList = Arrays.asList(
                new Room(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3)),
                new Room(new BigDecimal(1), new BigDecimal(1),new BigDecimal(5))
        );

        when(roomsRepository.findAll()).thenReturn(roomList);

        BigDecimal expected = new BigDecimal(47);
        BigDecimal actual = roomsServiceImpl.getTotalWallpaperSurface();
        assertThat(actual, Matchers.comparesEqualTo(expected));
    }
}
