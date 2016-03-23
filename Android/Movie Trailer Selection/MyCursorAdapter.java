package com.example.greg.trailerlaptop;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends SimpleCursorAdapter {

    final Context context;
    private int layout;

    public MyCursorAdapter (Context context, int layout, Cursor c, String[] from, int[] to, int flags)
    {
        super(context, layout, c, from, to);
        this.context = context; //to be able to access the context from inside this class
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(layout, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {
        //store the DB column INDEX into variables ****THIS ISN'T THE DATA, ITS THE INDEX****
        final int keyID = c.getColumnIndex(DBAdapter.KEY_ROWID);
        int keyCol = c.getColumnIndex(DBAdapter.KEY_IMAGE);
        int titlecol = c.getColumnIndex(DBAdapter.KEY_TITLE);
        int desccol = c.getColumnIndex(DBAdapter.KEY_DESCRIPTION);
        int ratcol = c.getColumnIndex(DBAdapter.KEY_RATING);

        //store those the column index into these variables ****THIS IS THE DATA FROM THE INDEX****
        final int key = Integer.parseInt(c.getString(keyID));
        final String title = c.getString(titlecol);
        final String desc = c.getString(desccol);
        final String image = c.getString(keyCol);
        final float rating = c.getFloat(ratcol);

        //get the imageID corresponding to which title was put in the DB.
        final int imageId = context.getResources().getIdentifier(image.toLowerCase(), "drawable", MainActivity.PACKAGE_NAME);

        TextView mtitle = (TextView) view.findViewById(R.id.textViewItemTitle);
        TextView mdesc = (TextView) view.findViewById(R.id.textViewItemDescription);
        ImageView im = (ImageView) view.findViewById(R.id.imageView);
        RatingBar rb = (RatingBar) view.findViewById(R.id.mainRatingBar);

        //set the data in the listview from the data we've retrieved from the DB
        mtitle.setText(title);
        mdesc.setText(desc);
        rb.setRating(rating);
        im.setImageResource(imageId);
        rb.setEnabled(false);

        im.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is to display a single movie.
                Intent intent = new Intent(v.getContext(), SingleFilm.class);
                //pass all of the movie credentials into the intent
                intent.putExtra("rowID", key);
                intent.putExtra("YOUR_TITLE", title);
                intent.putExtra("IMAGE_ID", imageId);
                intent.putExtra("MOVIE_TITLE", image);
                intent.putExtra("MOVIE_DESC", desc);
                intent.putExtra("MOVIE_RATING",rating);
                v.getContext().startActivity(intent);
            }
        });
    }
}


