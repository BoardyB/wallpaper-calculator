package com.github.boardyb.service;

import com.github.boardyb.domain.Room;
import com.github.boardyb.domain.RoomWithArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomServiceImpl implements RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);

    @Override
    public int computeWallpaperOrderArea(List<Room> rooms) {
        return rooms.stream().mapToInt(this::calculateSurfaceArea).sum();
    }

    public int calculateSurfaceArea(Room room) {
        int length = room.length();
        int width = room.width();
        int height = room.height();

        log.debug("Dimensions of the room: {}, {}, {}", length, width, height);

        int surfaceArea = 2 * length * width + 2 * width * height + 2 * height * length;
        int smallestSide = Math.min(length, Math.min(width, height));

        log.debug("Calculated surface area: {}", surfaceArea + smallestSide);
        return surfaceArea + smallestSide;
    }

    @Override
    public List<Room> getCubicShapedRoomsOrdered(List<Room> rooms) {
        return rooms.stream()
                .filter(room -> room.length() == room.width() && room.width() == room.height())
                .map(room -> new RoomWithArea(room, calculateSurfaceArea(room)))
                .sorted((r1, r2) -> Integer.compare(r2.surfaceArea(), r1.surfaceArea()))
                .map(RoomWithArea::room)
                .toList();
    }

    @Override
    public List<Room> getDuplicateRooms(List<Room> rooms) {
        Map<String, Integer> roomCountMap = new HashMap<>();

        for (Room room : rooms) {
            String dimensionsKey = room.length() + "-" + room.width() + "-" + room.height();
            roomCountMap.put(dimensionsKey, roomCountMap.getOrDefault(dimensionsKey, 0) + 1);
        }

        log.debug("Rooms to counts: {}", roomCountMap);

        return rooms.stream()
                .filter(room -> roomCountMap.get(room.length() + "-" + room.width() + "-" + room.height()) > 1)
                .toList();
    }

}
