package com.j1s.expenseanalyser;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony.Sms;
import android.text.format.DateFormat;
import android.util.Log;

import com.j1s.model.Transaction;

public class SMSAnalyzer {

	private Uri uriSMSURI = Sms.Inbox.CONTENT_URI;//Uri.parse("content://sms/inbox");

	private ContentResolver contentResolver;
	
	public SMSAnalyzer(ContentResolver contentResolver) {
		this.contentResolver = contentResolver;
	}
	
	public List<Transaction> readTransactions(Map<String, Map<String, String>> msgSplitterMap, String[] whereConditionVal) throws Exception{
		List<Transaction> transactionList = new ArrayList<Transaction>();
		String smsStr;
		String smsMsg;
		Map<String, String> spentAmtKey = msgSplitterMap.get("spentAmtKey");
		Map<String, String> spentOnKey = msgSplitterMap.get("spentOnKey");
		Map<String, String> spentAtKey = msgSplitterMap.get("spentAtKey");
		String spentAmt;
		String spentOn;
		String spentAt;

		int startTime = (int) System.currentTimeMillis();
		
		Log.i("JonesInfo", "Start read SMS");
		String[] columnsToFetch = new String[]{Sms.ADDRESS, Sms.BODY, Sms.DATE};
		String whereCondition = Sms.ADDRESS+" =  ? ";//AND date("+Sms.DATE+") >= ? AND date("+Sms.DATE+") <= ?";
		//String whereCondition = Sms.ADDRESS+" = ? strftime('%s', "+Sms.DATE+") BETWEEN strftime('%s', ?) AND strftime('%s', ?)";

	    Cursor cur = contentResolver.query(uriSMSURI, columnsToFetch, whereCondition, whereConditionVal, null);

	    while (cur.moveToNext()) {
	    	
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss.SSS");
	    	Calendar calendar = Calendar.getInstance();
	    	long now = cur.getLong(2);
	    	calendar.setTimeInMillis(now);
	    	Log.i("time", "date time - "+formatter.format(calendar.getTime()));
	    	
	    	smsStr = "From :" + cur.getString(0) + " : " + cur.getString(1)+ " : " + cur.getLong(2)+"\n";    
	    	Log.i("JonesInfo" ,"SMS String - "+smsStr);
	    	
	    	smsMsg = cur.getString(1);

	    	spentAmt = StringUtils.substringBetween(smsMsg, spentAmtKey.get("after"), spentAmtKey.get("before"));
	    	spentOn  = StringUtils.substringBetween(smsMsg, spentOnKey.get("after"), spentOnKey.get("before"));
	    	//spentOn  = (String) DateFormat.format("dd-MM-yyyy", new Date(cur.getString(2)));
	    	spentAt  = StringUtils.substringBetween(smsMsg, spentAtKey.get("after"), spentAtKey.get("before"));

	    	
	    	if(spentAmt != null && spentOn != null && spentAt!= null) {
	    		Log.i("JonesInfo" ,"Splitted - spentAmt : "+spentAmt);
		    	Log.i("JonesInfo" ,"Splitted - spentOn  : "+spentOn);
		    	Log.i("JonesInfo" ,"Splitted - spentAt  : "+spentAt);
		    	
		    	Transaction transaction = new Transaction();
		    	transaction.setAddress(cur.getString(0));
		    	transaction.setSpentAmt(spentAmt);
		    	transaction.setSpentOn(spentOn);  	
		    	transaction.setSpentAt(spentAt);
		    	transaction.setMsg(smsMsg);
		    	//transaction.setDime(cur.getString(2));
		    	transactionList.add(transaction);
	    	}
		    	
	    }
	    cur.close();
	    Log.i("JonesInfo", "End read SMS -- MilliSec : "+(System.currentTimeMillis() - startTime));
	    return transactionList;		
	}
}
