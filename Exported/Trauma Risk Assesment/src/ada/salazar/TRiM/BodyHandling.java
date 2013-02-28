package ada.salazar.TRiM;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout; 
import android.view.View.OnClickListener;  


public class BodyHandling extends Activity {
	//search
	WebView wvBook; 
	private LinearLayout container;  
	private Button nextButton, closeButton;  
	private EditText findBox;  
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		
		// WebView webview = new WebView(this);
		// setContentView(webview);
		// webview.loadUrl("file:///android_asset/webpages/webpage2.html");

		setContentView(R.layout.books);
		final WebView wvBook = (WebView) findViewById(R.id.wvBook);
		wvBook.getSettings().setBuiltInZoomControls(true);

		try {
			wvBook.loadUrl("file:///android_asset/webpages/body_handling.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Button bBack = (Button) findViewById(R.id.idbback);
		bBack.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// This goes back by finishing the current layout
				finish();
			}
		});

		// Set an EditText view to get user input
		/*final EditText searchText = (EditText) findViewById(R.id.etToFind);

		final Button bFind = (Button) findViewById(R.id.idbSearch);
		bFind.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Searches for the text in the web view
				// searchWV(wvBook, searchText);
			}
		});*/

		/*
		 * Working
		 * 
		 * //check if the enter key is clicked searchText.setOnKeyListener(new
		 * View.OnKeyListener() {
		 * 
		 * public boolean onKey(View v, int keyCode, KeyEvent event) { if
		 * ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode ==
		 * KeyEvent.KEYCODE_ENTER)) {
		 * 
		 * //This makes the search box have only one line
		 * searchText.setSingleLine(true); //Searches for the text in the web
		 * view searchWV(wvBook, searchText);
		 * 
		 * bFind.requestFocus(); } return false; }
		 * 
		 * 
		 * });
		 */

		/*
		 * searchText.setOnEditorActionListener(new
		 * TextView.OnEditorActionListener() {
		 * 
		 * @Override public boolean onEditorAction(TextView v, int actionId,
		 * KeyEvent event) { if (actionId == EditorInfo.IME_ACTION_SEARCH) {
		 * performSearch();
		 * searchText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		 * searchText.setImeOptions(EditorInfo.IME_ACTION_DONE); return true; }
		 * return false; } });
		 */

	}// oncreate

	
	 private static final int SEARCH_MENU_ID = Menu.FIRST;  
	    
	    @Override  
	    public boolean onCreateOptionsMenu(Menu menu){  
	    super.onCreateOptionsMenu(menu);  
	      
	    menu.add(0, SEARCH_MENU_ID, 0, "Search");  
	      
	    return true;  
	    }  
	    
	    public boolean onPrepareOptionsMenu(Menu menu){  
	    super.onPrepareOptionsMenu(menu);  
	    return true;  
	    }  
	    
	    public boolean onOptionsItemSelected(MenuItem item){  
	    switch(item.getItemId()){  
	    case SEARCH_MENU_ID:  
	    search();  
	    return true;  
	    }  
	    return true;  
	    }  
	    
	    public void search(){  
	    container = (LinearLayout)findViewById(R.id.layoutId);  
	      
	    nextButton = new Button(this);  
	    nextButton.setText("Next");  
	    nextButton.setOnClickListener(new OnClickListener(){  
	
	    	//@Override  
	public void onClick(View v){  
	wvBook.findNext(true);  
	}  
	});  
	    container.addView(nextButton);  
	      
	    closeButton = new Button(this);  
	    closeButton.setText("Close");  
	    closeButton.setOnClickListener(new OnClickListener(){  
	//@Override  
	public void onClick(View v){  
	container.removeAllViews();  
	  
	}  
	});  
	    container.addView(closeButton);  
	      
	    findBox = new EditText(this);  
	findBox.setMinEms(30);  
	findBox.setSingleLine(true);  
	findBox.setHint("Search");  
	  
	findBox.setOnKeyListener(new OnKeyListener(){  
	public boolean onKey(View v, int keyCode, KeyEvent event){  
	if((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode == KeyEvent.KEYCODE_ENTER))){  
	wvBook.findAll(findBox.getText().toString());  
	  
	try{  
	Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);  
	m.invoke(wvBook, true);  
	}catch(Exception ignored){}  
	}  
	return false;  
	}  
	});  
	  
	container.addView(findBox);  
	    }   
	
	
	
	
	
	
	
	
	

	// searches the web view for the text in editable text
	public void searchWV(WebView wbToFind, EditText etToFind) {

		// This gets the text from the edit text field in the form books
		String toFind = etToFind.getText().toString();
		// This search the word(s)
		wbToFind.findAll(toFind);
		try {
			Method m = WebView.class.getMethod("setFindIsUp", Boolean.TYPE);
			m.invoke(wbToFind, true);
		}// catch any errors
		catch (Throwable ignored) {
			Log.i("Error", ignored.toString());
		}
	}

}// class SecondBook
