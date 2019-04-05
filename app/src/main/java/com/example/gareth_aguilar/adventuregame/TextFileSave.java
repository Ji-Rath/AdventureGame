package com.example.gareth_aguilar.adventuregame;

import java.io.*;

public class TextFileSave implements Serializable {
    private FileOutputStream file;
    private ObjectOutput obj;

    public void saveName(String[] str) {
        try {
            file = new FileOutputStream("Game.data");
            obj = new ObjectOutputStream(file);
            obj.writeObject(str);
            obj.close();
        } catch(IOException e) {
            System.out.println("Error!");
        }
    }
}
