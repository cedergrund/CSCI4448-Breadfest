package org.example.breadfest;

import org.example.breadfest.ingredients.JSONReadingHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONReadingHelperTest {


    @Test
    void getRandomExcuse() throws IOException {
        JSONReadingHelper test_json = new JSONReadingHelper();
        String test_excuse = test_json.getRandomExcuse();
        assertNotNull(test_excuse);
    }

    @Test
    void generateRandomElementFromJSON() {
    }
}