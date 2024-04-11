package org.example.breadfest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {

    @Test
    void testConstruction() {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        assertNotNull(cave_test);

    }
    @Test
    void generateNeighboringRoomsMap() {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        assertEquals(cave_test.getNeighboring_rooms().size(), 0);

        cave_test.generateNeighboringRoomsMap();
        assertNotEquals(cave_test.getNeighboring_rooms().size(), 0);
    }

    @Test
    void enterRoom0() throws Exception {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);

        assertEquals(cave_test.enterRoom0().getDepth(), 0);
    }

    @Test
    void enterRoom() throws Exception {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        cave_test.enterRoom('N', cave_test);
        assertNotNull(cave_test);
    }

    @Test
    void move() throws Exception {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        cave_test.generateNeighboringRoomsMap();
        Cave cave_moved_into_test = cave_test.move('N');
        assertNotNull(cave_moved_into_test);
    }

    @Test
    void getRoomNeighbor() throws Exception {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        Cave room_0_cave_test = cave_test.enterRoom0();
        room_0_cave_test.generateNeighboringRoomsMap();
        Cave north_cave_test = room_0_cave_test.getRoomNeighbor('N');
        assertNotNull(north_cave_test);
    }

    @Test
    void getDepth() {
        Cave cave_test = new Cave(0);
        int depth_test = cave_test.getDepth();
        assertEquals(depth_test, 0);
    }

    @Test
    void hasRoomBeenVisited() throws Exception {
        Cave cave_test = new Cave(0);
        Cave cave_test_room_0 = cave_test.enterRoom0();
        assertTrue(cave_test_room_0.hasRoomBeenVisited());
    }

    @Test
    void getBackgroundImage() {
        Cave cave_test = new Cave(0);
        String background_image_test = cave_test.getBackgroundImage();
        assertNull(background_image_test);
    }
//
//    @Test
//    void getRoomIngredients() {
//    }
//
//    @Test
//    void removeObjectFromLocation() {
//    }
//
//    @Test
//    void getRoomDinosaurs() {
//    }
}