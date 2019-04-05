package com.example.gareth_aguilar.adventuregame;

import java.util.Scanner;

public class AdventureGameRunner {
    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to AdventureGame Test,\nwe will be testing the random adventure system, stats of players, etc.\nLets start!");
        System.out.println("\n\n");

        game.pickCharacter();

        System.out.println("Setting: The game will take place in feudal Japan, a time of war and unrest.");
        System.out.println("The objective of the game will be to travel across Japan to Kyoto, the capital.");
        System.out.println("To deliver a note that says - something");
        game.pause();
        System.out.println("The adventure starts with 3 travelers: "+game.getPlayer(0).getName()+", "+game.getPlayer(1).getName()+", "+game.getPlayer(2).getName());
        while(game.getProgress() != 100) {
            game.menu();
            //game.randomEvent();
            game.addProgress(5);
        }
        //Start random events here
        //Order of major provinces - Echigo, Shinano, Mino, Omu



    }
}
