package com.example.onlinerestaurant;

import java.util.ArrayList;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ShowResults extends Activity {
	ArrayList<dbobject> q = new ArrayList<dbobject>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_results);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MainActivity x = new MainActivity();
		String resturant = x.getChoice;

		getMenuInflater().inflate(R.menu.show_results, menu);

		DBAdapter db = DBAdapter.getDBAdapter(resturant + ".sqlite",
				getApplicationContext());

		if (!db.checkDatabase()) {
			db.createDatabase(getApplicationContext());
		}
		db.openDatabase();
		q = db.getdatabase();
		SelectionMenue e = new SelectionMenue();
		String cond = e.getChoice;

		ArrayList<dbobject> lastdata = new ArrayList<dbobject>();

		Calories v = new Calories();
		double value = v.value;
		if (cond.equals("calo")) {
			for (int i = 0; i < q.size(); i++) {
				if (q.get(i).calories <= value) {
					lastdata.add(q.get(i));
				}
			}
		} else {
			for (int i = 0; i < q.size(); i++) {
				if (q.get(i).price <= value) {
					lastdata.add(q.get(i));
				}
			}
		}

		LinearLayout vertcal = (LinearLayout) findViewById(R.id.vertical);
		LinearLayout horizontal = (LinearLayout) findViewById(R.id.horizontal);

		for (int i = 0; i < lastdata.size(); i++) {
			TextView nameanddesc = new TextView(getApplicationContext());
			nameanddesc.setText(lastdata.get(i).name + "\n"
					+ lastdata.get(i).dexcription);
			TextView price = new TextView(getApplicationContext());
			nameanddesc.setText(lastdata.get(i).price + "");
			Button minus = new Button(this);
			minus.setText("-");
			Button plus = new Button(this);
			minus.setText("+");
			TextView cnt = new TextView(getApplicationContext());
			cnt.setText("0");
			horizontal.addView(nameanddesc);
			horizontal.addView(price);
			horizontal.addView(minus);
			horizontal.addView(cnt);
			horizontal.addView(plus);
			vertcal.addView(horizontal);
		}
		ScrollView scroller = new ScrollView(getApplicationContext());
		scroller.addView(vertcal);
		
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
