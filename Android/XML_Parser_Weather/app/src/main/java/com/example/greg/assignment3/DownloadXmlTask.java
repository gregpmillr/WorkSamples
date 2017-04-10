package com.example.greg.assignment3;

/**
 * Activity used to download and parse the XML file via URL.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadXmlTask extends AsyncTask<String[], Void, String[]> {

    /**
     * Declare variables
     */
    private NetworkActivity activity;
    private String url;
    private XmlPullParserFactory xmlFactoryObject;
    private ProgressDialog pDialog;

    /**
     * Loaded constructor to assign variables
     * @param activity main activity
     * @param url to be parsed
     */
    public DownloadXmlTask(NetworkActivity activity, String url) {
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);

    }

    @Override
    protected String[] doInBackground(String[]... params) {
        try {
            //get the url
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream stream = connection.getInputStream();

            /**
             * instantiate an XmlPullParser which will take url into an
             * input stream, store it in results and pass back that
             * information to the network activity.
             */
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();

            myParser.setInput(stream, null);

            //parse the XmlPullParser object
            String[] result = parseXML(myParser);

            stream.close();

            return result;

        } catch (Exception e) {
            //display errors with asynctask
            e.printStackTrace();
            Log.e("AsyncTask", "exception");
            return null;
        }
    }

    /**
     * Used to return the parsed XML from tags
     * @param myParser parse this XML
     * @return String[]
     */
    public String[] parseXML(XmlPullParser myParser) {

        int event;
        String name="";
        List<String> xmlStrings = new ArrayList<>();

        /**
         * Routine to grab information from the XML file
         * by the event type's name. If it's information we want,
         * it is stored in xmlStrings.
         */
        try {
            event = myParser.getEventType();
            //while not at the end of the document, parse by name
            while (event != XmlPullParser.END_DOCUMENT)  {
                switch (event){
                    case XmlPullParser.START_TAG:
                        name=myParser.getName();
                        break;
                    case XmlPullParser.TEXT:
                        if(name.equals("title")){ xmlStrings.add(myParser.getText()); }
                        else if(name.equals("summary")){ xmlStrings.add("Summary:\r\n " + myParser.getText()); }
                        name = "";
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = myParser.next();
            }
            } catch (XmlPullParserException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        /**
         * Convert ArrayList to String[] and return String[]
         */
        String[] fixedStrings = new String[xmlStrings.size()];
        for(int i = 0;i<xmlStrings.size();i++){
            fixedStrings[i] = xmlStrings.get(i);
        }
        return fixedStrings;

    }

    @Override
    protected void onPostExecute(String[] result) {
        //call back data to main thread
        pDialog.dismiss();
        activity.callBackData(result);

    }
}