package com.pandey;

import com.pandey.models.Group;
import com.pandey.models.User;
import com.pandey.models.expenses.EqualExpense;

public class Main {
    public static void main(String[] args) {
        User amit = new User("Amit", "amit@company.com", "123");
        User anand = new User("Anand", "anand@company.com", "123");
        User anil = new User("Anil", "anil@company.com", "123");
        Group group = Group.getBuilder()
                .addUser(amit)
                .addUser(anand)
                .addUser(anil)
                .build();
        EqualExpense equalExpense = EqualExpense.getBuilder()
                .setPaidBy(amit)
                .setPaidAmout(600.0f)
                .addPayee(anand)
                .addPayee(anil)
                .addPayee(amit)
                .build();
        group.addExpense(equalExpense);
        group.showBalances();
        group.showBalancesForUser(anil);
    }
}