package com.example.greg.assignment3;

/**
 * Activity used to show the details of a specific location's weather
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    /**
     * Declare variables
     */
    private TextView txtSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        /**
         * Instantiate variables
         */
        txtSummary = (TextView)findViewById(R.id.txtSummary);

        Bundle extras = getIntent().getExtras();

        //ensure there is information to show
        if(extras == null){
            return;
        }

        //get summary information and display to user
        String summary = extras.getString("summary");
        if(summary != null){
            txtSummary.setText(summary);
        }
    }

    /**
     * Go back to the network activity
     * @param v
     */
    public void goToNetworkActivity(View v){
        Intent i = new Intent(this,NetworkActivity.class);
        startActivity(i);
    }
}
