package com.github.boardyb.service;

import com.github.boardyb.domain.Room;
import com.github.boardyb.exception.FileParsingException;

import java.util.List;


public interface FileParserService {

    /**
     * Reads the dimensions of a room from a file of the provided path line-by-line.
     * The line should contain the dimensions of the room in the following format:
     * "LengthxWidthxHeight" example: 13x5x19
     * The method does validation against this format and also if the numbers are not positive integers.
     * If any validation checks have been violated, the method logs it on the console.
     *
     * @param filePath path of the file to read.
     * @return list of Room objects which were parsed.
     * @throws FileParsingException if errors occurred during the parsing of the file.
     */
    List<Room> readFileAsRooms(String filePath) throws FileParsingException;
}
