package org.example.breadfest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testConstruction(){

        Room test_room = new Room(0);
        assertNotNull(test_room);
    }

    @Test
    void getRoomNeighbor() {
        Room test_room = new Room(0);
        assertNull(test_room.getRoomNeighbor('N'));
    }


    @Test
    void moveToRoom() {
        Room test_room = null;
        try {
            test_room = Room.enterRoom0();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_room.getRoomNeighbor('N'));

        Room north_room = null;
        try {
            north_room = test_room.move('N');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(north_room);
    }

    @Test
    void moveToRoomExtended() {
        Room test_room = new Room(0);
        try {
            test_room.enterRoom0();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotNull(test_room.getRoomNeighbor('N'));

        Room north_room = null;
        try {
            north_room = test_room.move('N');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(north_room);

        Room go_back_to_room0 = null;
        try {
            go_back_to_room0 = north_room.move('S');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(go_back_to_room0);
        assertEquals(go_back_to_room0, test_room);

        Room east_room = null;
        try {
            east_room = go_back_to_room0.move('E');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertNotNull(east_room);
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