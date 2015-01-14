package com.j1s.expenseanalyser.db;


import java.io.IOException;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j1s.model.MonthlyTransactions;
import com.j1s.model.Transaction;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "expenseanalyzer.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<Transaction, String> transactionDao = null;
	private Dao<MonthlyTransactions, String> monthlyTransactionsDao = null;
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		DatabaseInitializer initializer = new DatabaseInitializer(context);
		try {
			initializer.createDatabase();
			initializer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			
			TableUtils.createTable(connectionSource, Transaction.class);
			TableUtils.createTable(connectionSource, MonthlyTransactions.class);
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			
			TableUtils.dropTable(connectionSource, Transaction.class, true);
			TableUtils.dropTable(connectionSource, MonthlyTransactions.class, true);
			
			onCreate(db);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	
	public Dao<Transaction, String> getTransactionDao() throws SQLException {
		if (transactionDao == null) {
			transactionDao = getDao(Transaction.class);
		}
		return transactionDao;
	}
	
	public Dao<MonthlyTransactions, String> getMonthlyTransactionsDao() throws SQLException {
		if (monthlyTransactionsDao == null) {
			monthlyTransactionsDao = getDao(MonthlyTransactions.class);
		}
		return monthlyTransactionsDao;
	}
	
	
	
	@Override
	public void close() {
		super.close();
		transactionDao = null;
		monthlyTransactionsDao=null;
	}
}