package com.j1s.model;


public class Transaction {
	private String 	id;
	private String 	address;
	private String 	msg;
	private String 	spentAmt;
	private String 	spentOn;
	private String 	spentAt;
	
		
	public String getId() {
		return id;
	}


	public void setId(String id) {
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
	
	
	public String getSpentAmt() {
		return spentAmt;
	}


	public void setSpentAmt(String spentAmt) {
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


	@Override
	public String toString() {
		return spentOn+" | "+spentAt+" | Amt : "+spentAmt;
	}

}