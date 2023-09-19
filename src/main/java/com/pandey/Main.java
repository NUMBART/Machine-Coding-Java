package com.pandey;

import com.pandey.models.Game;
import com.pandey.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Tic-Tac-Toe!");
        List<Player> players = new ArrayList<>(2);
        for(int i = 0; i < 2; ++i) {
            System.out.println("Enter the name and symbol for Player " + (i+1) + ": ");
            String name = sc.nextLine();
            Character symbol = sc.nextLine().charAt(0);
            players.add(new Player(name, symbol));
        }
        Game game = new Game(players);
        game.start();
    }
    // Remaining cases
    // 1. Handle out of bound moves
    // 2. Handle input a bit better
}