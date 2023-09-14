package com.pandey.models;

import com.pandey.constants.GameStatus;
import com.pandey.exceptions.InvalidLadderException;
import com.pandey.exceptions.InvalidSnakeException;
import com.pandey.exceptions.MultiplePipeStartException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Board {
    List<Player> players;
    HashMap<Integer, Pipe> pipes = new HashMap<>();
    GameStatus gameStatus = GameStatus.INPROGRESS;
    int moveNumber = 0;
    Die die;
    public Board(List<Player> players, Integer snakeCnt, Integer ladderCnt, Die die) {
        this.players = players;
        this.die = die;
        for(int i = 0; i < snakeCnt; ++i) {
            Random random = new Random();
            Integer start = random.nextInt(99) + 1;
            Integer end = random.nextInt(99) + 1;
            try {
                Snake snake = new Snake(start, end);
                if(pipes.containsKey(start))
                    throw new MultiplePipeStartException();
                pipes.put(start, snake);
            } catch(InvalidSnakeException | MultiplePipeStartException ex) {
                i--;
            }
        }

        for(int i = 0; i < ladderCnt; ++i) {
            Random random = new Random();
            Integer start = random.nextInt(99) + 1;
            Integer end = random.nextInt(99) + 1;
            try {
                Ladder ladder = new Ladder(start, end);
                if(pipes.containsKey(start))
                    throw new MultiplePipeStartException();
                pipes.put(start, ladder);
            } catch(InvalidLadderException | MultiplePipeStartException ex) {
                i--;
            }
        }
    }
    public void move() {
        Player curPlayer = players.get(moveNumber%players.size());
        Integer playerPosition = curPlayer.getCurPos();
        Integer curDieRoll = die.roll();
        if(playerPosition + curDieRoll > 100) {
            System.out.println(curPlayer.getName() + " rolled a " + curDieRoll + " and moved from " + playerPosition + " to " + playerPosition + ".");
            moveNumber++;
            return;
        }
        Integer newPosition = playerPosition + curDieRoll;
        while(pipes.containsKey(newPosition)) {
            Pipe curPipe = pipes.get(newPosition);
            newPosition = curPipe.getEnd();
        }
        curPlayer.setCurPos(newPosition);
        System.out.println(curPlayer.getName() + " rolled a " + curDieRoll + " and moved from " + playerPosition + " to " + newPosition + ".");
        if(newPosition == 100) {
            gameStatus = GameStatus.COMPLETED;
            System.out.println(curPlayer.getName() + " wins the game.");
        }
        moveNumber++;
    }
}
