package com.amadkor.expensetracker.helpers;

import java.util.ArrayList;

import com.amadkor.expensetracker.model.Expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExpenseSQLiteHelper extends SQLiteOpenHelper {

	private final String TABLE_EXPENSES="expenses";
	private final String COLUMN_ID = "_id";
	private final String COLUMN_AMOUNT= "amount";
	private final String COLUMN_DATE= "date";
	private final static String DATABASE_NAME = "expenses.db";
	private final static int DATABASE_VERSION = 2;
	
	private SQLiteDatabase database;
	
	
	private final String DATABASE_CREATE = "create table "
      + TABLE_EXPENSES+ 
      "(" + COLUMN_ID+ " integer primary key autoincrement, " 
      + COLUMN_AMOUNT+ " integer not null, "
      + COLUMN_DATE+ " integer not null);";
	
	public void open(){
		database = this.getReadableDatabase();
	}
	@Override
	public void close(){
		this.close();
	}
	
	public Expense addExpense(Expense expense){
		ContentValues values = new ContentValues();
		values.put(COLUMN_AMOUNT, expense.amount);
		values.put(COLUMN_DATE, expense.dateAdded.getTime());
		expense.id = database.insert(TABLE_EXPENSES, null, values);
		return expense;
	}
	
	public ArrayList<Expense> allExpenses(){
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		
		String query = "SELECT * FROM "+TABLE_EXPENSES;
		Cursor cursor = database.rawQuery(query, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Expense temp = new Expense();
			temp.id = cursor.getLong(0);
			temp.amount = cursor.getInt(1);
			
			temp.dateAdded = new java.util.Date(cursor.getLong(2));
			expenses.add(temp);
			cursor.moveToNext();
		}
		return expenses;
	}
	  
	public ExpenseSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL(DATABASE_CREATE);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ExpenseSQLiteHelper.class.getName(),
				"Upgrading database from version, will delete all data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
		onCreate(db);
	}
	
	

	

}
