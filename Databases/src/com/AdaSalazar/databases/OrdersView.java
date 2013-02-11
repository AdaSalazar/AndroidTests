package com.AdaSalazar.databases;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
//import android.widget.TextView;
import android.widget.ListView;

public class OrdersView extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ordersview);
		//TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		ordersHolder info = new ordersHolder(this);
		/*info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
		
		
		 **/
		//This is the ListView element obtained by id from the xml layout
		ListView lvOrd = (ListView)findViewById(R.id.lvOrder); 		
		ListView lvDate = (ListView)findViewById(R.id.lvDate); 
		ListView lvCust = (ListView)findViewById(R.id.lvCustomer); 
		ListView lvCont = (ListView)findViewById(R.id.lvContact); 
		ListView lvDeli = (ListView)findViewById(R.id.lvDeliver); 		
		ListView lvAddr = (ListView)findViewById(R.id.lvDelivery); 

		//ListView lvInfo = (ListView)findViewById(R.id.AllInfo); 
		
		//array adapter is a class that binds data to views 
		ArrayAdapter<String> arrAdap;
		
		info.open();
		ArrayList<String> orders = info.getOrderIDs();
		ArrayList<String> dates = info.getDates();
		ArrayList<String> customers = info.getCustomers();
		ArrayList<String> contacts = info.getContacts();
		ArrayList<String> companies = info.getCompanies();
		ArrayList<String> deliveries = info.getAddresses();
		
		//ArrayList<String> allArr = info.getAll();
		
		//in here is binding a strings array to the simple_list_item_1 which is built in view (view is things you see on the screen)
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, orders);
		//here we set the array adapter (it turns it on)
		lvOrd.setAdapter(arrAdap);
		
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, dates);
		lvDate.setAdapter(arrAdap);
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, customers);
		lvCust.setAdapter(arrAdap);
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, contacts);
		lvCont.setAdapter(arrAdap);
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, companies);
		lvDeli.setAdapter(arrAdap);
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, deliveries);
		lvAddr.setAdapter(arrAdap);
		
		

		//arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, allArr);
		//lvInfo.setAdapter(arrAdap);
		info.close();


		
		//lockingscrolling
		/*lvids.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lvNames.setSelectionFromTop(firstVisibleItem, lvids.getChildAt(0).getTop());
            }
        });
		
		lvids.setOnItemSelectedListener(new OnItemSelectedListener()
		{
		   public void onItemSelected(AdapterView adapterView, View view, int position, long id){
			   lvNames.setSelection(position);
		   }
		});
		*/
		
	}

	
}
