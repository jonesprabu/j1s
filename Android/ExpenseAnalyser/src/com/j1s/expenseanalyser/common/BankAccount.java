package com.j1s.expenseanalyser.common;

import java.util.LinkedHashMap;
import java.util.List;

import com.j1s.model.Transaction;
import com.j1s.model.TransactionsMonthly;

public abstract class BankAccount {
	
	protected String accountNumber;
	protected String accountType;
	protected String bankName;

	protected int bankIcon;
	
	protected List<Transaction> transactions;
	protected LinkedHashMap<String, TransactionsMonthly> transactionsMonthWise;
	
	
	
	
	public int getBankIcon() {
		return bankIcon;
	}
	public void setBankIcon(int bankIcon) {
		this.bankIcon = bankIcon;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public LinkedHashMap<String, TransactionsMonthly> getTransactionsMonthWise() {
		return transactionsMonthWise;
	}
	public void setTransactionsMonthWise(
			LinkedHashMap<String, TransactionsMonthly> transactionsMonthWise) {
		this.transactionsMonthWise = transactionsMonthWise;
	}
	
	

}
