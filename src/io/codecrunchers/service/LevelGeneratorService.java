package io.codecrunchers.service;

import io.codecrunchers.providers.LevelServiceProvider;

import java.io.*;
import java.util.Random;

public class LevelGeneratorService {

    //world variables
    private boolean generated = false;
    private int worldWidth;
    private int worldHeight;
    private int[] worldTiles;

    //room variables
    private int roomHeight;
    private int roomWidth;
    private int[][] rooms;
    private final int maxRooms;

    public LevelGeneratorService(LevelServiceProvider provider) {
        //set the max rooms variable
        this.maxRooms = provider.getMaxRooms();

        //load file on boot
        this.loadFile(provider.world0path());
    }



    public void loadFile(String path) {

        int currentRoom = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            String room = "";

            while ((line = br.readLine()) != null) {

                if (line.contains("{{new_room}}")) {
                    this.rooms[currentRoom] = new int[room.length()];

                    for (int i = 0; i < room.length(); i++) {
                        this.rooms[currentRoom][i] = room.charAt(i) - '0';
                    }
                    room = "";
                    currentRoom++;

                }
                if (!line.contains("//") && !line.contains("{{") && !line.isBlank()) {

                    room += line;

                } else if (line.contains("{{room_width}}")) {
                    this.roomWidth = Integer.parseInt(line.split("=")[1]);
                } else if (line.contains("{{room_count}}")) {

                    //new variables
                    int roomCount = Integer.parseInt(line.split("=")[1]);
                    this.rooms = new int[roomCount][this.roomWidth];
                } else if (line.contains("{{room_height}}")) {
                    this.roomHeight = Integer.parseInt(line.split("=")[1]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void generate() {
        String world = "";

        this.worldWidth = this.roomWidth * this.maxRooms;
        this.worldHeight = roomHeight;

        int[] worldRooms = new int[this.maxRooms];
        int i = 0;
        Random random = new Random();
        while (i < this.maxRooms) {
            worldRooms[i] = random.nextInt(this.rooms.length);
            i++;

        }
        int x = 0;
        int y = 0;
        while (y < this.roomHeight) {
        for (int j = 0; j < this.maxRooms; j++) {
            int selectedRoom = worldRooms[j];
                while (x < this.roomWidth) {
                    int tile = this.roomWidth * y;

                    world += this.rooms[selectedRoom][x+tile];
                    x++;
                }
                x = 0;
            }
            y++;
        }

        int[] worldTiles = new int[world.length()];
        for (i = 0; i < world.length(); i++) {
            worldTiles[i] = world.charAt(i) - '0';
        }

        //return the final world tiles array
        this.worldTiles = worldTiles;

    }

    public void generateWorld(){
        this.generate();
    }

    public int[] getWorld(){
        return this.worldTiles;
    }

    public int getWorldWidth(){
            return this.worldWidth;
    }

    public int getWorldHeight(){
            return this.worldHeight;
    }

}


