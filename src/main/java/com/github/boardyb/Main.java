package com.github.boardyb;

import com.github.boardyb.domain.Room;
import com.github.boardyb.exception.FileParsingException;
import com.github.boardyb.service.FileParserService;
import com.github.boardyb.service.FileParserServiceImpl;
import com.github.boardyb.service.RoomService;
import com.github.boardyb.service.RoomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws FileParsingException {
        // Read data from provided file
        FileParserService fileParserService = new FileParserServiceImpl();
        List<Room> rooms = fileParserService.readFileAsRooms("sample-input.txt");

        // Calculate how much wallpaper is needed
        RoomService roomService = new RoomServiceImpl();
        int area = roomService.computeWallpaperOrderArea(rooms);
        log.info("Wallpaper area to order in square feet: {} ", area);

        // Fetch cubic shaped rooms:
        log.info("Cubic shaped rooms: ");
        List<Room> cubicShapedRoomsOrdered = roomService.getCubicShapedRoomsOrdered(rooms);
        for (Room room : cubicShapedRoomsOrdered) {
            log.info("{}", room);
        }

        // Fetch duplicated rooms:
        log.info("Duplicated rooms: ");
        List<Room> duplicateRooms = roomService.getDuplicateRooms(rooms);
        for (Room room : duplicateRooms) {
            log.info("{}", room);
        }
    }
}