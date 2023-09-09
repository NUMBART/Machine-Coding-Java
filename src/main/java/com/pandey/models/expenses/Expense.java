package com.pandey.models.expenses;

import com.pandey.exceptions.ExpenseException;
import com.pandey.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class Expense {
    User paidBy;
    Float paidAmount;
    public abstract Map<User, Float> split();
    public abstract void validate() throws ExpenseException;
}
