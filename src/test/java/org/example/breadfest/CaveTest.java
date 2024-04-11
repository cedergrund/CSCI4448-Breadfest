package org.example.breadfest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaveTest {

    @Test
    void testConstruction(){

        Cave test_cave = new Cave(0);
        assertNotNull(test_cave);
    }

    @Test
    void getRoomNeighbor() {
        Cave test_cave = new Cave(0);
        assertNull(test_cave.getRoomNeighbor('N'));
    }


    @Test
    void moveToRoom() {
        Cave test_cave = null;
        try {
            test_cave = Cave.enterRoom0();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_cave.getRoomNeighbor('N'));

        Cave north_cave = null;
        try {
            north_cave = test_cave.move('N');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(north_cave);
    }

    @Test
    void moveToRoomExtended() {
        Cave test_cave = new Cave(0);
        try {
            test_cave.enterRoom0();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_cave.getRoomNeighbor('N'));

        Cave north_cave = null;
        try {
            north_cave = test_cave.move('N');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(north_cave);

        Cave go_back_to_cave0 = null;
        try {
            go_back_to_cave0 = north_cave.move('S');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(go_back_to_cave0);
        assertEquals(go_back_to_cave0, test_cave);

        Cave east_cave = null;
        try {
            east_cave = go_back_to_cave0.move('E');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(east_cave);
    }

    @Test
    void getDepth() {
    }

    @Test
    void getBackgroundImage() {
    }

    @Test
    void getRoomIngredients() {
    }

    @Test
    void getRoomDinosaurs() {
    }
}