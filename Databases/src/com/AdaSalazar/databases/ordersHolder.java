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

public class ordersHolder {

	private static final String DATABASE_NAME = "Hypertec";
	private static final int DATABASE_VERSION = 2;
	/* Operatives Table */
	private static final String WAREHOUSE_TABLE = "WarehouseOperatives";
	public static final String OP_ROWID = "OperativeID";
	public static final String OP_NAME = "OperativeName";
	public static final String PROD_TYPE = "OperativeProdType";
	/* Orders Table */
	private static final String ORDERS_TABLE = "Orders";
	public static final String ORDER_ROWID = "orderRowID";
	public static final String ORDER_ID = "OrderID";
	public static final String ORDER_DATE = "OrderDate";
	public static final String CUSTOMER_ORDER = "CustOrd";
	public static final String ORDER_CONTACT = "OrdContact";
	public static final String ORDER_DELCOMPANY = "DelCompany";
	public static final String ORDER_DELIVERYADD = "DelAddress";

	// instance of our class
	private DbHelper ourHelper;
	// context of our class
	private final Context ourContext;
	// sql database class
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

			db.execSQL("CREATE TABLE " + WAREHOUSE_TABLE + " (" + OP_ROWID
					+ "  INTEGER PRIMARY KEY AUTOINCREMENT, " + OP_NAME
					+ " varchar(25) NOT NULL , " + PROD_TYPE
					+ " varchar(35) NOT NULL); ");

