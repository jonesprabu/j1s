package com.j1s.expenseanalyser;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.j1s.model.Transaction;
import com.j1s.model.TransactionsList;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public void readSMS(View v){
		try{
			SMSAnalyzer smsAnalyzer  = new SMSAnalyzer(getContentResolver());
			String smsAddress = "VM-Citibk";
			//String dateFrom = "2014-10-01";
			//String dateTo = "2014-10-30";
			
			//SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar dateFrom = Calendar.getInstance();
			dateFrom.set(2014, Calendar.NOVEMBER, 23);
			
			Calendar dateTo = Calendar.getInstance();
			dateTo.set(2014, Calendar.DECEMBER, 23);
			
			String[] whereCondtionVal = {smsAddress, ""+dateFrom.getTimeInMillis(), ""+dateTo.getTimeInMillis()};
			
			Map<String, Map<String, String>> msgSpliterKeys = new HashMap<String, Map<String, String>>();
			
			Map<String, String> spentAmtBeforeAter = new HashMap<String, String>();
			spentAmtBeforeAter.put("after", "Rs ");
			spentAmtBeforeAter.put("before", " was");
			
			Map<String, String> spentOnBeforeAter = new HashMap<String, String>();
			spentOnBeforeAter.put("after", "0 on ");
			spentOnBeforeAter.put("before", " at");
			
			Map<String, String> spentAtBeforeAter = new HashMap<String, String>();
			spentAtBeforeAter.put("after", "at ");
			spentAtBeforeAter.put("before", ".");
				
			msgSpliterKeys.put("spentAmtKey", spentAmtBeforeAter);
			msgSpliterKeys.put("spentOnKey", spentOnBeforeAter);
			msgSpliterKeys.put("spentAtKey", spentAtBeforeAter);
			
			TransactionsList transactionsList  =  smsAnalyzer.readTransactions(msgSpliterKeys, whereCondtionVal);
			//showTransactionInMonthView(swipeList);
			showTransactionInListView(transactionsList);
		} catch(Exception e){
			Log.i("Exception", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void showTransactionInMonthView(TransactionsList transactionsList) throws Exception{
		ListView listView = (ListView) findViewById(R.id.showSMSList);
		
		ArrayAdapter<Transaction> arrayAdapter = new ArrayAdapter<Transaction>(this,
	              android.R.layout.two_line_list_item, android.R.id.text1, transactionsList);
		
		listView.setAdapter(arrayAdapter);

	}
	
	public void showTransactionInListView(TransactionsList transactionsList) throws Exception{
		ListView listView = (ListView) findViewById(R.id.showSMSList);
		
		ArrayAdapter<Transaction> arrayAdapter = new ArrayAdapter<Transaction>(this,
	              android.R.layout.two_line_list_item, android.R.id.text1, transactionsList);
		
		listView.setAdapter(arrayAdapter);
		Log.i("JonesInfo" ,"Total Amt : " + transactionsList.getTotalSpentAmt());
		TextView headerView  = new TextView(this);
		headerView.setText("Total Amt : " + transactionsList.getTotalSpentAmt());
		listView.addHeaderView(headerView);
		Log.i("JonesInfo" ,"<<<<<<<<<<<<<<<THE END>>>>>>>>>>>>>>>>>>>>");
			
	}

}
