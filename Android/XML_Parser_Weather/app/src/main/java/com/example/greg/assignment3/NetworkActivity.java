package com.example.greg.assignment3;

/**
 * Activity representing the main activity. This will display the
 * 7 day forecast, along with any special announcements.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NetworkActivity extends Activity {

    /**
     * Declare variables
     */
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;
    SharedPreferences sharedPreferences;
    private TextView txtTitle;
    private TextView txtCurrentWeather;

    /**
     * Instantiate variables
     */
    private List<Entry> entries = new ArrayList<Entry>();
    public static final String PREFS_NAME = "prefs" ;
    String summary = "";
    ArrayList<String> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        /**
         * Instantiate views
         */
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtCurrentWeather = (TextView) findViewById(R.id.txtCurrentWeather);

        //assign shared prefs
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        //get the feeds.csv file
        InputStream stream = null;
        try {
            stream = getAssets().open("feeds.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Read in feeds.csv, splitting on "," which returns 3 columns and 1 row.
         * Add that information to the entries ArrayList and locations ArrayList
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] RowData = line.split(",");
                entries.add(new Entry(RowData[0],RowData[1],RowData[2]));
                locations.add(RowData[1]);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                stream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Ensure there is a URL in the shared preferences,
         * if not, assign one.
         */
        //set initial location
        if( (!sharedPreferences.contains("URL")) ) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("URL","http://weather.gc.ca/rss/city/ns-19_e.xml");
            editor.putString("location","Halifax, NS");
            editor.commit();

            //download the xml file and parse
            DownloadXmlTask task = new DownloadXmlTask(this, sharedPreferences.getString("URL",null));
            task.execute();
        }else{
            //we've been in the application before, so just load the location
            String location = sharedPreferences.getString("location",null);

            for(Entry e : entries){
                if(e.getLocation().equals(sharedPreferences.getString("location",null))){
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("URL",e.getUrl());
                    editor.commit();
                }
            }

            //download and parse the XML file
            DownloadXmlTask task = new DownloadXmlTask(this, sharedPreferences.getString("URL",null));
            task.execute();
        }

    }

    /**
     * Handle data which is called by the ASyncTask
     * @param results represents results from ASyncTask
     */
    public void callBackData(String[] results){

        //loop through results
        for(String s : results){
            if(s.contains("Current Conditions:")){
                txtCurrentWeather.setText(txtCurrentWeather.getText() + s);
            }
            else if(s.contains("Summary:")){
                summary = summary + "\r\n" + s + "\r\n";
            }else{
                txtTitle.setText(txtTitle.getText() + s + "\r\n");
            }
        }
    }

    /**
     * Used to navigate to specific details activity
     * @param v
     */
    public void goToDetailsActivity(View v){
        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtra("summary",summary);
        startActivity(i);
    }

    /**
     * Used to navigate to locations activity with the specific
     * location.
     * @param v
     */
    public void goToLocationsActivity(View v){
        Intent i = new Intent(this, LocationsActivity.class);
        i.putExtra("location",sharedPreferences.getString("location",null));
        i.putStringArrayListExtra("locations",locations);
        startActivity(i);
    }

}