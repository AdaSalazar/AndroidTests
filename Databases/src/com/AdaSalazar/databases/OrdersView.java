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
		ListView lvids = (ListView)findViewById(R.id.lvIds); 		
		ListView lvNames = (ListView)findViewById(R.id.lvNames); 
		ListView lvprods = (ListView)findViewById(R.id.lvProducts); 

		//ListView lvInfo = (ListView)findViewById(R.id.AllInfo); 
		
		//array adapter is a class that binds data to views 
		ArrayAdapter<String> arrAdap;
		
		info.open();
		ArrayList<String> idsArr = info.getID();
		ArrayList<String> namesArr = info.getNames();
		ArrayList<String> prodsArr = info.getProd();
		
		//ArrayList<String> allArr = info.getAll();
		
		//in here is binding a strings array to the simple_list_item_1 which is built in view (view is things you see on the screen)
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, idsArr);
		//here we set the array adapter (it turns it on)
		lvids.setAdapter(arrAdap);
		
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, namesArr);
		lvNames.setAdapter(arrAdap);
		arrAdap = new ArrayAdapter<String>(OrdersView.this, android.R.layout.simple_list_item_1, prodsArr);
		lvprods.setAdapter(arrAdap);
		
		

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
