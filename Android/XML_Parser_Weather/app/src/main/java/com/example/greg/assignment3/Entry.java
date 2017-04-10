package com.example.greg.assignment3;

/**
 * Used to store the information for each entry of the feeds.csv file
 */

public class Entry {

    /**
     * Decalre variables
     */
    private String url;
    private String location;
    private String parentLocation;

    /**
     * Empty constructor
     */
    public Entry(){}

    /**
     * Loaded constructor with required fields
     * @param url to be parsed
     * @param location of the city
     * @param parentLocation parent of the city
     */
    public Entry(String url, String location, String parentLocation){
        this.url = url;
        this.location = location;
        this.parentLocation = parentLocation;
    }

    public String getUrl(){return this.url;}
    public String getLocation(){return this.location;}
    public String getParentLocation(){return this.parentLocation;}

    public void setUrl(String url){this.url = url;}
    public void setLocation(String location){this.location = location;}

}
