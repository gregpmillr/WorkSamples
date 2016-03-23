package com.example.greg.trailerlaptop;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBAdapter {

	//simple tag to get ahold of DBAdapter name
	public static final String TAG = "DBAdapter";

	//database column names
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION ="description";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_RATING= "rating";

	//array to store all of the column names for the update to be easier
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_TITLE, KEY_DESCRIPTION, KEY_IMAGE, KEY_RATING};

	//database info
	private static final String DATABASE_NAME = "dbMovies";
	private static final String DATABASE_TABLE = "movies";
	private static final int DATABASE_VERSION = 2;

	//sql statement to create database
	private static final String DATABASE_CREATE_SQL = "CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE + " TEXT NOT NULL, " + KEY_DESCRIPTION + " TEXT NOT NULL, " + KEY_IMAGE + " TEXT NOT NULL, " + KEY_RATING + " FLOAT " + ");";
	
	private final Context context;
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	//constructor
	public DBAdapter(Context ctx) {this.context = ctx;myDBHelper = new DatabaseHelper(context);}

	//open the database
	public DBAdapter open() throws SQLException {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	//close the database
	public void close()
	{
		myDBHelper.close();
	}
	
	//insert a movie into the database
	public long insertMovie(Movie movie) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_TITLE, movie.getTitle());
		initialValues.put(KEY_DESCRIPTION, movie.getDescription());
		initialValues.put(KEY_IMAGE, movie.getImage());
		initialValues.put(KEY_RATING, movie.getRating());
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	//delete a particular movie
	public void deleteMovie(int rowId) {
		String where = KEY_ROWID + "=" + rowId; //where clause to make it easier on the sql statement
		db = myDBHelper.getWritableDatabase();
		if(db.isOpen())
		{
			boolean b = db.delete(DATABASE_TABLE, where, null) != 0;
		}
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);
	}

	//updates a movie
	public boolean updateMovie(long rowId,String newTitle,String description, String image, float rating)
	{
		//get all of the new fields that the user has entered
		ContentValues newValues = new ContentValues();
		//put all of the fields that the user entered into a ContentValues variable
		newValues.put(KEY_TITLE, newTitle);
		newValues.put(KEY_DESCRIPTION, description);
		newValues.put(KEY_IMAGE,image);
		newValues.put(KEY_RATING,rating);
		//it's looking for a column where the newTitle variable goes.
		return db.update(DATABASE_TABLE, newValues, KEY_ROWID+"="+rowId,null)>0;
	}

	//retrieve all the movies
	public Cursor getAllMovies() {
		String where = null;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
		if(c != null){
			c.moveToFirst();
		}
		return c;
	}
	
	//retrieve a single movie
	public Cursor getMovie(long rowId) throws SQLException {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = db.query(true, DATABASE_TABLE, ALL_KEYS,
				where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context)
		{
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try{
				db.execSQL(DATABASE_CREATE_SQL);
			}catch(SQLException e){
				e.printStackTrace();
			}
		}//end method onCreate

		@Override
		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
			Log.w(TAG, "Upgrade database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}//end DatabaseHelper
}//end class DBAdapter












