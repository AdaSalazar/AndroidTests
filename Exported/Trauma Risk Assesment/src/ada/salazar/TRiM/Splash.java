package ada.salazar.TRiM;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.view.Menu;


public class Splash extends Activity {

	MediaPlayer mpIntroSound; //This allow us to use this variable  everywhere in this class
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		
		mpIntroSound = MediaPlayer.create(this, R.raw.logo_sound);
		mpIntroSound.start();
		 
		Thread logoTimer = new Thread(){
			public void run(){
				try{
					int logoTimer = 0;
					 while(logoTimer < 3500){
						 sleep(100);//100 is a tenth of a second
						 logoTimer = logoTimer + 100;
					 }
					
				} catch(InterruptedException e){
					e.printStackTrace();
				} finally {
					//The intent has to match action name part in the Manifest.xml <intent-filter> section
					Intent openMenu = new Intent("ada.salazar.TRiM.MENU");
					//To create an Intent name is PackageName.CLASSNAME
					startActivity(openMenu); 
				}//try
				
			}//run method
		};//Thread
		logoTimer.start();
	}//On Create
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mpIntroSound.release();//Finish the music
		finish();
	}
	
	/*	 
			 
				 //1st step
				 try{
					 
					 .AReference in this case clearscreen
					 startActivity(new Intent("ada.salazar.TRiM.CLEARSCREEN"));
				 } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				 //finish the activity
				 finally{
					 finish();
				 }
			 }
		 
		 };
		 
		 logoTimer.run();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//mpIntroSound.release();
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//mpIntroSound.start();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
//	
	
	/*String stringVar = "Value of string variable";
	int intVar = 12345;
	boolean booleanVar = false;
	short smallNumber = 13;
	long longerNumbers = 234;
    @Override
    public void onCreate(Bundle bundleName) {
        super.onCreate(bundleName);
        setContentView(R.layout.activity_hello_world);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hello_world, menu);
        return true;
    }*/
	
	
}
