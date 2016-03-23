package com.example.greg.trailerlaptop;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DBAdapter myDB;
    Button btnAdd;
    ListView myList;
    public static String PACKAGE_NAME;

    /* Within which the entire activity is enclosed */
    private DrawerLayout mDrawerLayout;

    /* ListView represents Navigation Drawer */
    private ListView mDrawerList;

    /* ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar */
    private ActionBarDrawerToggle mDrawerToggle;

    /* Title of the action bar */
    private String mTitle = "Navigation Drawer";

    /* Getting navigation items from array */
    private String[] items;

    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        openDB();

        //defaultMovies();
        populateListView(); //populate the list view in the main activity

        items = getResources().getStringArray(R.array.planets_array);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);

        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.drawer_list_item, items);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                //Replace fragment content
                updateActivity(view, position);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

    }

    public void updateActivity(View v, int position) {
        Intent intent = new Intent(v.getContext(), MainActivity.class);//go back to main activity
        if (position == 1)
        {
            intent = new Intent(v.getContext(), addMovie.class);//go back to main activity
        }
        v.getContext().startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }return super.onOptionsItemSelected(item);}

    @Override
    public void onResume(){
        super.onResume();
        populateListView(); //every time the user goes back to the main activity, we must re-populate the listview given the changes
    }

    private void openDB(){
        myDB = new DBAdapter(this);
        myDB.open();
    }

    private void addMovie(View v){
        Intent intent = new Intent(v.getContext(), addMovie.class);
        v.getContext().startActivity(intent);
    }

    private void defaultMovies(){
        //default movies to be inserted
        Movie mov1 = new Movie("An Action movie","very action-y","action", 0);
        Movie mov2 = new Movie("Hilarious movie","It isn't really that funny ", "comedy", 0);
        Movie mov3 = new Movie("Scary movie", "AHHH","horror", 0);
        //insert movie objects into the DB
        myDB.insertMovie(mov1);
        myDB.insertMovie(mov2);
        myDB.insertMovie(mov3);
        populateListView();
    }

    private void populateListView(){
        Cursor cursor = myDB.getAllMovies();//store all of the movies in the database into a cursor
        String[] fromFieldNames = new String[]{DBAdapter.KEY_TITLE, DBAdapter.KEY_DESCRIPTION, DBAdapter.KEY_RATING };//data in list view comes from these fields in the DB
        int[] toViewIDs = new int[] {R.id.textViewItemTitle, R.id.textViewItemDescription, R.id.mainRatingBar};//data in list view goes into these fields
        MyCursorAdapter myCursorAdapter;
        myCursorAdapter = new MyCursorAdapter(getBaseContext(),R.layout.item_layout,cursor,fromFieldNames,toViewIDs,0);
        myList = (ListView) findViewById(R.id.listViewMovies);
        myList.setAdapter(myCursorAdapter);//populates the list view via an adapter
    }

    public DBAdapter getDBAdapter()
    {
        return this.myDB;
    }
    }

