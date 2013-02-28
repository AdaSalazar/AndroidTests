package com.AdaSalazar.databases;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		ordersHolder info = new ordersHolder(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
		
		/*
		 * 		ArrayList<String> datax = info.test();
		String text = null;
		for(String value: datax) {
	        // print the value
	         text = text + " " + value + " " ;
	    }
		info.close();
		tv.setText(text);
		 */
	}

	
}
