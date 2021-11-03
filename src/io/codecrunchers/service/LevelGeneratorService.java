package io.codecrunchers.service;

import io.codecrunchers.providers.LevelGeneratorServiceProvider;

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

    private LevelGeneratorServiceProvider provider;

    public LevelGeneratorService(LevelGeneratorServiceProvider provider){
        this.provider=provider;
    }


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
            int skip=this.rooms[i]*(worldHeight+1)+1;

            try {
                file0 = new Scanner(new FileReader(this.provider.world0path()));
            } catch (Exception e) {
                System.out.println("Error with file");
            }
            for(int j=0;j<skip;j++){
                file0.nextLine();
            }
            for(int n=0;n<worldHeight;n++) {
                inputLine = file0.nextLine();
                tiles[pointer] = inputLine;
                pointer++;
            }
            file0.close();

        }


    }

    public void writeWorldOnFile() {
        try {
            pw = new PrintWriter(new FileWriter("res/finalWorld.txt"));
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


