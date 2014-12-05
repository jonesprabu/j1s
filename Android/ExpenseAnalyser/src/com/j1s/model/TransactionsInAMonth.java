package com.j1s.model;

import java.util.List;

public class TransactionsInAMonth {

	private List<Transaction> thisMonthTransactionList;
	private String thisMonthName;
	private int thisMonthInMM;
	private int thisMonthtotalAmt;
	private int thisYearInYYYY;
	
	public List<Transaction> getThisMonthTransactionList() {
		return thisMonthTransactionList;
	}
	public void setThisMonthTransactionList(List<Transaction> thisMonthTransactionList) {
		this.thisMonthTransactionList = thisMonthTransactionList;
	}
	public String getThisMonthName() {
		return thisMonthName;
	}
	public void setThisMonthName(String thisMonthName) {
		this.thisMonthName = thisMonthName;
	}
	public int getThisMonthtotalAmt() {
		return thisMonthtotalAmt;
	}
	public void setThisMonthtotalAmt(int thisMonthtotalAmt) {
		this.thisMonthtotalAmt = thisMonthtotalAmt;
	}
	public int getThisMonthInMM() {
		return thisMonthInMM;
	}
	public void setThisMonthInMM(int thisMonthInMM) {
		this.thisMonthInMM = thisMonthInMM;
	}
	public int getThisYearInYYYY() {
		return thisYearInYYYY;
	}
	public void setThisYearInYYYY(int thisYearInYYYY) {
		this.thisYearInYYYY = thisYearInYYYY;
	}	
	
}
