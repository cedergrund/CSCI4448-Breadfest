package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.ingredients.Ingredient;
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
        assertEquals(cave_test.getNeighboringRooms().size(), 0);

        cave_test.generateNeighboringRoomsMap();
        assertEquals(cave_test.getNeighboringRooms().size(), 3);
    }

    @Test
    void enterRoom0() throws Exception {
        Cave cave_test = Cave.enterRoom0();
        assertEquals(cave_test.getDepth(), 0);
    }

    @Test
    void enterRoom() throws Exception {
        int test_depth = 0;
        Cave cave_test = new Cave(test_depth);
        cave_test.enterRoom('N', cave_test);
        assertTrue(cave_test.getRoomNeighbor('N').hasRoomBeenVisited());
    }

    @Test
    void move() throws Exception {
        int test_depth = 0;
        Cave cave_test = Cave.enterRoom0();
        cave_test.generateNeighboringRoomsMap();
        Cave cave_moved_into_test = cave_test.move('N');
        assertEquals(cave_moved_into_test, cave_test.getRoomNeighbor('N'));
    }

    @Test
    void getDepth() {
        Cave cave_test = new Cave(0);
        int depth_test = cave_test.getDepth();
        assertEquals(depth_test, 0);

        Cave cave_test2 = new Cave(10);
        int depth_test2 = cave_test2.getDepth();
        assertEquals(depth_test2, 10);
    }

    @Test
    void hasRoomBeenVisited() throws Exception {
        Cave cave_test_room_0 = Cave.enterRoom0();
        assertTrue(cave_test_room_0.hasRoomBeenVisited());

        Cave cave_test2 = new Cave(10);
        assertFalse(cave_test2.hasRoomBeenVisited());
    }

    @Test
    void getRoomIngredients() throws Exception {
        Cave cave_test_room_0 = Cave.enterRoom0();
        assertEquals(cave_test_room_0.getRoomIngredients().length, 8);
        for (Ingredient ingredient: cave_test_room_0.getRoomIngredients()){
            assertNull(ingredient);
        }
    }


    @Test
    void getRoomDinosaurs() throws Exception {
        Cave cave_test_room_0 = Cave.enterRoom0();
        assertEquals(cave_test_room_0.getRoomDinosaurs().length, 8);
        for (Dinosaur dinosaur: cave_test_room_0.getRoomDinosaurs()){
            assertNull(dinosaur);
        }
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