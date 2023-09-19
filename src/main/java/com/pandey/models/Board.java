package com.pandey.models;

import com.pandey.constants.GameConstants;
import com.pandey.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    List<List<Character>> grid;

    Board() {
        int sz = GameConstants.gridSize;
        grid = new ArrayList<>(sz);
        for(int i = 0; i < sz; ++i) {
            grid.add(new ArrayList<>(sz));
            for(int j = 0; j < sz; ++j)
                grid.get(i).add(null);
        }
    }
    public void mark(Character symbol, int x, int y) throws InvalidMoveException {
        if(grid.get(x).get(y) != null)
            throw new InvalidMoveException();
        grid.get(x).set(y, symbol);
    }
    public void print() {
        for(var row: grid) {
            for(var symbol: row) {
                if(symbol == null) System.out.print("-\t");
                else System.out.print(symbol + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    public boolean isLastMove(int moveNumber) {
        return moveNumber >= grid.size()*grid.size();
    }
    public Character checkSimultaneousChars() {
        int sz = grid.size();
        List<Boolean> isRowSame = new ArrayList<>(sz);
        List<Boolean> isColSame = new ArrayList<>(sz);
        for(int i = 0; i < sz; ++i) {
            isRowSame.add(true);
            isColSame.add(true);
        }
        for(int i = 0; i < sz; ++i)
            for(int j = 0; j < sz; ++j) {
                if (grid.get(i).get(j) != grid.get(i).get(0))
                    isRowSame.set(i, false);
                if(grid.get(i).get(j) != grid.get(0).get(j))
                    isColSame.set(j, false);
            }
        for(int i = 0; i < sz; ++i) {
            if(isRowSame.get(i)) return grid.get(i).get(0);
            if(isColSame.get(i)) return grid.get(0).get(i);
        }
        boolean isDiagonalSame = true;
        for(int i = 0, j = 0; i < sz; ++i, ++j)
            if (grid.get(0).get(0) != grid.get(i).get(j))
                isDiagonalSame = false;
        if(isDiagonalSame) return grid.get(0).get(0);
        isDiagonalSame = true;
        for(int i = 0, j = sz-1; i < sz; ++i, --j)
            if (grid.get(0).get(sz-1) != grid.get(i).get(j))
                isDiagonalSame = false;
        if(isDiagonalSame) return grid.get(0).get(sz-1);
        return null;
    }
}
