package com.AdaSalazar.databases;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DropDown extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlProduct, sqlRow;
	private boolean didItWork = false;

				

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		// Buttons
		sqlUpdate = (Button) findViewById(R.id.bUpdateSQL);
		sqlView = (Button) findViewById(R.id.bViewSQL);
		sqlGetInfo = (Button) findViewById(R.id.bGet);
		
		sqlModify = (Button) findViewById(R.id.bEdit);
		sqlDelete = (Button) findViewById(R.id.bDelete);

		// Edit texts
		sqlName = (EditText) findViewById(R.id.actvName);
		sqlProduct = (EditText) findViewById(R.id.etProduct);
		sqlRow = (EditText) findViewById(R.id.etRowId);

		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		
		ordersHolder autoComplete = new ordersHolder(DropDown.this);
		//ALWAYS OPEN AND CLOSE THE HOLDER!!!!!!!!!
		autoComplete.open();
		ArrayList<String> operativesNames = autoComplete.getNames();
		autoComplete.close();

		//this auto completes the form
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, operativesNames);
				AutoCompleteTextView textView = (AutoCompleteTextView)
				findViewById(R.id.actvName);
				textView.setThreshold(2);
				textView.setAdapter(adapter);
		}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		
		case R.id.bUpdateSQL:
			String name = sqlName.getText().toString();
			String product = sqlProduct.getText().toString();

			didItWork = true;
			try {
				ordersHolder entry = new ordersHolder(DropDown.this);
				entry.open();
				entry.createEntry(name, product);
				entry.close();

			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yeah!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
			
		case R.id.bViewSQL:
			Intent i = new Intent("android.intent.action.SQLVIEW");
			startActivity(i);
			break;

		case R.id.bGet:
			try {
				String sRowGet = sqlRow.getText().toString();
				// converts the text into a long (number) variable
				long lRowGet = Long.parseLong(sRowGet);
				
				ordersHolder dbH = new ordersHolder(this);
				dbH.open();
				String returnedName = dbH.getName(lRowGet);
				String returnedProdType = dbH.getProdType(lRowGet);
				dbH.close();

				sqlName.setText(returnedName);
				sqlProduct.setText(returnedProdType);
			
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error while retriving data!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} 
			break;

		case R.id.bEdit:
			didItWork = true;
			try {
				String modifyName = sqlName.getText().toString();
				String modifyProduct = sqlProduct.getText().toString();
				String sRowMod = sqlRow.getText().toString();
				// converts the text into a long (number) variable
				long lRowMod = Long.parseLong(sRowMod);

				ordersHolder editDb = new ordersHolder(this);
				editDb.open();
				editDb.updateEntry(lRowMod, modifyName, modifyProduct);
				editDb.close();
				
			} catch (Exception e) {
				
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error while updating data!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Record Modified");
					TextView tv = new TextView(this);
					tv.setText("Successfully!");
					d.setContentView(tv);
					d.show();
				}
			}

			break;

		case R.id.bDelete:
			didItWork = true;
			try {
				String sRowDel = sqlRow.getText().toString();
				// converts the text into a long (number) variable
				long lRowDel = Long.parseLong(sRowDel);
				ordersHolder delRDb = new ordersHolder(this);
				delRDb.open();
				delRDb.deleteEntry(lRowDel);
				delRDb.close();
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error while deleting data!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Record Deleted ");
					TextView tv = new TextView(this);
					tv.setText("Successfully!");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		}

	}

	/*
	 * db.open(); long id = db.insertContact("Wei-Meng Lee",
	 * "weimenglee@learn2develop.net"); id = db.insertContact("Mary Jackson",
	 * "mary@jackson.com"); db.close();
	 */
}
