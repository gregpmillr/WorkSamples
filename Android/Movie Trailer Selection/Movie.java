package com.example.greg.trailerlaptop;

public class Movie {
    private String title;
    private String description;
    private String image;
    private float rating;

    public Movie(){

    }
    public  Movie(String title, String description, String image, float rating)
    {
        this.title = title;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
