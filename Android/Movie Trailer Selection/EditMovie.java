package com.example.greg.trailerlaptop;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class EditMovie extends Activity {
    int id;
    String image;
    int imageID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //get the fields of the movie that is to be edited
            id = extras.getInt("ROW_ID");
            image = extras.getString("MOVIE_TITLE");
            imageID = extras.getInt("MOVIE_ID");
        }

        //must be declared final because accessed inthe OnClickListener
        final EditText editTitle= (EditText) findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) findViewById(R.id.editDescription);
        Button updateSubmit = (Button) findViewById(R.id.submitEdit);
        final RatingBar editRating = (RatingBar) findViewById(R.id.editRatingBar2);

        updateSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the new fields from the form
                String newTitle = editTitle.getText().toString();
                String newDescription = editDescription.getText().toString();
                float newRating = editRating.getRating();

                //validation to check if user input is empty
                if(isEmpty(newTitle))
                {
                    editTitle.setError(" Title is required!");
                }
                else if(isEmpty(newDescription))
                {
                    editDescription.setError(" Description is required!");
                }
                else{
                    //update movie if everything is good
                    DBAdapter myDB = new DBAdapter(getBaseContext());
                    myDB.open();
                    myDB.updateMovie(id, newTitle, newDescription, image, newRating);
                    myDB.close();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isEmpty(String input)
    {
        if(input.isEmpty())
        {
            return true;
        }
        else{return false;}
    }
}
