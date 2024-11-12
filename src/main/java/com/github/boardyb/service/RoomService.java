package com.github.boardyb.service;

import com.github.boardyb.domain.Room;

import java.util.List;

public interface RoomService {

    /**
     * Calculates the number of total square feet of wallpaper the company should order for all rooms from the input.
     * The calculation is running for each provided room and is based on the following formula:
     * (2 * Length * Width + 2 * Width * Height + 2 * Height * Length) + Length of smallest side
     * @param rooms Room objects representing the area which will be calculated.
     * @return Integer value which will be the required amount of wallpaper in square feet.
     */
    int computeWallpaperOrderArea(List<Room> rooms);

    /**
     * Lists all rooms from input that have a cubic shape ordered by total needed wallpaper descending.
     * A room is considered cubic shaped, if all of its dimensions are of equal length.
     * @param rooms Room objects from which the cubic shaped are fetched.
     * @return List of Room objects which have a cubic shaped dimension
     *         ordered by their total needed wallpaper in descending order.
     */
    List<Room> getCubicShapedRoomsOrdered(List<Room> rooms);

    /**
     * Lists all rooms from input that are appearing more than once.
     * A room is considered a duplicate if it has the same dimensions as other rooms from the input.
     * @param rooms Room objects from which the duplicate ones are fetched.
     * @return List of Room objects with duplicate dimensions.
     */
    List<Room> getDuplicateRooms(List<Room> rooms);
}
