package com.amadkor.expensetracker;

import java.util.ArrayList;
import com.amadkor.expensetracker.helpers.ExpenseArrayAdapter;
//import com.amadkor.expensetracker.helpers.ExpenseArrayAdapter;
import com.amadkor.expensetracker.model.Expense;

import android.app.ListFragment;
import android.os.Bundle;

public class EntriesListFragment extends ListFragment {
	ExpenseArrayAdapter adapter;
	
	
	public void reloadData(ArrayList<Expense> expenses){
		if(adapter==null){
			adapter= new ExpenseArrayAdapter(getActivity(), expenses); 

		}
		adapter.clear();
		adapter.addAll(expenses);
	}
	public void addExpense(Expense entry){
		adapter.add(entry);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		adapter= new ExpenseArrayAdapter(getActivity(), expenses); 
		setListAdapter(adapter);
	}
}
