package com.pandey.models;

import java.util.ArrayList;
import java.util.List;

public class BalanceKeeper {
    List<List<Float>> balanceMap = new ArrayList<>();
    int userCnt;
    BalanceKeeper(int userCnt) {
        this.userCnt = userCnt;
        for (int i = 0; i < userCnt; ++i) {
            ArrayList<Float> row = new ArrayList<>(userCnt);
            for (int j = 0; j < userCnt; ++j) {
                row.add(0.0f);
            }
            balanceMap.add(row);
        }
    }
    void paid(int x, int y, Float amount) {
        // y owes x: amount
        balanceMap.get(x).set(y, balanceMap.get(x).get(y)+amount);
        balanceMap.get(y).set(x, balanceMap.get(y).get(x)-amount);
    }
    public void printBalances() {
        for(int i = 0; i < userCnt; ++i) {
            for(int j = i+1; j < userCnt; ++j) {
                if(balanceMap.get(i).get(j) > 0)
                    System.out.println(j + " owes " + i + ": " + balanceMap.get(i).get(j));
                else
                    System.out.println(i + " owes " + j + ": " + balanceMap.get(j).get(i));
            }
        }
        System.out.println();
    }

    public void printBalancesForId(int userId) {
        for(int j = 0; j < userCnt; ++j) {
            if(balanceMap.get(userId).get(j) > 0)
                System.out.println(j + " owes " + userId + ": " + balanceMap.get(userId).get(j));
            else
                System.out.println(userId + " owes " + j + ": " + balanceMap.get(j).get(userId));
        }
        System.out.println();
    }
}
