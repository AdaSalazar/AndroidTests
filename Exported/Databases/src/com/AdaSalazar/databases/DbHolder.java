package com.AdaSalazar.databases;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHolder {
	
	public static final String KEY_ROWID = "operativeID";
	public static final String KEY_NAME = "operativeName";
	public static final String KEY_PRODUCTSTYPE = "operativeProdType";

	private static final String DATABASE_NAME= "Hypertec";
	private static final String DATABASE_TABLE= "WarehouseOperatives";
	private static final int DATABASE_VERSION= 1;
	
	//instance of our class
	private DbHelper ourHelper;
	//context of our class
	private final Context ourContext;
	//sql database class
	private SQLiteDatabase ourDatabase;
	
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL("CREATE TABLE "+ DATABASE_TABLE +" (" +
					KEY_ROWID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_NAME + " varchar(25) NOT NULL , "+
					KEY_PRODUCTSTYPE + " varchar(35) NOT NULL); "		
			);
			
			
			/*
			db.execSQL("CREATE TABLE WarehouseOperatives (" +
				" operativeID int PRIMARY KEY AUTO_INCREMENT, "+
				"operativeName varchar(25) NOT NULL , "+
				"operativeProdType varchar(35) NOT NULL); "		
			);*/
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS WarehouseOperatives");
			onCreate(db);
			
		}
		
	}

	//constructor
	public DbHolder(Context c){
		ourContext = c;
	}
	
	public DbHolder open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String name, String product) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_PRODUCTSTYPE, product);
		//Make sure that the table exists!
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iProduct = c.getColumnIndex(KEY_PRODUCTSTYPE);
		
		//this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " "+ c.getString(iName) + " " +c.getString(iProduct) + "\n";
		}
		
		return result;
	}

	public String getName(long lRowGet) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + lRowGet, null, null, null, null);
		if (c !=null){
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		
		return null;
	}

	public String getProdType(long lRowGet) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + lRowGet, null, null, null, null);
		if (c !=null){
			c.moveToFirst();
			String product = c.getString(2);
			return product;
		}
		return null;
	}

	public void updateEntry(long lRowMod, String modifyName, String modifyProduct) throws SQLException{
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, modifyName);
		cvUpdate.put(KEY_PRODUCTSTYPE, modifyProduct);
		
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRowMod, null);				
		
	}

	public void deleteEntry(long lRowDel) throws SQLException{
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRowDel, null);
	}
	
	public ArrayList<String> getNames()  throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		int iName = c.getColumnIndex(KEY_NAME);
		ArrayList<String> names = new ArrayList<String>(); 
		
		//this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			names.add(c.getString(iName)); 
		}
		return names;
	}
	
	public ArrayList<String> getID()  throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		int iRow = c.getColumnIndex(KEY_ROWID);
		
		ArrayList<String> id = new ArrayList<String>(); 
		//this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			id.add(c.getString(iRow)); 
		}
		return id;
	}
	
	public ArrayList<String> getProd()  throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		int iProduct = c.getColumnIndex(KEY_PRODUCTSTYPE);
		
		ArrayList<String> prod = new ArrayList<String>(); 
		
		//this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			prod.add(c.getString(iProduct)); 
		}
		return prod;
	}
	/*public ArrayList<String> getAll()  throws SQLException{
		String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_PRODUCTSTYPE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);

		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iProduct = c.getColumnIndex(KEY_PRODUCTSTYPE);
		
		ArrayList<String> id = new ArrayList<String>(); 
		ArrayList<String> names = new ArrayList<String>(); 
		ArrayList<String> prod = new ArrayList<String>(); 
		
		//this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			id.add(c.getString(iRow)); 
			names.add(c.getString(iName)); 
			prod.add(c.getString(iProduct)); 
		}
		return names prod id;
	}*/
}
