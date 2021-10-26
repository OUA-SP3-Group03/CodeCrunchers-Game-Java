package io.codecrunchers.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class LevelGeneratorService {

    //Rooms
    private static final int ROOM_0 = 0;
    private static final int ROOM_1 = 1;

    //Scanners
    private Scanner file0 = null;
    private Scanner file1 = null;

    //PrintWriter
    PrintWriter pw = null;

    //
    private int rooms[];
    String tiles[];
    private final int numberOfRooms = 3;
    private final int worldHeight = 4;


    public void chooseWorld() {
        rooms = new int[numberOfRooms];
        Random random = new Random();
        for (int i = 0; i < numberOfRooms; i++) {
            rooms[i] = random.nextInt(numberOfRooms - 1);
            System.out.println(rooms[i]);
        }
    }

    public void generateWorld() {
        String inputLine = "";
        tiles = new String[worldHeight * numberOfRooms];
        int pointer = 0;


        for (int i = 0; i < numberOfRooms; i++) {

            //a case for every room must be implemented
            switch (rooms[i]) {
                case 0:

                    try {
                        file0 = new Scanner(new FileReader("res/textures/worlds/WORLD_0.txt"));
                    } catch (Exception e) {
                        System.out.println("Error with room 0");
                    }
                    while (file0.hasNextLine()) {
                        inputLine = file0.nextLine();
                        tiles[pointer] = inputLine;
                        pointer++;

                    }
                    file0.close();
                    break;
                case 1:

                    try {
                        file1 = new Scanner(new FileReader("res/textures/worlds/WORLD_1.txt"));
                    } catch (Exception e) {
                        System.out.println("Error with room 1");
                    }
                    while (file1.hasNextLine()) {
                        inputLine = file1.nextLine();
                        tiles[pointer] = inputLine;
                        pointer++;
                    }
                    file1.close();
                    break;
            }
        }


    }

    public void writeWorldOnFile() {
        try {
            pw = new PrintWriter(new FileWriter("res/textures/worlds/finalWorld.txt"));
        } catch (Exception e) {
            System.out.println("File error-Wrong path");
        }

        for (int i = 0; i < worldHeight; i++) {
            for (int j = 0; j < numberOfRooms; j++)
                if (j == numberOfRooms - 1)
                    pw.println(tiles[i + worldHeight * j] + " ");
                else
                    pw.print(tiles[i + worldHeight * j] + " ");
        }
        pw.close();
    }
}


