package com.pandey.models.expenses;

import com.pandey.exceptions.ExpenseException;
import com.pandey.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EqualExpense extends Expense {
    List<User> payees;
    @Override
    public Map<User, Float> split() {
        HashMap<User, Float> splits = new HashMap<>();
        Float splitAmount = (float)Math.round(paidAmount*100/payees.size())/100;
        for(User payee: payees) {
            splits.put(payee, splitAmount);
        }
        splits.put(payees.get(0), paidAmount - splitAmount*(payees.size()-1));
        return splits;
    }

    @Override
    public void validate() throws ExpenseException {
    }

    public static class Builder {
        User paidBy;
        Float paidAmout;
        List<User> payees = new ArrayList<>();

        public Builder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public Builder setPaidAmout(Float paidAmout) {
            this.paidAmout = paidAmout;
            return this;
        }

        public Builder addPayee(User payee) {
            payees.add(payee);
            return this;
        }

        public EqualExpense build() {
            EqualExpense equalExpense = new EqualExpense();
            equalExpense.setPaidBy(paidBy);
            equalExpense.setPaidAmount(paidAmout);
            equalExpense.setPayees(payees);
            return equalExpense;
        }
    }
    public static Builder getBuilder() {
        return new Builder();
    }
}
