package com.pandey;

import com.pandey.models.Group;
import com.pandey.models.User;
import com.pandey.models.expenses.EqualExpense;
import com.pandey.models.expenses.ExactExpense;
import com.pandey.models.expenses.PercentExpense;

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
                .setPaidAmout(500.0f)
                .addPayee(anand)
                .addPayee(anil)
                .addPayee(amit)
                .build();
        group.addExpense(equalExpense);
        group.showBalances();
        group.showBalancesForUser(anil);

        ExactExpense exactExpense = ExactExpense.getBuilder()
                .setPaidBy(amit)
                .setPaidAmout(500.0f)
                .addPayee(anand, 200.0f)
                .addPayee(anil, 100.0f)
                .addPayee(amit, 200.0f)
                .build();
        group.addExpense(exactExpense);
        group.showBalances();
        group.showBalancesForUser(anil);

        PercentExpense percentExpense = PercentExpense.getBuilder()
                .setPaidBy(amit)
                .setPaidAmount(1000.0f)
                .addPayee(anand, 80.0f)
                .addPayee(anil, 20.0f)
                .build();
        group.addExpense(percentExpense);
        group.showBalances();
        group.showBalancesForUser(anil);
    }
}