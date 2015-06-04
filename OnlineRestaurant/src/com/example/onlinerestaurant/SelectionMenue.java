package com.example.onlinerestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectionMenue extends Activity {
	TextView text;
	TextView text1;
	MainActivity object=new MainActivity();
	
	Button calo;
	Button budget;
	Button order;
	public static String getChoice="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_menue);
		text = (TextView) findViewById(R.id.textView1);
		text1= (TextView) findViewById(R.id.textView2);
		String text2 = getIntent().getStringExtra(object.getChoice);
		text.setText(text2);
		text1.setText("Select a categorie");
		calo=(Button)findViewById(R.id.Submit);
		budget=(Button)findViewById(R.id.button2);
		order=(Button)findViewById(R.id.button3);
		calo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectionMenue.this, Calories.class);
				getChoice = "calo";
				intent.putExtra("calo", calo.getText().toString());
				startActivity(intent);

				
			}
		});
		budget.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectionMenue.this, Calories.class);
				getChoice = "budget";
				intent.putExtra("budget", budget.getText().toString());
				startActivity(intent);
			}
		});
		
		order.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectionMenue.this, Order.class);
				startActivity(intent);
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection_menue, menu);
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
