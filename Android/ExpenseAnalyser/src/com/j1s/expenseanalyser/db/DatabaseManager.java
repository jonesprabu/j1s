package com.j1s.expenseanalyser.db;

import java.sql.SQLException;
import java.util.List;

import com.j1s.model.MonthlyTransactions;
import com.j1s.model.Transaction;
import com.j1s.model.TransactionsList;
import com.j256.ormlite.stmt.PreparedQuery;

import android.content.Context;


public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context ctx) {
		if (null==instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;
	private DatabaseManager(Context ctx) {
		helper = new DatabaseHelper(ctx);
	}

	private DatabaseHelper getHelper() {
		return helper;
	}

	public List<Transaction> getAllTransactions() {
		List<Transaction> transactionsList = null;
		try {
			transactionsList = getHelper().getTransactionDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionsList;
	}

	public List<Transaction> getMonthlyTransactionsForMonthAndYear(int month, int year) {
		MonthlyTransactions mt = null;
		try {
			PreparedQuery<List<Transaction>> pq;
			mt = getHelper().getMonthlyTransactionsDao().queryForFirst(pq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishList;
	}
	

	public WishItem getWishItemWithId(int wishItemId) {
		WishItem wishList = null;
		try {
			wishList = getHelper().getWishItemDao().queryForId(wishItemId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishList;
	}

	public WishItem newWishItem() {
		WishItem wishItem = new WishItem();
		try {
			getHelper().getWishItemDao().create(wishItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishItem;
	}

	public void deleteWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().delete(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteWishItem(WishItem wishItem) {
		try {
			getHelper().getWishItemDao().delete(wishItem);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void updateWishItem(WishItem item) {
		try {
			getHelper().getWishItemDao().update(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void refreshWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().refresh(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateWishList(WishList wishList) {
		try {
			getHelper().getWishListDao().update(wishList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}