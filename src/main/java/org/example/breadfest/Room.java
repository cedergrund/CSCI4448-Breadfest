package org.example.breadfest;

import org.example.breadfest.dinosaurs.Dinosaur;
import org.example.breadfest.dinosaurs.DinosaurFactory;
import org.example.breadfest.ingredients.IngredientFactory;
import org.example.breadfest.ingredients.IngredientRarity;
import org.example.breadfest.ingredients.IngredientTypes;
import org.example.breadfest.ingredients.Ingredients;

import java.util.*;

public class Room {

    private final int depth;

    private char entry_direction;

    private Map<Character, Room> neighboring_rooms;

    private String background_image;

    private Ingredients[] ingredients_in_room_by_location;
    private Dinosaur[] dinosaurs_in_room_by_location;

    private IngredientFactory ingredient_factory;
    private DinosaurFactory dinosaur_factory;



    public Room(int depth){
        // depth of room from cave-room 0
        this.depth = depth;
        this.neighboring_rooms = new HashMap<>();

    }

    public boolean areThereNeighbors(){
        if (this.neighboring_rooms == null) {
            return false;
        }
        else {
            return true;
        }

    }

    private int generateNeighboringRoomsCount(){
        if (this.depth == 0){
            return 3;
        }

        double chance_for_dead_end = this.depth*0.1;
        double chance_for_connected_room = (1-chance_for_dead_end)/3;

        Random random_seed = new Random();
        double random_roll = random_seed.nextDouble();

        if (random_roll < this.depth*0.1){
            return 0;
        }
        else if (random_roll < chance_for_dead_end+chance_for_connected_room){
            return 1;
        }
        else if (random_roll < chance_for_dead_end+2*chance_for_connected_room){
            return 2;
        }
        else {
            return 3;
        }
    }

    private int generateDinosaurCount(){

        Random random_seed = new Random();

        if (this.depth > 7){

            // max 1 spawns above room depth 7. 50% chance of spawn rn
            double random_roll = random_seed.nextDouble();
            if (random_roll < 0.5){
                return 1;
            }
            return 0;
        }

        double chance_for_dead_end = this.depth*0.1;
        double chance_for_connected_room = (1-chance_for_dead_end)/3;

        double random_roll = random_seed.nextDouble();

        if (random_roll < this.depth*0.1){
            return 0;
        }
        else if (random_roll < chance_for_dead_end+chance_for_connected_room){
            return 1;
        }
        else if (random_roll < chance_for_dead_end+2*chance_for_connected_room){
            return 2;
        }
        else {
            return 3;
        }
    }

    private void addIngredient(Ingredients added_ingredient){

    }

    private void generateIngredients() throws Exception {

        this.ingredient_factory = new IngredientFactory();

        if (this.depth == 0){

            // check to see if add a nuclear ingredient to the room
            Random random_seed = new Random();
            double random_roll = random_seed.nextDouble();
            if (random_roll < 0.01){
                IngredientTypes type_of_ingredient = IngredientTypes.getRandomIngredientType();
                this.addIngredient(ingredient_factory.IngredientFactory(type_of_ingredient, IngredientRarity.Nuclear));
            }
            return;
        }

        // how many rooms are generated
        int num_ingredients_to_generate = this.generateNeighboringRoomsCount();
//
//        // generate neighboring rooms.
//        this.generateNeighboringRoomsMap(num_rooms_to_generate);
//
//        // generate background image
//        this.background_image = this.generate_background_image(num_rooms_to_generate);



//        if (this.depth == 0){
//            return 3;
//        }
//
//        double chance_for_dead_end = this.depth*0.1;
//        double chance_for_connected_room = (1-chance_for_dead_end)/3;
//
//        Random random_seed = new Random();
//        double random_roll = random_seed.nextDouble();
//
//        if (random_roll < this.depth*0.1){
//            return 0;
//        }
//        else if (random_roll < chance_for_dead_end+chance_for_connected_room){
//            return 1;
//        }
//        else if (random_roll < chance_for_dead_end+2*chance_for_connected_room){
//            return 2;
//        }
//        else {
//            return 3;
//        }
    }

    private void generateDinosaurs() throws Exception {

        this.dinosaur_factory = new DinosaurFactory();

        if (this.depth == 0){
            return;
        }

        // how many rooms are generated
        int num_ingredients_to_generate = this.generateNeighboringRoomsCount();
//
//        // generate neighboring rooms.
//        this.generateNeighboringRoomsMap(num_rooms_to_generate);
//
//        // generate background image
//        this.background_image = this.generate_background_image(num_rooms_to_generate);



//        if (this.depth == 0){
//            return 3;
//        }
//
//        double chance_for_dead_end = this.depth*0.1;
//        double chance_for_connected_room = (1-chance_for_dead_end)/3;
//
//        Random random_seed = new Random();
//        double random_roll = random_seed.nextDouble();
//
//        if (random_roll < this.depth*0.1){
//            return 0;
//        }
//        else if (random_roll < chance_for_dead_end+chance_for_connected_room){
//            return 1;
//        }
//        else if (random_roll < chance_for_dead_end+2*chance_for_connected_room){
//            return 2;
//        }
//        else {
//            return 3;
//        }
    }

    private void generateNeighboringRoomsMap(int num_rooms_to_generate){

        if (num_rooms_to_generate == 0){
            return;
        }

        List<Character> available_directions = new ArrayList<Character>() {{
            add('N');
            add('S');
            add('E');
            add('W');
        }};

        available_directions.remove(this.entry_direction);
        Random random = new Random();

        switch (num_rooms_to_generate){
            case 3:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
            }
            case 2:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
            }
            case 1:{
                int direction_index = random.nextInt(available_directions.size());
                char new_room_direction = available_directions.remove(direction_index);

                this.neighboring_rooms.put(new_room_direction, new Room(this.depth+1));
                break;
            }

        }

    }

    private String generate_background_image(int num_rooms_to_generate){

        // TODO: add art for different room types, and randomly generate one based on num rooms
        return "";

    }

    private void populateRoom(int num_rooms_to_generate){


        this.dinosaurs_in_room_by_location = new Dinosaur[5];
        // TODO: add dinosaurs to the room in one of 5 locations:


        this.ingredients_in_room_by_location = new Ingredients[7];
        // TODO: add ingredients to room in one of 7 locations:


    }

    private void newRoomGeneration(){

        // how many rooms are generated
        int num_rooms_to_generate = this.generateNeighboringRoomsCount();

        // generate neighboring rooms.
        this.generateNeighboringRoomsMap(num_rooms_to_generate);

        // generate background image
        this.background_image = this.generate_background_image(num_rooms_to_generate);

        // populate room

    }

    public Room getRoomNeighbor(char direction){
        return this.neighboring_rooms.get(direction);
    }

    public int getDepth() {
        return depth;
    }

    public void moveToRoom(char entry_direction, Room prev_room){

        // direction from which player enter the room (in what direction cave-room 0 is)
        this.entry_direction = entry_direction;

        // generating new neighbors
        this.neighboring_rooms = new HashMap<>();
        this.neighboring_rooms.put(entry_direction, prev_room);
        newRoomGeneration();
    }
}
