package com.j1s.expenseanalyser.common;

import java.util.Date;

public abstract class CreditCardAccount extends BankAccount {

	protected int dueAmount;  
	protected Date dueDate;
	
	
	public int getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
