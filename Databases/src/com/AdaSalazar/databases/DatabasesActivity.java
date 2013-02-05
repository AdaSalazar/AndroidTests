package com.AdaSalazar.databases;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.database.Cursor;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabasesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		
		DBAdapter db = new DBAdapter(this);
		
		try {
			String destPath = "/data/data/" + getPackageName() +
			"/databases";
			File f = new File(destPath);
			if (!f.exists()) {
				f.mkdirs();
				f.createNewFile();
				//---copy the db from the assets folder into
				// the databases folder---
				CopyDB(getBaseContext().getAssets().open("adadb"),
				new FileOutputStream(destPath + "/MyDB"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			//---get all contacts---
			db.open();
			Cursor c = db.getAllContacts();
		if (c.moveToFirst())
		{
			do {
				DisplayContact(c);
			} while (c.moveToNext());
		}
			db.close();
	}
	public void CopyDB(InputStream inputStream,
	OutputStream outputStream) throws IOException {
		//---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
		outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}
		
	
		/*
		//---add a contact---
		db.open();
		long id = db.insertContact("Wei-Meng Lee", "weimenglee@learn2develop.net");
		id = db.insertContact("Mary Jackson", "mary@jackson.com");
		db.close();
		}
		*/

		//---get all contacts---
		/*db.open();
		Cursor c = db.getAllContacts();
		if (c.moveToFirst())
		{
			do {
				DisplayContact(c);
			} while (c.moveToNext());
		}
			db.close();
		}
	*/
		public void DisplayContact(Cursor c)
		{
			Toast.makeText(this,
			"id: " + c.getString(0) + "\n" +
			"Name: " + c.getString(1) + "\n" +
			"Email: " + c.getString(2),
			Toast.LENGTH_LONG).show();
		}
		

		//---get a contact---
		/*db.open();
		Cursor c = db.getContact(2);
		if (c.moveToFirst())
		DisplayContact(c);
		else
		Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
		db.close();*/
		
		
		//---update contact---
		/*db.open();
		if (db.updateContact(1, "asdf", "asdf@gmail.com"))
		Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
		else
		Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
*/
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_databases, menu);
		return true;
	}

}
