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
        for(User payee: payees) {
            // naive approach - 2 decimal places and matched sum not handled
            splits.put(payee, this.paidAmount/payees.size());
        }
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
