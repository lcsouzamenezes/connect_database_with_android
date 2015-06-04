package com.example.onlinerestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	
	
	Button mac;
	Button cookDoor;
	public static String getChoice="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mac=(Button) findViewById(R.id.Mac);
        cookDoor =(Button)findViewById(R.id.cookdoor);
       
        mac.setOnClickListener(new View.OnClickListener() {
        	 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1=new Intent(MainActivity.this,SelectionMenue.class);
				
				getChoice="mac";
				intent1.putExtra("mac", mac.getText().toString());
				startActivity(intent1);
				
			}
		});
        
        cookDoor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1=new Intent(MainActivity.this,SelectionMenue.class);
				getChoice="cookdoor";
				intent1.putExtra("cookdoor",cookDoor.getText().toString());
				startActivity(intent1);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
