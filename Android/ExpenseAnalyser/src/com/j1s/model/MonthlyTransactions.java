package com.j1s.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="monthly_transactions")
public class MonthlyTransactions {
	@DatabaseField(generatedId=true)
	private int id;
	@ForeignCollectionField
	private ForeignCollection<Transaction> transactions;
	@DatabaseField
	private String monthName;
	@DatabaseField
	private int monthInMM;
	@DatabaseField
	private int monthtotalAmt;
	@DatabaseField
	private int yearInYYYY;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Transaction> getTransactionsList() {
		List<Transaction> transactionsList = null;
		if(transactions != null){
			transactionsList = new ArrayList<Transaction>();
			for(Transaction transaction : transactions){
				transactionsList.add(transaction);
			}
		}		
		return transactionsList;
	}
	public void setTransactionsList(ForeignCollection<Transaction> transactionsList) {
		this.transactions = transactionsList;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public int getMonthInMM() {
		return monthInMM;
	}
	public void setMonthInMM(int monthInMM) {
		this.monthInMM = monthInMM;
	}
	public int getMonthtotalAmt() {
		return monthtotalAmt;
	}
	public void setMonthtotalAmt(int monthtotalAmt) {
		this.monthtotalAmt = monthtotalAmt;
	}
	public int getYearInYYYY() {
		return yearInYYYY;
	}
	public void setYearInYYYY(int yearInYYYY) {
		this.yearInYYYY = yearInYYYY;
	}
	
	
}
