package com.amadkor.expensetracker;


import java.util.ArrayList;
import java.util.Date;

import com.amadkor.expensetracker.helpers.ExpenseSQLiteHelper;
import com.amadkor.expensetracker.model.Expense;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ArrayList<Expense> expenses = new ArrayList<Expense>();;
	private EntriesListFragment entriesListFragment;
	private TextView lblTotal;
	private int total;
	private EditText txtAmount;
	private ExpenseSQLiteHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		 entriesListFragment= (EntriesListFragment) getFragmentManager().findFragmentById(R.id.fragment1);
		
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(txtAmount.getText().toString().isEmpty())
				{
					 Toast.makeText(MainActivity.this, "You need to enter a value first", Toast.LENGTH_LONG).show();
				}else{
					int amount = Integer.parseInt(txtAmount.getText().toString());
					
					Expense temp = new Expense();
					temp.amount = amount;
					temp.dateAdded = new Date();
					expenses.add(temp);
					entriesListFragment.addExpense(temp);
					dbHelper.addExpense(temp);
					refreshSum();
					txtAmount.setText("");
				}
				
				
			}
		});
		
		
		lblTotal = (TextView)findViewById(R.id.lblTotal);
		
		txtAmount = (EditText)findViewById(R.id.txtAmount);
		
		dbHelper = new ExpenseSQLiteHelper(this);
		dbHelper.open();
		expenses = dbHelper.allExpenses();
		entriesListFragment.reloadData(expenses);
		refreshSum();
		
	}
	
	private void refreshSum(){
		total=0;
		for(Expense e: expenses){
			total+=e.amount;
		}
		lblTotal.setText(String.valueOf(total));
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
}
