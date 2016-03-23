package com.example.greg.trailerlaptop;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class FilmViewer extends AppCompatActivity {

    VideoView vidView;
    String title;
    Uri video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_viewer);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("MOVIE_TITLE"); //pass the title of the movie to be shown
        }
        vidView = (VideoView)findViewById(R.id.videoView);

        int resId = getResources().getIdentifier(title.toLowerCase(), "raw", getPackageName()); //get the ID from the title that was passed to this class
        String currentName = getBaseContext().getResources().getResourceEntryName(resId); //get the name from the ID (sanitized)
        video = Uri.parse("android.resource://" + getPackageName() + "/raw/"+currentName); //parse the name of the video into a video URI
        vidView.setVideoURI(video); //set videoview URI to the URI from the live above this
        MediaController vidControl = new MediaController(this); //new mediacontroller for buttons
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl); //set mediacontroller on the current video
        vidView.start(); //start video
    }
}
