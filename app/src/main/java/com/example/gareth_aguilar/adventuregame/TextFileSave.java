package com.example.gareth_aguilar.adventuregame;

import android.content.Context;

import java.io.*;

public class TextFileSave extends Encounter implements Serializable {
    private FileOutputStream file;
    private ObjectOutput obj;

    public void saveName(String[] str) {
        try {
            file = openFileOutput("Game.data", Context.MODE_PRIVATE);
            obj = new ObjectOutputStream(file);
            obj.writeObject(str);
            obj.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
