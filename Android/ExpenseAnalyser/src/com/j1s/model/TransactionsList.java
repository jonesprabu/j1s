package com.j1s.model;

import java.util.ArrayList;

public class TransactionsList extends ArrayList<Transaction>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float totalSpentAmt;
	
	public float getTotalSpentAmt() {
		return totalSpentAmt;
	}
	
	@Override
	public boolean add(Transaction transaction) {
		
		this.totalSpentAmt = this.totalSpentAmt+Float.parseFloat(transaction.getSpentAmt());
		
		return super.add(transaction);
		
	}
}
