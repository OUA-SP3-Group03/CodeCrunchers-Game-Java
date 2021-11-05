package io.codecrunchers.engine.service;

import io.codecrunchers.engine.providers.LevelGeneratorServiceProvider;

import java.io.*;
import java.util.Random;

public class LevelGeneratorService {



    //
    String[] world;
    private int worldWidth;

    private int roomHeight;
    private int roomWidth;
    private int[][] rooms;
    private final int maxRooms;

    public LevelGeneratorService(LevelGeneratorServiceProvider provider) {


        this.loadFile(provider.world0path());
        for (int i = 0; i < this.rooms[0].length; i++) {
            System.out.println("room 1 tile " + i + " = " + this.rooms[0][i]);
        }
        for (int i = 0; i < this.rooms[1].length; i++) {
            System.out.println("room 2 tile " + i + " = " + this.rooms[1][i]);
        }

        this.maxRooms = provider.getMaxRooms();
        this.generate();
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
        int worldWidth = this.roomWidth * this.maxRooms;
        int worldHeight = roomHeight;
        int[] worldRooms = new int[this.maxRooms];
        int i = 0;
        Random random = new Random();
        while (i < this.maxRooms) {
            worldRooms[i] = random.nextInt(this.rooms.length);
            i++;

        }
        int x = 0;
        int y = 0;
        int offset = 0;
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

        System.out.println("world= " + world);
        System.out.println("rooms array lenght " + rooms.length);
        System.out.println("printing world rooms");
        for ( i=0;i<worldRooms.length;i++)
            System.out.println(worldRooms[i]);


    }


}


