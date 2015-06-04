package com.example.onlinerestaurant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {

	static String name = "";
	static String path = "";
	static ArrayList<dbobject> a;
	static SQLiteDatabase sdb;

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	private DBAdapter(Context v) {
		super(v, name, null, 1);
		path = "/data/data/" + v.getApplicationContext().getPackageName()
				+ "/databases";
	}

	private DBAdapter(String n, Context v) {
		super(v, n, null, 1);
		name = n;
		path = "/data/data/" + v.getApplicationContext().getPackageName()
				+ "/databases";
	}

	public boolean checkDatabase() {
		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (db == null) {
			return false;
		} else {
			db.close();
			return true;
		}
	}

	public static synchronized DBAdapter getDBAdapter(String x,Context v) {
		return (new DBAdapter(x,v));
	}

	public void createDatabase(Context v) {
		this.getReadableDatabase();
		try {
			InputStream myInput = v.getAssets().open(name);
			// Path to the just created empty db
			String outFileName = path + "/" + name;
			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void openDatabase() {
		try {
			sdb = SQLiteDatabase.openDatabase(path + "/" + name, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Method for fetching Data and storing it into ArrayList
	public ArrayList<dbobject> getdatabase() {
		// question is the table name, you can also use "SELECT * FROM question"
		// in case
		Cursor c1 = sdb.rawQuery("SELECT * FROM question ORDER BY RANDOM()",
				null);
		a = new ArrayList<dbobject>();
		while (c1.moveToNext()) {
			dbobject q1 = new dbobject(); // kbc is the getter setter class
			// the below code can vary upon ur getter-setter methods & variables
			q1.name = c1.getString(0);
			q1.price = c1.getFloat(1);
			q1.calories = c1.getFloat(2);
			q1.dexcription = c1.getString(3);
			a.add(q1);// --Always remember to add the fetched elements of type
						// ArrayList<GetterGetter>
		}
		return a;
	}

	public void emptydb() {
		sdb.delete(name, null, null);
	}

	public long insertrow(dbobject x) {

		ContentValues initialValues = new ContentValues();
		initialValues.put("Name", x.name);
		initialValues.put("Price", x.price);
		initialValues.put("Calories", x.calories);
		initialValues.put("Description", x.dexcription);

		return sdb.insert(name, null, initialValues);
	}

}
