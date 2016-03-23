package com.example.greg.trailerlaptop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

public class addMovie extends Activity {

    //properties
    ListView movies;
    int itemClicked;
    String title;
    String description;
    String image;
    float rating;
    String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        populateListView(); //populate the list view for movies they can choose from

        //must be declared final because they're accessed in the onClickListener below
        final EditText txtTitle = (EditText) findViewById(R.id.addTitle);
        final EditText txtdescription = (EditText) findViewById(R.id.addDescription);
        final Button btnAdd = (Button) findViewById(R.id.addSingleMovie);
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final EditText txtImageName = (EditText) findViewById(R.id.addImageName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get fields that the user entered
                title = txtTitle.getText().toString();
                description = txtdescription.getText().toString();
                rating = ratingBar.getRating();
                imageName = txtImageName.getText().toString();

                //check for validation, if it's valid then submit, if it isn't valid then get out of here!
                if (isEmpty(title)) {
                    txtTitle.setError(" Title is required!");
                }
                else if (isEmpty(description)) {
                    txtdescription.setError(" Description is required!");
                }
                else if (isEmpty(imageName))
                {
                    txtImageName.setError("Image File Name is required!");
                }
                else
                {
                    int resId = getResources().getIdentifier(imageName.toLowerCase(), "drawable", getPackageName());//get the resource ID of the image name that they've entered
                    String image = getBaseContext().getResources().getResourceEntryName(resId);//get the name of the image from the resource ID (sanitized a bit from the line above)

//                if(itemClicked == 0){
//                    int resId = getResources().getIdentifier(imageName.toLowerCase(), "raw", getPackageName());
//                    String image = getBaseContext().getResources().getResourceEntryName(resId);
//                }
//                else if(itemClicked ==1){
//                    image="comedy";
//                }

                    Movie newMovie = new Movie(title,description,image, rating);//create a new movie given fields above
                    DBAdapter myDB = new DBAdapter(getBaseContext());
                    myDB.open();
                    myDB.insertMovie(newMovie);//add movie to the DB
                    myDB.close();
                    Intent intent = new Intent(v.getContext(), MainActivity.class);//go back to main activity
                    v.getContext().startActivity(intent);
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_movie, menu);
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

    private void populateListView(){
        addMovieAdapter movieAdapter= new addMovieAdapter(getBaseContext());
        movies = (ListView) findViewById(R.id.listView);
        movies.setAdapter(movieAdapter);
        movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemClicked = position;
            }
        });
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
