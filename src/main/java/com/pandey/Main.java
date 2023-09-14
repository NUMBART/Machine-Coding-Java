package com.pandey;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;
import com.pandey.models.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player anand = new Player(1, "Anand");
        Player pandey = new Player(2, "Pandey");
        Board board = new Board();
        board.addPlayer(anand);
        board.addPlayer(pandey);
        board.print();

        Scanner sc = new Scanner(System.in);
        while(true) {
            Player curPlayer = board.getPlayers().get((board.getMoveNumber()-1)%2);
            System.out.println("Player " + curPlayer.getName() + "'s turn: ");
            String startCell = sc.nextLine();
            String endCell = sc.nextLine();
            try {
                Move move = new Move(startCell, endCell);
                board.move(move);
                board.print();
            }
            catch(InvalidMoveException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}