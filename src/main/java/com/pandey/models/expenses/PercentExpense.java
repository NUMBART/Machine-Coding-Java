package com.pandey.models.expenses;

import com.pandey.exceptions.ExpenseException;
import com.pandey.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PercentExpense extends Expense {
    Map<User, Float> payees;
    @Override
    public Map<User, Float> split() {
        Map<User, Float> splits = new HashMap<>();
        for(var payee: payees.entrySet()) {
            splits.put(payee.getKey(), payee.getValue()*paidAmount/100);
        }
        return splits;
    }

    @Override
    public void validate() throws ExpenseException {
        Float payeeSplitSum = 0.0f;
        for(var entry: payees.entrySet()) {
            payeeSplitSum += entry.getValue();
        }
        if(!payeeSplitSum.equals(100.0f))
            throw new ExpenseException("Sum of split percentages should be 100% !");
    }

    public static class Builder {
        User paidBy;
        Float paidAmount;
        HashMap<User, Float> payees = new HashMap<>();

        public Builder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public Builder setPaidAmount(Float paidAmount) {
            this.paidAmount = paidAmount;
            return this;
        }

        public Builder addPayee(User payee, Float percentage) {
            this.payees.put(payee, percentage);
            return this;
        }

        public PercentExpense build() {
            PercentExpense percentExpense = new PercentExpense();
            percentExpense.setPaidBy(paidBy);
            percentExpense.setPaidAmount(paidAmount);
            percentExpense.setPayees(payees);
            return percentExpense;
        }
    }

    public static Builder getBuilder() {
        return new Builder();
    }
}
