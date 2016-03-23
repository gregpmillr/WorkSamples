package com.example.greg.trailerlaptop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

public class addMovieAdapter extends ArrayAdapter<String>{
    //properties
    private final Context context;
    private final String[] movies = {
            "action",
            "comedy",
            "horror"
    };

    //custom constructor
    public addMovieAdapter(Context context) {
        super(context, R.layout.list_row);//pass in layout because that's where you're displaying it
        this.context = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView= inflater.inflate(R.layout.list_row, null, true);

        ImageView image = (ImageView) rowView.findViewById(R.id.imageView2);
        //set images from the array above
        final int imageId = context.getResources().getIdentifier(movies[position].toLowerCase(), "drawable", MainActivity.PACKAGE_NAME);
        image.setBackgroundResource(imageId);

        return rowView;
    }

    @Override
    public int getCount(){
        return movies.length;
    }
}

