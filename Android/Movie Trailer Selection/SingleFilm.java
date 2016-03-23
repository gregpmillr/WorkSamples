package com.example.greg.trailerlaptop;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class SingleFilm extends AppCompatActivity {

    int id;
    String desc;
    String image;
    float rating;
    int imageID;
    String yourTitle;

    public ImageView filmImageView;
    public TextView titleTextView;
    public TextView descTextView;
    public Button btnDelete;
    public RatingBar ratingBarSingle;
    public Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_film);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //get all of the movie credentials that are to be displayed on this activity
            id = extras.getInt("rowID");
            yourTitle = extras.getString("YOUR_TITLE");
            imageID = extras.getInt("IMAGE_ID");
            desc = extras.getString("MOVIE_DESC");
            image = extras.getString("MOVIE_TITLE");
            rating = extras.getFloat("MOVIE_RATING");
        }

        //get all of fields that'll have data
        filmImageView = (ImageView)findViewById(R.id.imgFilm);
        titleTextView = (TextView)findViewById(R.id.txtFilmName);
        descTextView = (TextView)findViewById(R.id.txtFilmDescription);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        ratingBarSingle = (RatingBar) findViewById(R.id.ratingBarSingle);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        //set all fields
        filmImageView.setImageResource(imageID);
        titleTextView.setText(yourTitle);
        descTextView.setText(desc);
        ratingBarSingle.setRating(rating);

        //when user clicks on an image, pass the title of the trailer and start the intent to view the film
        filmImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), FilmViewer.class);
                intent.putExtra("MOVIE_TITLE", image);
                startActivity(intent);

            }
        });

        //pass an ID to delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter myDB = new DBAdapter(v.getContext());
                myDB.deleteMovie(id);
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EditMovie.class);
                //pass in current movie credentials that are to be edited
                intent.putExtra("ROW_ID", id);
                intent.putExtra("MOVIE_TITLE",image);
                intent.putExtra("MOVIE_ID",imageID);
                startActivity(intent);
            }
        });





    }
}
