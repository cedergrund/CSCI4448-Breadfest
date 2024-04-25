package org.example.breadfest.dice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.breadfest.dinosaurs.DinosaurAndDiceTypes;
import org.example.breadfest.ingredients.JSONReadingHelper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DieFactory {

    private final Map<String, List<String>> die_types;

    public DieFactory() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String DICE_FILE_PATH = "src/main/java/org/example/breadfest/dice/dice-types.json";
        this.die_types = mapper.readValue(new File(DICE_FILE_PATH), new TypeReference<>() {});
    }

    public Dice makeDieByType(DinosaurAndDiceTypes die_type) throws Exception {

        String type = die_type.toString();
        String dice_string = JSONReadingHelper.generateRandomElementFromJSON(this.die_types, type);
        String[] dice_parts = dice_string.split("\\|");

        int num_die_rolled = Integer.parseInt(String.valueOf(dice_parts[1]));

        int[] dice_roll_numbers = Arrays.stream(dice_parts[2].split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> roll_possibilities = new ArrayList<>();

        if (dice_roll_numbers[2] == 1){
            roll_possibilities.add(dice_roll_numbers[0]);
        }
        else{
            for (int curr_roll = dice_roll_numbers[0]; curr_roll<= dice_roll_numbers[1]; curr_roll += ((dice_roll_numbers[1]-dice_roll_numbers[0])/(dice_roll_numbers[2]-1))){
                roll_possibilities.add(curr_roll);
            }
        }

        return new Dice() {
            @Override
            public int rollDice() {
                Random random_seed = new Random();

                int total_roll = 0;
                for (int die_num = 0; die_num < num_die_rolled; die_num++){
                    total_roll += roll_possibilities.get(random_seed.nextInt(roll_possibilities.size()));
                }

                return total_roll;
            }

            @Override
            public String getName() {
                return dice_parts[0];
            }

            @Override
            public String getDescription() {
                return dice_parts[3];
            }

            @Override
            public String getPDFImage() {
                if (Objects.equals(dice_parts[4], "empty")){
                    dice_parts[4] = "";
                }
                return dice_parts[4];
            }

            @Override
            public String getRarity() {
                return die_type.toString();
            }
        };
    }

    public static Dice generateBaseDie(){
        return new Dice() {
            @Override
            public int rollDice() {
                Random random_seed = new Random();
                return random_seed.nextInt(6) + 1;
            }

            @Override
            public String getName() {
                return "Normal Die";
            }

            @Override
            public String getDescription() {
                return "Just your everyday 6-sided Die";
            }

            @Override
            public String getPDFImage() {
                return "";
            }

            @Override
            public String getRarity() {
                return "Common";
            }
        };
    }

}
