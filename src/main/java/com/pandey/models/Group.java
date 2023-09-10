package com.pandey.models;

import com.pandey.exceptions.ExpenseException;
import com.pandey.models.expenses.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Group {
    List<User> users;
    List<Expense> expenses;
    BalanceKeeper balanceKeeper;

    public static class Builder {
        List<User> users = new ArrayList<>();
        public Builder addUser(User user) {
            user.setId(users.size());
            users.add(user);
            return this;
        }
        public Group build() {
            Group group = new Group();
            group.setUsers(users);
            group.setExpenses(new ArrayList<>());
            group.setBalanceKeeper(new BalanceKeeper(users.size()));
            return group;
        }
    }
    public static Builder getBuilder() {
        return new Builder();
    }

    public void addExpense(Expense expense) {
        try {
            expense.validate();
            Map<User, Float> splits = expense.split();
            User paidBy = expense.getPaidBy();
            for (var entry : splits.entrySet()) {
                User paidTo = entry.getKey();
                Float amount = entry.getValue();
                balanceKeeper.paid(paidBy.getId(), paidTo.getId(), amount);
            }
            expenses.add(expense);
        } catch(ExpenseException ex) {
            System.out.println("Failed to add expense: " + ex.getMessage());
        }
    }
    public void showBalances() {
        balanceKeeper.printBalances();
    }
    public void showBalancesForUser(User user) {
        balanceKeeper.printBalancesForId(user.getId());
    }
}
