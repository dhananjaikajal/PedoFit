package com.dj.pedofit;

import java.util.ArrayList;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	ArrayList<String> date = new ArrayList<String>();
	ArrayList<String> steps = new ArrayList<String>();
	
	Context context;
	LayoutInflater inflater;
	
	public ListViewAdapter(Context context, ArrayList<String> date,
			ArrayList<String> steps) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.date = date;
		this.steps = steps;
		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return date.size();
	}
	

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.list, null);

		TextView tvName = (TextView) v.findViewById(R.id.date);
		TextView tvUsername = (TextView) v.findViewById(R.id.steps);
		
		tvName.setText(date.get(position));
		tvUsername.setText(steps.get(position));

		return v;
	}

}
