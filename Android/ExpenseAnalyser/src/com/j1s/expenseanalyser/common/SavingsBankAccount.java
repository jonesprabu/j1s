package com.j1s.expenseanalyser.common;


public abstract class SavingsBankAccount extends BankAccount{
	
	protected int balanceAmount;
	
	
	public int getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(int balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
}
