package com.AdaSalazar.databases;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;


public class ListViewsWithTextsViews extends Activity{
	
	//This is to hold the result object
	ArrayList<Order> WebDataArr = new ArrayList<Order>();
	
	class Order{
		public String orderRowID;
		public String OrderID;
		public String OrderDate;
		public String CustOrd;
		public String OrdContact;
		public String DelCompany;
		public String DelAddress;
	}

	//This is a class var and a Adapter
	OrderAdapter fancyAdapter=null;
	
	//This is just a temporal array to hold json rows for each row returned 
	//until its used to create a Order object
	static ArrayList<String> resultRow;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		try{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.lvwithtv);
			String resultV = "";			
			
			//http post
			try{
				//this is called http lifting
				HttpClient httpclientV = new DefaultHttpClient(); //create a client object
				//HttpPost httpposting= new HttpPost("http://www.abisalazar.com/as/hypertest/index.php");//post to our url (we coluld also use get)
				HttpPost httpposting= new HttpPost("localhost/hyperapp/index.php");//post to our url (we coluld also use get)
				//execute the post and get response object
				HttpResponse responseV = httpclientV.execute(httpposting);
				//get the message from the response variable
				HttpEntity entityV = responseV.getEntity();
				//get the message's content
				InputStream websV = entityV.getContent();				
				//convert responseV to string
				try{
					BufferedReader buffReader = new BufferedReader(new InputStreamReader(websV,"iso-8859-l"),8);
					StringBuilder strBuilder = new StringBuilder();
					String lineV = null;
					
					while ((lineV = buffReader.readLine()) != null){
						strBuilder.append(lineV + "\n");
					}
					//close the inputstream
					websV.close();
					//convert the strbuilder to a string
					resultV = strBuilder.toString();
				}catch (Exception e){
					Log.e("log_tag", "Error converting the result "+e.toString());
				}
				
			}catch (Exception e){
					Log.e("log_tag", "Error in http connection "+e.toString());
			}
			
			//We have to parse the json data
			try{
				//create a new json array from the resultV string
				JSONArray jArr = new JSONArray(resultV);
				//for each object in that json array
				for(int j=0;j<jArr.length();j++){
					JSONObject jsonData = jArr.getJSONObject(j);
					//create a new order object
					Order resultRowO = new Order();
					//set that order's attributes
					resultRowO.orderRowID = jsonData.getString("orderRowID");
					resultRowO.OrderID = jsonData.getString("OrderID");
					resultRowO.OrderDate = jsonData.getString("OrderDate");
					resultRowO.CustOrd = jsonData.getString("CustOrd");
					resultRowO.OrdContact = jsonData.getString("OrdContact");
					resultRowO.DelCompany = jsonData.getString("DelCompany");
					resultRowO.DelAddress = jsonData.getString("DelAddress");
					
					//this is our array list object we add our person object to it
					WebDataArr.add(resultRowO);
				}
			}catch (Exception e){
					Log.e("log_tag", "Error parsing json data "+e.toString());
			
		}
			
			//Get the listview 
			ListView lvOrder = (ListView)findViewById(R.id.lvOrderInfo);
			
			//Initialising the fancy adapter obj as its already declared above (by the class definition)
			fancyAdapter = new OrderAdapter();
			
			//setting the adapter, this turns it on
			lvOrder.setAdapter(fancyAdapter);
				
		} catch (Exception e){
				//line of the code that send a real error message to the log
				Log.e("ERROR", "Error in code: "+ e.toString());
				//this prints out the location in the code where the error occurred
				e.printStackTrace();
			
		}
		
	}
	
	//By creating this class I extend the Array Adapter and use this to create custom views
	//and also execute more complicated functions
	class OrderAdapter extends ArrayAdapter<Order>{
		
		OrderAdapter(){
			super(ListViewsWithTextsViews.this, android.R.layout.simple_list_item_1, WebDataArr);
		}
		
		public View getView(int position, View viewToConvert, ViewGroup parent){
			ViewHolder vHolder;
			//this checks if the holder has been recycled
			//if yes then it already exists and there is not need to call the inflater function
			//this saves A LOT OF RESOURSES AND PROCESSING
			if(viewToConvert==null){
				LayoutInflater layInflater = getLayoutInflater();
				viewToConvert = layInflater.inflate(R.layout.row, null);
				
				//using this class to cache the result of the findViewById function 
				vHolder = new ViewHolder(viewToConvert);
				//Store a tag on the view
				viewToConvert.setTag(vHolder);
				
			} else {
				vHolder = (ViewHolder)viewToConvert.getTag();
			}
			
			vHolder.orderPopulator(WebDataArr.get(position));
			
			return(viewToConvert);
			
		}
		
	}
	
	class ViewHolder {
		public TextView order = null;
		public TextView date = null;
		public TextView custord = null;
		public TextView contact = null;
		public TextView delvierto = null;
		public TextView deladdress = null;
		public TextView findOrder = null;
		//public TextView order, date, custord, contact, delvierto, deladdress, findOrder;
		
		ViewHolder(View row){
			order = (TextView)row.findViewById(R.id.tvOrder);
			date = (TextView)row.findViewById(R.id.tvDate);
			custord = (TextView)row.findViewById(R.id.tvCustomer);
			contact = (TextView)row.findViewById(R.id.tvContact);
			delvierto = (TextView)row.findViewById(R.id.tvCompany);
			deladdress = (TextView)row.findViewById(R.id.tvAddress);
		}
		
		void orderPopulator(Order o){
			//retrieved information
			order.setText(o.OrderID);
			date.setText(o.OrderDate);
			custord.setText(o.CustOrd);
			contact.setText(o.OrdContact);
			delvierto.setText(o.DelCompany);
			deladdress.setText(o.DelAddress);
		}
		
	}
}
