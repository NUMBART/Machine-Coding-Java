package com.pandey.models.expenses;

import com.pandey.exceptions.ExpenseException;
import com.pandey.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ExactExpense extends Expense {
    Map<User, Float> payees;

    @Override
    public Map<User, Float> split() {
        return payees;
    }

    @Override
    public void validate() throws ExpenseException {
        Float payeeSplitSum = 0.0f;
        for(var entry: payees.entrySet()) {
            payeeSplitSum += entry.getValue();
        }
        if(!payeeSplitSum.equals(paidAmount))
            throw new ExpenseException("Sum of split amounts does not match paid amount!");
    }

    public static class Builder {
        User paidBy;
        Float paidAmout;
        Map<User, Float> payees = new HashMap<>();

        public Builder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public Builder setPaidAmout(Float paidAmout) {
            this.paidAmout = paidAmout;
            return this;
        }

        public Builder addPayee(User payee, Float split) {
            payees.put(payee, split);
            return this;
        }

        public ExactExpense build() {
            ExactExpense exactExpense = new ExactExpense();
            exactExpense.setPaidBy(paidBy);
            exactExpense.setPaidAmount(paidAmout);
            exactExpense.setPayees(payees);
            return exactExpense;
        }
    }
    public static Builder getBuilder() {
        return new Builder();
    }
}
