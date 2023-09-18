package com.pandey.models;

import com.pandey.constants.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    List<List<Integer>> board;
    List<Integer> spawnNumbers = Arrays.asList(2, 2, 2, 2, 4, 4, 8);
    int size;

    public Board(int size) {
        this.size = size;
        board = new ArrayList<>(size);
        for(int i = 0; i < size; ++i) {
            board.add(new ArrayList<>(size));
            for(int j = 0; j < size; ++j) {
                board.get(i).add(null);
            }
        }
        this.addTile();
        print();
    }
    public void addTile() {
        int emptyCount = 0;
        List<List<Integer>> coordinates = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(board.get(i).get(j) == null) {
                    emptyCount++;
                    coordinates.add(Arrays.asList(i, j));
                }
            }
        }
        Random random = new Random();
        int randomIndex = random.nextInt(emptyCount);
        List<Integer> randomCell = coordinates.get(randomIndex);
        Integer tileValue = spawnNumbers.get(random.nextInt(spawnNumbers.size()));
        board.get(randomCell.get(0)).set(randomCell.get(1), tileValue);
    }
    public void print() {
        for(var row: board) {
            for(var tile: row) {
                if(tile == null) System.out.print("-\t\t");
                else System.out.print(tile+"\t\t");
            }
            System.out.println();
        }
    }
    public boolean isScorePresent(int score) {
        for(var row: board)
            for(var tile: row)
                if(tile != null && tile == score)
                    return true;
        return false;
    }
    public void slide(MoveDirection moveDirection) {
        switch(moveDirection) {
            case LEFT:
                for(int i = 0; i < size; ++i) {
                    int curSwapIdx = 0, lastMergeIdx = -1;
                    for(int j = 0; j < size; ++j) {
                        if(board.get(i).get(j) == null) continue;
                        if(curSwapIdx-1 == lastMergeIdx || board.get(i).get(j) != board.get(i).get(curSwapIdx-1)) {
                            board.get(i).set(curSwapIdx, board.get(i).get(j));
                            if(j != curSwapIdx) board.get(i).set(j, null);
                            curSwapIdx++;
                        }
                        else {
                            board.get(i).set(curSwapIdx-1, 2*board.get(i).get(j));
                            if(j != curSwapIdx-1) board.get(i).set(j, null);
                            lastMergeIdx = curSwapIdx-1;
                        }
                    }
                }
                break;
            case RIGHT:
                for(int i = 0; i < size; ++i) {
                    int curSwapIdx = size-1, lastMergeIdx = size;
                    for(int j = size-1; j >= 0; j--) {
                        if(board.get(i).get(j) == null) continue;
                        if(curSwapIdx+1 == lastMergeIdx || board.get(i).get(j) != board.get(i).get(curSwapIdx+1)) {
                            board.get(i).set(curSwapIdx, board.get(i).get(j));
                            if(j != curSwapIdx) board.get(i).set(j, null);
                            curSwapIdx--;
                        }
                        else {
                            board.get(i).set(curSwapIdx+1, 2*board.get(i).get(j));
                            if(j != curSwapIdx+1) board.get(i).set(j, null);
                            lastMergeIdx = curSwapIdx+1;
                        }

                    }
                }
                break;
            case TOP:
                for(int j = 0; j < size; ++j){
                    int curSwapIdx = 0, lastMergeIdx = -1;
                    for(int i = 0; i < size; ++i) {
                        if(board.get(i).get(j) == null) continue;
                        if(curSwapIdx-1 == lastMergeIdx || board.get(i).get(j) != board.get(curSwapIdx-1).get(j)) {
                            board.get(curSwapIdx).set(j, board.get(i).get(j));
                            if(i != curSwapIdx) board.get(i).set(j, null);
                            curSwapIdx++;
                        }
                        else {
                            board.get(curSwapIdx-1).set(j, 2*board.get(i).get(j));
                            if(i != curSwapIdx-1) board.get(i).set(j, null);
                            lastMergeIdx = curSwapIdx-1;
                        }
                    }
                }
                break;
            case BOTTOM:
                for(int j = 0; j < size; j++) {
                    int curSwapIdx = size-1, lastMergeIdx = size;
                    for(int i = size-1; i >= 0; --i) {
                        if(board.get(i).get(j) == null) continue;
                        if(curSwapIdx+1 == lastMergeIdx || board.get(i).get(j) != board.get(curSwapIdx+1).get(j)) {
                            board.get(curSwapIdx).set(j, board.get(i).get(j));
                            if(i != curSwapIdx) board.get(i).set(j, null);
                            curSwapIdx--;
                        }
                        else {
                            board.get(curSwapIdx+1).set(j, 2*board.get(i).get(j));
                            if(i != curSwapIdx+1) board.get(i).set(j, null);
                            lastMergeIdx = curSwapIdx+1;
                        }
                    }
                }
                break;
        }
    }

    public boolean isFull() {
        for(var row: board)
            for(var tile: row)
                if(tile == null) return false;
        return true;
    }
}
