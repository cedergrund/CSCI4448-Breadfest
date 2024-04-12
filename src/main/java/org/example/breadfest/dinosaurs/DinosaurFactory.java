package org.example.breadfest.dinosaurs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.breadfest.ingredients.JSONReadingHelper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DinosaurFactory {

    private final Map<String, List<String>> dinosaur_names;

    public DinosaurFactory() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String DIALOGUE_FILE_PATH = "src/main/java/org/example/breadfest/dinosaurs/dinosaurs.json";
        this.dinosaur_names = mapper.readValue(new File(DIALOGUE_FILE_PATH), new TypeReference<>() {});
    }

    public Dinosaur makeADinosaurFromDepth(int room_depth) throws Exception {

        return this.makeADinosaurByType(DinosaurTypes.getRandomDinosaurType(room_depth));

    }

    public Dinosaur makeADinosaurByType(DinosaurTypes dinosaur_type) throws Exception {

        String type = dinosaur_type.toString();
        String dinosaur_name = JSONReadingHelper.generateRandomElementFromJSON(this.dinosaur_names, type);

        return new Dinosaur(dinosaur_name, dinosaur_type);

    }

}
