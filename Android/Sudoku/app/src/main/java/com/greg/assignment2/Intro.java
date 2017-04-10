package com.greg.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Intro extends AppCompatActivity {

    /**
     * Declare variables
     */
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        /**
         * Initialize variables
         */
        btnStart = (Button) this.findViewById(R.id.btnStart);

        /**
         * When start button is clicked, navigate to MainActivity
         */
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intro.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
