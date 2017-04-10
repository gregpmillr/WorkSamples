package com.example.greg.assignment3;

/**
 * Activity used to show the current location
 * and allow for changing locations using a ListView
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity {

    /**
     * Declare variables
     */
    private TextView txtLocation;
    private String location;
    private ListView lvLocations;
    private ArrayList<String> locations;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        /**
         * Instantiate variables
         */
        lvLocations = (ListView)findViewById(R.id.lvLocations);
        txtLocation = (TextView)findViewById(R.id.txtLocation);

        //ensure there is information from the bundle
        Bundle extras = getIntent().getExtras();

        if(extras == null){
            return;
        }

        //get information from the bundle
        locations = extras.getStringArrayList("locations");
        location = extras.getString("location");

        if(location != null){
            txtLocation.setText("Current location: " + location);
        }

        /**
         * Set the ListView locations
         */
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                locations );

        lvLocations.setAdapter(arrayAdapter);

        /**
         * When a location is clicked, change the shared preferences location
         * and display the new information in NetworkActivity.
         */
        lvLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //change shared preferences for url and location name
                Intent j = new Intent(getApplicationContext(),NetworkActivity.class);
                TextView txtView = (TextView) view;

                sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("location",txtView.getText().toString());
                editor.commit();

                startActivity(j);
            }
        });

    }

    /**
     * Used to navigate back to NetworkActivity.
     * @param v
     */
    public void goToHomeActivity(View v){
        Intent i = new Intent(this,NetworkActivity.class);
        startActivity(i);
    }
}
