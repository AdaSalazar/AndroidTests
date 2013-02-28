package com.AdaSalazar.databases;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HypertecView extends Activity implements OnClickListener {

	Button bFindOrder, bDisplayOrders;
	AutoCompleteTextView acOrder, acOpName;
	private boolean didItWork = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hypertec);
		// Buttons
		bFindOrder = (Button) findViewById(R.id.bFindOrder);
		bDisplayOrders = (Button) findViewById(R.id.bDisplayOrders);

		// Auto Complete
		acOrder = (AutoCompleteTextView) findViewById(R.id.actvorder);
		acOpName = (AutoCompleteTextView) findViewById(R.id.actvOpName);

		bFindOrder.setOnClickListener(this);
		bDisplayOrders.setOnClickListener(this);

		ordersHolder acOrderHolder = new ordersHolder(HypertecView.this);
		// ALWAYS OPEN AND CLOSE THE HOLDER!!!!!!!!!
		acOrderHolder.open();
		ArrayList<String> orders = acOrderHolder.getOrderIDs();
		ArrayList<String> operativesNames = acOrderHolder.getNames();
		acOrderHolder.close();

		// this auto completes the names
		ArrayAdapter<String> opNameAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, operativesNames);
		AutoCompleteTextView tvName = (AutoCompleteTextView) findViewById(R.id.actvOpName);
		tvName.setThreshold(2);
		tvName.setAdapter(opNameAdapter);

		// this auto completes the orders
		ArrayAdapter<String> orderAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, orders);
		AutoCompleteTextView tvOrders = (AutoCompleteTextView) findViewById(R.id.actvorder);
		tvOrders.setThreshold(2);
		tvOrders.setAdapter(orderAdapter);

	}

	/*
	 * ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this,
	 * android.R.layout.simple_dropdown_item_1line, datesx);
	 * AutoCompleteTextView tvDate = (AutoCompleteTextView)
	 * findViewById(R.id.actvdate); tvDate.setThreshold(2);
	 * tvDate.setAdapter(dateAdapter); } ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
	 * orders); AutoCompleteTextView textView = (AutoCompleteTextView)
	 * findViewById(R.id.actvorder); textView.setThreshold(2);
	 * textView.setAdapter(adapter); } ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
	 * orders); AutoCompleteTextView textView = (AutoCompleteTextView)
	 * findViewById(R.id.actvorder); textView.setThreshold(2);
	 * textView.setAdapter(adapter); } ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
	 * orders); AutoCompleteTextView textView = (AutoCompleteTextView)
	 * findViewById(R.id.actvorder); textView.setThreshold(2);
	 * textView.setAdapter(adapter); } ArrayAdapter<String> adapter = new
	 * ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
	 * orders); AutoCompleteTextView textView = (AutoCompleteTextView)
	 * findViewById(R.id.actvorder); textView.setThreshold(2);
	 * textView.setAdapter(adapter); }
	 */

	@Override
	public void onClick(View arg0) {
		
		// change this as it is still referencing the  old xml!!

		switch (arg0.getId()) {

		case R.id.bUpdateSQL:
			String orderStr = order.getText().toString();
			String dateStr = date.getText().toString();
			String custordStr = custord.getText().toString();
			String contactStr = contact.getText().toString();
			String delivertoStr = delvierto.getText().toString();
			String deladdressStr = deladdress.getText().toString();

			didItWork = true;
			try {
				ordersHolder entry = new ordersHolder(HypertecView.this);
				entry.open();
				entry.createOrder(orderStr, dateStr, custordStr, contactStr,
						delivertoStr, deladdressStr);
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
			Intent i = new Intent("android.intent.action.ORDERSVIEW");
			startActivity(i);
			break;

		case R.id.bGet:
			try {
				String getO = findOrder.getText().toString();

				/*
				 * To test Stuff Toast toast =
				 * Toast.makeText(getApplicationContext(), "TextToShow",
				 * Toast.LENGTH_SHORT); toast.show();
				 */
				ordersHolder dbH = new ordersHolder(this);
				dbH.open();
				String returnedID = dbH.getOrderID(getO);
				String returnedDate = dbH.getDate(getO);
				String returnedCust = dbH.getCustomer(getO);
				String returnedCont = dbH.getContact(getO);
				String returnedComp = dbH.getCompany(getO);
				String returnedAddr = dbH.getAddress(getO);
				dbH.close();

				order.setText(returnedID);
				date.setText(returnedDate);
				custord.setText(returnedCust);
				contact.setText(returnedCont);
				delvierto.setText(returnedComp);
				deladdress.setText(returnedAddr);

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
				String modID = order.getText().toString();
				String modDate = date.getText().toString();
				String modCust = custord.getText().toString();
				String modCont = contact.getText().toString();
				String modComp = delvierto.getText().toString();
				String modAddr = deladdress.getText().toString();

				ordersHolder editDb = new ordersHolder(this);
				editDb.open();
				editDb.updateOrder(modID, modDate, modCust, modCont, modComp,
						modAddr);
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
				String delID = order.getText().toString();
				ordersHolder delRDb = new ordersHolder(this);
				delRDb.open();
				delRDb.deleteOrder(delID);
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
