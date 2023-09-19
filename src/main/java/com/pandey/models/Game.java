package com.pandey.models;

import com.pandey.constants.GameStatus;
import com.pandey.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    List<Player> players;
    GameStatus gameStatus = GameStatus.INPROGRESS;
    Integer moveNumber = 0;
    Board board;
    public Game(List<Player> players) {
        this.players = players;
        this.board = new Board();
    }
    public void makeMove(int x, int y) throws InvalidMoveException {
        Player curPlayer = players.get(moveNumber%2);
        board.mark(curPlayer.getSymbol(), x-1, y-1);
        moveNumber++;
        Character simultaneousChar = board.checkSimultaneousChars();
        if(simultaneousChar != null) {
            for (var player : players)
                if (player.getSymbol() == simultaneousChar) {
                    System.out.println(player.getName() + " won the game!");
                    gameStatus = GameStatus.COMPLETED;
                }
        }
        if(board.isLastMove(moveNumber)) {
            System.out.println("Game has ended in a draw!");
            gameStatus = GameStatus.COMPLETED;
        }
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        board.print();
        while(gameStatus == GameStatus.INPROGRESS) {
            Player curPlayer = players.get(moveNumber%2);
            System.out.println(curPlayer.getName() + "'s turn, enter the cell you wish to mark: ");
            int i = sc.nextInt(); sc.nextLine();
            int j = sc.nextInt(); sc.nextLine();
            try {
                this.makeMove(i, j);
                board.print();
            } catch (InvalidMoveException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
