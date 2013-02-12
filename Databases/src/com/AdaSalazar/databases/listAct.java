package com.AdaSalazar.databases;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listAct extends ListActivity {
	
	/**/
	String classes[] = {"Warehouse","Orders","ListView"};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub  ,"","","",""
		super.onCreate(savedInstanceState);	
									//context is the class, int is a single list item within our list, then the array
		setListAdapter(new ArrayAdapter<String>(listAct.this, android.R.layout.simple_list_item_1, classes));
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		
		super.onListItemClick(l, v, position, id);
		String arrayItemSelector = classes[position];
		
		
		Class ourClass;
		try {
			//this creates a class variable for the string name
										//the path of the class and the name
			ourClass = Class.forName("com.AdaSalazar.databases." + arrayItemSelector);			
			//this intent needs the Context is This class and a Class 
			Intent ourIntent = new Intent(listAct.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
