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

    public Dice makeDieByType(String die_type, String source) throws Exception {

        String dice_string = JSONReadingHelper.generateRandomElementFromJSON(this.die_types, die_type);
        String[] dice_parts = dice_string.split("\\|");

        int num_die_rolled = Integer.parseInt(String.valueOf(dice_parts[1]));

        int[] dice_roll_numbers = Arrays.stream(dice_parts[2].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (Objects.equals(source, "dino") && !Objects.equals(dice_parts[0], "Hail Mary")){
            while (dice_roll_numbers[0] == 0 && dice_roll_numbers[1] == 0 & dice_roll_numbers[2] == 0){
                dice_string = JSONReadingHelper.generateRandomElementFromJSON(this.die_types, die_type);
                dice_parts = dice_string.split("\\|");

                num_die_rolled = Integer.parseInt(String.valueOf(dice_parts[1]));

                dice_roll_numbers = Arrays.stream(dice_parts[2].split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }

        final String[] final_dice_parts = dice_parts;
        if (dice_roll_numbers[0] == 0 && dice_roll_numbers[1] == 0 & dice_roll_numbers[2] == 0){ // special die

            if (Objects.equals(final_dice_parts[0], "Hail Mary")){
                return new Dice() {
                    public int rollDice() {
                        Random random_seed = new Random();
                        if (random_seed.nextDouble() < 0.1){
                            return 100;
                        }
                        else{
                            return 0;
                        }
                    }
                    public String getName() {
                        return final_dice_parts[0];
                    }
                    public String getDescription() {
                        return final_dice_parts[3];
                    }
                    public String getPDFImage() {
                        if (Objects.equals(final_dice_parts[4], "empty")){
                            final_dice_parts[4] = "file:src/main/resources/org/example/breadfest/Images/i_dont_know.png";
                        }
                        return final_dice_parts[4];
                    }
                    public String getRarity() {
                        return die_type;
                    }
                };
            }

            return new Dice() {
                public int rollDice() {
                    return Integer.parseInt(final_dice_parts[1]) * -1000;
                }
                public String getName() {
                    return final_dice_parts[0];
                }
                public String getDescription() {
                    return final_dice_parts[3];
                }
                public String getPDFImage() {
                    if (Objects.equals(final_dice_parts[4], "empty")){
                        final_dice_parts[4] = "file:src/main/resources/org/example/breadfest/Images/i_dont_know.png";
                    }
                    return final_dice_parts[4];
                }
                public String getRarity() {
                    return die_type;
                }
            };
        }

        List<Integer> roll_possibilities = new ArrayList<>();

        if (dice_roll_numbers[2] == 1){
            roll_possibilities.add(dice_roll_numbers[0]);
        }
        else{
            for (int curr_roll = dice_roll_numbers[0]; curr_roll<= dice_roll_numbers[1]; curr_roll += ((dice_roll_numbers[1]-dice_roll_numbers[0])/(dice_roll_numbers[2]-1))){
                roll_possibilities.add(curr_roll);
            }
        }

        final int final_num_die_rolled = num_die_rolled;
        return new Dice() {
            public int rollDice() {
                Random random_seed = new Random();
                int total_roll = 0;
                for (int die_num = 0; die_num < final_num_die_rolled; die_num++){
                    total_roll += roll_possibilities.get(random_seed.nextInt(roll_possibilities.size()));
                }

                return total_roll;
            }
            public String getName() {
                return final_dice_parts[0];
            }
            public String getDescription() {
                return final_dice_parts[3];
            }
            public String getPDFImage() {
                if (Objects.equals(final_dice_parts[4], "empty")){
                    final_dice_parts[4] = "file:src/main/resources/org/example/breadfest/Images/i_dont_know.png";
                }
                return final_dice_parts[4];
            }
            public String getRarity() {
                return die_type;
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
                return "Just your everyday 6-sided Die. Nothing to it really. Kinda boring to be honest...";
            }

            @Override
            public String getPDFImage() {
                return "file:src/main/resources/org/example/breadfest/Images/dice_pdf/normal.png";
            }

            @Override
            public String getRarity() {
                return "Common";
            }
        };
    }

}