			/* Orders Table */
			db.execSQL("CREATE TABLE " + ORDERS_TABLE + " (" + ORDER_ROWID
					+ "  INTEGER PRIMARY KEY AUTOINCREMENT, " + ORDER_ID
					+ " varchar(7) NOT NULL , " + ORDER_DATE
					+ " varchar(9) NOT NULL , " + CUSTOMER_ORDER
					+ " varchar(7) NOT NULL , " + ORDER_CONTACT
					+ " varchar(25) NOT NULL , " + ORDER_DELCOMPANY
					+ " varchar(25) NOT NULL , " + ORDER_DELIVERYADD
					+ " varchar(45) NOT NULL); ");
			/*
			 * db.execSQL("CREATE TABLE WarehouseOperatives (" +
			 * " operativeID int PRIMARY KEY AUTO_INCREMENT, "+
			 * "operativeName varchar(25) NOT NULL , "+
			 * "operativeProdType varchar(35) NOT NULL); " );
			 */

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS WarehouseOperatives");
			db.execSQL("DROP TABLE IF EXISTS Orders");
			onCreate(db);

		}

	}

	// constructor
	public ordersHolder(Context c) {
		ourContext = c;
	}

	public ordersHolder open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name, String product) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(OP_NAME, name);
		cv.put(PROD_TYPE, product);
		// Make sure that the table exists!
		return ourDatabase.insert(WAREHOUSE_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, null, null,
				null, null, null);
		String result = "";

		int iRow = c.getColumnIndex(OP_ROWID);
		int iName = c.getColumnIndex(OP_NAME);
		int iProduct = c.getColumnIndex(PROD_TYPE);

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " \t\t\t" + c.getString(iName)
					+ " \t\t\t" + c.getString(iProduct) + "\n";
		}

		return result;
	}

	public String getName(long lRowGet) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, OP_ROWID + "="
				+ lRowGet, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}

		return null;
	}

	public String getProdType(long lRowGet) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, OP_ROWID + "="
				+ lRowGet, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String product = c.getString(2);
			return product;
		}
		return null;
	}

	public void updateEntry(long lRowMod, String modifyName,
			String modifyProduct) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(OP_NAME, modifyName);
		cvUpdate.put(PROD_TYPE, modifyProduct);

		ourDatabase.update(WAREHOUSE_TABLE, cvUpdate, OP_ROWID + "=" + lRowMod,
				null);

	}

	public void deleteEntry(long lRowDel) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(WAREHOUSE_TABLE, OP_ROWID + "=" + lRowDel, null);
	}

	public ArrayList<String> getNames() throws SQLException {
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, null, null,
				null, null, null);

		int iName = c.getColumnIndex(OP_NAME);
		ArrayList<String> names = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			names.add(c.getString(iName));
		}
		return names;
	}

	public ArrayList<String> getID() throws SQLException {
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, null, null,
				null, null, null);

		int iRow = c.getColumnIndex(OP_ROWID);

		ArrayList<String> id = new ArrayList<String>();
		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			id.add(c.getString(iRow));
		}
		return id;
	}

	public ArrayList<String> getProd() throws SQLException {
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, null, null,
				null, null, null);

		int iProduct = c.getColumnIndex(PROD_TYPE);

		ArrayList<String> prod = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			prod.add(c.getString(iProduct));
		}
		return prod;
	}

	public ArrayList<String> getAll() throws SQLException {
		String[] columns = new String[] { OP_ROWID, OP_NAME, PROD_TYPE };
		Cursor c = ourDatabase.query(WAREHOUSE_TABLE, columns, null, null,
				null, null, null);

		int iRow = c.getColumnIndex(OP_ROWID);
		int iName = c.getColumnIndex(OP_NAME);
		int iProduct = c.getColumnIndex(PROD_TYPE);

		ArrayList<String> allInfo = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			allInfo.add(c.getString(iRow) + " \t\t\t " + c.getString(iName)
					+ " \t\t\t " + c.getString(iProduct));
		}
		return allInfo;
	}

	/*
	 * public ArrayList<String> getAll() throws SQLException{ String[] columns =
	 * new String[]{ OP_ROWID, OP_NAME, PROD_TYPE}; Cursor c =
	 * ourDatabase.query(WAREHOUSE_TABLE, columns, null, null, null, null,
	 * null);
	 * 
	 * int iRow = c.getColumnIndex(OP_ROWID); int iName =
	 * c.getColumnIndex(OP_NAME); int iProduct = c.getColumnIndex(PROD_TYPE);
	 * 
	 * ArrayList<String> id = new ArrayList<String>(); ArrayList<String> names =
	 * new ArrayList<String>(); ArrayList<String> prod = new
	 * ArrayList<String>();
	 * 
	 * //this will get all the info from the database for (c.moveToFirst();
	 * !c.isAfterLast(); c.moveToNext()){ id.add(c.getString(iRow));
	 * names.add(c.getString(iName)); prod.add(c.getString(iProduct)); } return
	 * names prod id; }
	 */

	/* ORDERS TABLE STUFF */

	public long createOrder(String orderID, String date, String customer,
			String contact, String company, String address) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(ORDER_ID, orderID);
		cv.put(ORDER_DATE, date);
		cv.put(CUSTOMER_ORDER, customer);
		cv.put(ORDER_CONTACT, contact);
		cv.put(ORDER_DELIVERYADD, company);
		cv.put(ORDER_DELCOMPANY, address);
		// Make sure that the table exists!
		return ourDatabase.insert(ORDERS_TABLE, null, cv);
	}

	public String getOrderID(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID
				+ " LIKE '" + searchOrder + "'", null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(2);
			return order;
		}
		return null;
	}

	public String getDate(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID + "="
				+ searchOrder, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(3);
			return order;
		}
		return null;
	}

	public String getCustomer(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID + "="
				+ searchOrder, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(4);
			return order;
		}
		return null;
	}

	public String getContact(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID + "="
				+ searchOrder, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(5);
			return order;
		}
		return null;
	}

	public String getAddress(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID + "="
				+ searchOrder, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(6);
			return order;
		}
		return null;
	}

	public String getCompany(String searchOrder) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, ORDER_ID + "="
				+ searchOrder, null, null, null, null);

		if (c != null) {
			c.moveToFirst();
			String order = c.getString(7);
			return order;
		}
		return null;
	}

	public ArrayList<String> getOrdersInfo() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELIVERYADD,
				ORDER_DELCOMPANY };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int iorderid = c.getColumnIndex(ORDER_ID);
		int idate = c.getColumnIndex(ORDER_DATE);
		int icustomer = c.getColumnIndex(CUSTOMER_ORDER);
		int icontact = c.getColumnIndex(ORDER_CONTACT);
		int iaddress = c.getColumnIndex(ORDER_DELIVERYADD);
		int icompany = c.getColumnIndex(ORDER_DELCOMPANY);

		ArrayList<String> allInfo = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			allInfo.add(c.getString(iorderid) + " \t\t\t " + c.getString(idate)
					+ " \t\t\t " + c.getString(icustomer) + " \t\t\t "
					+ c.getString(icontact) + " \t\t\t "
					+ c.getString(iaddress) + " \t\t\t "
					+ c.getString(icompany));
		}
		return allInfo;
	}

	public ArrayList<String> getOrderIDs() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int iorderid = c.getColumnIndex(ORDER_ID);

		ArrayList<String> orderids = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			orderids.add(c.getString(iorderid));
		}
		return orderids;
	}

	public ArrayList<String> getDates() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int idate = c.getColumnIndex(ORDER_DATE);

		ArrayList<String> dates = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			dates.add(c.getString(idate));
		}
		return dates;
	}

	public ArrayList<String> getCustomers() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int icustomer = c.getColumnIndex(CUSTOMER_ORDER);

		ArrayList<String> customers = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			customers.add(c.getString(icustomer));
		}
		return customers;
	}

	public ArrayList<String> getContacts() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int icontact = c.getColumnIndex(ORDER_CONTACT);

		ArrayList<String> contacts = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			contacts.add(c.getString(icontact));
		}
		return contacts;
	}

	public ArrayList<String> getAddresses() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int iaddress = c.getColumnIndex(ORDER_DELIVERYADD);

		ArrayList<String> adresses = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			adresses.add(c.getString(iaddress));
		}
		return adresses;
	}

	public ArrayList<String> getCompanies() throws SQLException {
		String[] columns = new String[] { ORDER_ROWID, ORDER_ID, ORDER_DATE,
				CUSTOMER_ORDER, ORDER_CONTACT, ORDER_DELCOMPANY,
				ORDER_DELIVERYADD };
		Cursor c = ourDatabase.query(ORDERS_TABLE, columns, null, null, null,
				null, null);

		int icompany = c.getColumnIndex(ORDER_DELCOMPANY);

		ArrayList<String> companies = new ArrayList<String>();

		// this will get all the info from the database
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			companies.add(c.getString(icompany));
		}
		return companies;
	}

	public void updateOrder(String ordid, String date, String customer,
			String contact, String addresss, String company)
			throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(ORDER_ID, ordid);
		cvUpdate.put(ORDER_DATE, date);
		cvUpdate.put(CUSTOMER_ORDER, customer);
		cvUpdate.put(ORDER_CONTACT, contact);
		cvUpdate.put(ORDER_DELIVERYADD, addresss);
		cvUpdate.put(ORDER_DELCOMPANY, company);

		ourDatabase
				.update(ORDERS_TABLE, cvUpdate, ORDER_ID + "=" + ordid, null);

	}

	public void deleteOrder(String ordid) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(ORDERS_TABLE, ORDER_ID + "=" + ordid, null);
	}
}
