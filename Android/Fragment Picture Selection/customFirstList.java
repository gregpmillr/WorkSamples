package com.example.gregmiller.picselectfragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class customFirstList extends ArrayAdapter<String>{
    //properties
    private final Context context;
    private final String[] web;
    private final Integer[] imageId;

    //custom constructor
    public customFirstList(Context context, String[] web, Integer[] imageId) {
        super(context, R.layout.list_row, R.id.txt, web);//pass in layout because that's where you're displaying it
        this.context = context;
        this.web = web;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        //get shared preference so we can instantly load to check if the user has clicked here before
        SharedPreferences sP = ((Activity) context).getPreferences(context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();
        editor.clear();//clear the shared preferences
       //editor.commit();//RIGHT NOW IT'LL CLEAR SHARED PREFS EVERY TIME IT RELOADS, DELETE THIS LINE IF YOU WANT TO SAVE SHARED PREFS
        //if the shared preferences contains where the user has clicked before in the program, then disable it, etc.
        if(sP.contains(web[+position])) {
            System.out.println(sP.getAll());
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_row, null, true);
            rowView.setBackgroundColor(Color.BLACK);
            rowView.setClickable(true);
            rowView.setEnabled(false);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            txtTitle.setText(web[position]);
            return rowView;
        }
        else{//if they havent clicked it before so shared pref doesn't contain it, then just display it normally
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            View rowView= inflater.inflate(R.layout.list_row, null, true);
            TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
            txtTitle.setText(web[position]);

            return rowView;
        }





    }







}
