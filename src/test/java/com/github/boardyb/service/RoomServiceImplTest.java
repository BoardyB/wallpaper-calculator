package com.github.boardyb.service;

import com.github.boardyb.domain.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomServiceImplTest {

    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        roomService = new RoomServiceImpl();
    }

    @Test
    void shouldCalculateWallpaperOrderArea() {
        // GIVEN
        Room room1 = new Room(5, 10, 8);
        Room room2 = new Room(3, 4, 8);
        Room room3 = new Room(9, 8, 3);
        List<Room> rooms = List.of(room1, room2, room3);

        // WHEN
        int surfaceArea = roomService.computeWallpaperOrderArea(rooms);

        // THEN
        assertEquals(733, surfaceArea);
    }

    @Test
    void shouldGetCubicShapedRooms() {
        // GIVEN
        Room room1 = new Room(5, 5, 5);
        Room room2 = new Room(3, 3, 3);
        Room room3 = new Room(7, 7, 7);
        List<Room> rooms = List.of(room1, room2, room3);

        // WHEN
        List<Room> cubicShapedRoomsOrdered = roomService.getCubicShapedRoomsOrdered(rooms);

        // THEN
        assertEquals(cubicShapedRoomsOrdered.get(0), room3);
        assertEquals(cubicShapedRoomsOrdered.get(1), room1);
        assertEquals(cubicShapedRoomsOrdered.get(2), room2);
    }

    @Test
    void shouldNotGetRoomIfNotCubicShaped() {
        // GIVEN
        Room room1 = new Room(5, 6, 7);
        Room room2 = new Room(3, 4, 5);
        Room room3 = new Room(7, 7, 9);
        List<Room> rooms = List.of(room1, room2, room3);

        // WHEN
        List<Room> cubicShapedRoomsOrdered = roomService.getCubicShapedRoomsOrdered(rooms);

        // THEN
        assertEquals(0, cubicShapedRoomsOrdered.size());
    }

    @Test
    void shouldGetDuplicatedRooms() {
        // GIVEN
        Room room1 = new Room(5, 5, 5);
        Room room2 = new Room(3, 4, 5);
        Room room3 = new Room(5, 5, 5);
        Room room4 = new Room(6, 7, 8);
        List<Room> rooms = List.of(room1, room2, room3, room4);

        // WHEN
        List<Room> duplicateRooms = roomService.getDuplicateRooms(rooms);

        // THEN
        assertEquals(2, duplicateRooms.size());
        assertEquals(duplicateRooms.get(0), room1);
        assertEquals(duplicateRooms.get(1), room3);
    }
}