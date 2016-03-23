package com.example.w0273754.quizproj;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.*;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button btnBegin;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize variables
        txtName = (EditText) findViewById(R.id.editTxtName);
        btnBegin = (Button) findViewById(R.id.btnBegin);

        //onclick listener for intent to go to start quiz
        btnBegin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to use intent goes here
                String name = txtName.getText().toString();
                if(name.matches("^[\\p{L} .'-]+$")){
                    Intent i = new Intent("MainActivity2Activity");//create intent
                    Bundle extras = new Bundle();//create bundle object
                    extras.putString("KEY", name);//fill bundle
                    i.putExtras(extras);
                    startActivityForResult(i, 1);
                } else {
                    Toast.makeText(getBaseContext(), "Enter your name", Toast.LENGTH_LONG).show();
                }
            }
        });//end listener inner class
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
