package com.j1s.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="transaction")
public class Transaction {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private String 	address;
	@DatabaseField
	private String 	msg;
	@DatabaseField
	private int 	spentAmt;
	@DatabaseField
	private String 	spentOn;
	@DatabaseField
	private String 	spentAt;
	@DatabaseField
	private int 	dd;
	@DatabaseField
	private int 	mm;
	@DatabaseField
	private int 	yyyy;
	@DatabaseField
	private long 	date;
			
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;	
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public int getSpentAmt() {
		return spentAmt;
	}


	public void setSpentAmt(int spentAmt) {
		this.spentAmt = spentAmt;
	}


	public String getSpentOn() {
		return spentOn;
	}


	public void setSpentOn(String spentOn) {
		this.spentOn = spentOn;
	}


	public String getSpentAt() {
		return spentAt;
	}


	public void setSpentAt(String spentAt) {
		this.spentAt = spentAt;
	}


	public int getDd() {
		return dd;
	}


	public void setDd(int dd) {
		this.dd = dd;
	}


	public int getMm() {
		return mm;
	}


	public void setMm(int mm) {
		this.mm = mm;
	}


	public int getYyyy() {
		return yyyy;
	}


	public void setYyyy(int yyyy) {
		this.yyyy = yyyy;
	}


	public long getDate() {
		return date;
	}


	public void setDate(long date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return spentOn+" | "+spentAt+" | Amt : "+spentAmt;
	}

}