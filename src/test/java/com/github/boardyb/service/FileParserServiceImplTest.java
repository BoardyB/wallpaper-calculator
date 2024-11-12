package com.github.boardyb.service;

import com.github.boardyb.domain.Room;
import com.github.boardyb.exception.FileParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParserServiceImplTest {

    private FileParserService fileParserService;

    @BeforeEach
    void setUp() {
        fileParserService = new FileParserServiceImpl();
    }

    @Test
    void shouldReadFileAsRooms() {
        List<Room> rooms = fileParserService.readFileAsRooms("test-file.txt");

        Room room1 = new Room(24, 18, 7);
        Room room2 = new Room(21, 18, 14);
        Room room3 = new Room(14, 26, 25);
        Room room4 = new Room(9, 11, 3);
        Room room5 = new Room(10, 7, 15);
        assertEquals(5, rooms.size());
        assertEquals(room1, rooms.get(0));
        assertEquals(room2, rooms.get(1));
        assertEquals(room3, rooms.get(2));
        assertEquals(room4, rooms.get(3));
        assertEquals(room5, rooms.get(4));
    }

    @Test
    void shouldNotReadFileIfPathIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> fileParserService.readFileAsRooms(null),
                "File path cannot be null or empty");
    }

    @Test
    void shouldNotReadFileIfPathIsEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> fileParserService.readFileAsRooms(""),
                "File path cannot be null or empty");
    }

    @Test
    void shouldNotReadFileIfExtensionIsNotSupported() {
        assertThrows(IllegalArgumentException.class,
                () -> fileParserService.readFileAsRooms("test.pdf"),
                "File path does not end with .txt");
    }

    @Test
    void shouldNotReadFileIfDoesNotExist() {
        assertThrows(FileParsingException.class,
                () -> fileParserService.readFileAsRooms("non-existent-file.txt"),
                "File does not exist: null");
    }

    @Test
    void shouldNotReadFileIfFormatIsInvalid() {
        assertThrows(FileParsingException.class,
                () -> fileParserService.readFileAsRooms("invalid-format.txt"),
                "Could not parse line: Invalid line format: 24x18x-7");
    }

    @Test
    void shouldNotReadFileIfDimensionsAreNotValid() {
        assertThrows(FileParsingException.class,
                () -> fileParserService.readFileAsRooms("invalid-dimensions.txt"),
                "Could not parse line: Invalid line format: 9x11");
    }

    @Test
    void shouldNotReadFileIfNumberIsOutOfBounds() {
        assertThrows(FileParsingException.class,
                () -> fileParserService.readFileAsRooms("type-out-of-bounds.txt"),
                "Invalid number format: For input string: \"21474836471\"");
    }

    @Test
    void shouldNotReadFileIfNumberIsNotPositive() {
        assertThrows(FileParsingException.class,
                () -> fileParserService.readFileAsRooms("invalid-number.txt"),
                "A dimension of the room is negative: 0, 7, 8");
    }
}