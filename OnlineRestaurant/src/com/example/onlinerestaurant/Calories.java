package com.example.onlinerestaurant;

import java.util.ArrayList;

import org.w3c.dom.Text;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calories extends Activity {
	Button submit;
	TextView show;
	TextView type;
	SelectionMenue myObj;
	public static double value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calories);
		submit = (Button)findViewById(R.id.Submit);
		type = (TextView) findViewById(R.id.textView2);
		String text2 = getIntent().getStringExtra(myObj.getChoice);	
		type.setText(text2);
		submit.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				value =Integer.parseInt( (String) type.getText());
				Intent intent = new Intent(Calories.this,ShowResults.class );	
				startActivity(intent);
			}
		});
		ArrayList<dbobject> q = new ArrayList<dbobject>();
		DBAdapter db = DBAdapter.getDBAdapter("mac" + ".sqlite",
				getApplicationContext());

		if (!db.checkDatabase()) {
			db.createDatabase(getApplicationContext());
		}
		db.openDatabase();
		q = db.getdatabase();
		show.setText(q.size()+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calories, menu);
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
