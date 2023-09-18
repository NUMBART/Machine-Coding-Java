package com.pandey.models;

import com.pandey.constants.GameConstants;
import com.pandey.constants.GameStatus;
import com.pandey.constants.MoveDirection;
import com.pandey.exceptions.InvalidMoveCodeException;

import java.util.Scanner;

public class Game {
    Board board;
    GameStatus gameStatus = GameStatus.INPROGRESS;
    private void move(Integer code) throws InvalidMoveCodeException {
        board.slide(MoveDirection.getByCode(code));
        board.addTile();
        if(board.isScorePresent(GameConstants.requiredScore))
            gameStatus = GameStatus.WON;
        if(board.isFull())
            gameStatus = GameStatus.LOST;
        board.print();
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to 2048 Game!");
        System.out.println("Enter the board size: ");
        int size = sc.nextInt(); sc.nextLine();
        board = new Board(size);
        while(gameStatus == GameStatus.INPROGRESS) {
            System.out.println(gameStatus.getMessage());
            int moveCode = sc.nextInt(); sc.nextLine();
            try {
                this.move(moveCode);
            } catch (InvalidMoveCodeException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(gameStatus.getMessage());
    }
}
