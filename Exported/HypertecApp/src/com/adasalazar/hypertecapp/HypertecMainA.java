package com.adasalazar.hypertecapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class HypertecMainA extends Activity {
	String[] presidents = {
	"Dwight D. Eisenhower",
	"John F. Kennedy",
	"Lyndon B. Johnson",
	"Richard Nixon",
	"Gerald Ford",
	"Jimmy Carter",
	"Ronald Reagan",
	"George H. W. Bush",
	"Bill Clinton",
	"George W. Bush",
	"Barack Obama"
	};
		/** Called when the activity is first created. */
		@Override
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hypertec);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_dropdown_item_1line, presidents);
		AutoCompleteTextView textView = (AutoCompleteTextView)
		findViewById(R.id.actvOpName);
		textView.setThreshold(3);
		textView.setAdapter(adapter);
		}

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hypertec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hypertec, menu);
		return true;
	}*/

}
