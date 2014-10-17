package com.amadkor.expensetracker.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.amadkor.expensetracker.model.Expense;

public class ExpenseArrayAdapter extends ArrayAdapter<Expense> {

	private Context context;
	private List<Expense> values;
	public ExpenseArrayAdapter(Context context, List<Expense> values) {
		super(context, android.R.layout.simple_list_item_2);
		this.context  = context;
		this.values = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater) context
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
		}
		
		TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
		TextView text2 = (TextView) convertView.findViewById(android.R.id.text2);
		
		text1.setText(String.valueOf(values.get(position).amount));
		text2.setText(values.get(position).dateAdded.toString());
		
		return convertView;
	}
	@Override
	public void clear() {
		super.clear();
		values = new ArrayList<Expense>();
	}
	@Override
	public void add(Expense object) {
		super.add(object);
		values.add(object);
	}
	@Override
	public void addAll(Collection<? extends Expense> collection) {
		super.addAll(collection);
		values.addAll(collection);
	}
	

}
