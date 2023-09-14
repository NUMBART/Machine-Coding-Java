package com.pandey;

import com.pandey.constants.GameStatus;
import com.pandey.models.Board;
import com.pandey.models.Player;
import com.pandey.models.SingleDie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Snake and Ladders");
        System.out.println("How many players do you want to play with: ");
        Integer playerCnt = sc.nextInt();
        sc.nextLine();
        List<Player> players = new ArrayList<>(playerCnt);
        System.out.println("Enter the names of the players: ");
        for(int i = 0; i < playerCnt; ++i) {
            String name = sc.nextLine();
            players.add(new Player(name));
        }
        System.out.println("How many snakes should be there in the board: ");
        Integer snakeCnt = sc.nextInt(); sc.nextLine();
        System.out.println("How many ladders should be there in the board: ");
        Integer ladderCnt = sc.nextInt(); sc.nextLine();
        Board board = new Board(players, snakeCnt, ladderCnt, new SingleDie());
        while(board.getGameStatus() != GameStatus.COMPLETED) {
            board.move();
        }
    }
}