package com.github.boardyb.service;

import com.github.boardyb.domain.Room;
import com.github.boardyb.exception.FileParsingException;
import com.github.boardyb.exception.RoomDimensionParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileParserServiceImpl implements FileParserService {

    private static final Logger log = LoggerFactory.getLogger(FileParserServiceImpl.class);

    @Override
    public List<Room> readFileAsRooms(String filePath) throws FileParsingException {
        List<Room> rooms = new ArrayList<>();
        validateFilePath(filePath);

        log.info("Reading file {}", filePath);
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

            reader.lines().map(String::trim).forEach(line -> {
                Room room = readLineAsRoom(line);
                log.debug("Successfully read room: {}", room);
                rooms.add(room);
            });
        } catch (IOException e) {
            log.error("Could not read file: {} ", e.getMessage());
            throw new FileParsingException(e);
        } catch (NullPointerException e) {
            log.error("File does not exist: {}", e.getMessage());
            throw new FileParsingException(e);
        } catch (NumberFormatException e) {
            log.error("Invalid number format: {}", e.getMessage());
            throw new FileParsingException(e);
        } catch (RoomDimensionParsingException e) {
            log.error("Could not parse line: {}", e.getMessage());
            throw new FileParsingException(e);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new FileParsingException(e);
        }

        return rooms;
    }

    private void validateFilePath(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        if (!filePath.endsWith(".txt")) {
            throw new IllegalArgumentException("File path does not end with .txt");
        }
    }

    private Room readLineAsRoom(String line) {
        if (!line.matches("^\\d+x\\d+x\\d+$")) {
            throw new RoomDimensionParsingException("Invalid line format: " + line);
        }
        String[] dimensions = line.split("x");
        if (dimensions.length != 3) {
            throw new RoomDimensionParsingException("Incorrect amount of dimensions: " + line);
        }

        int length = Integer.parseInt(dimensions[0]);
        int width = Integer.parseInt(dimensions[1]);
        int height = Integer.parseInt(dimensions[2]);

        return new Room(length, width, height);
    }

}
