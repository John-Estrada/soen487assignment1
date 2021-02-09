package com.example.core;

import java.util.ArrayList;

public class Album {
    private int isrc;
    private String title;
    private String description;
    private int year;
    private Artist artist;

    //used to ensure that the isrc is unique - incremented every time a new album is created
    private static int currentIsrc=0;

    public Album(String title, String description, int year, Artist artist) {
        this.isrc = this.currentIsrc++;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getIsrc() {
        return isrc;
    }

    @Override
    public String toString() {
        return "Album{" +
                "isrc=" + isrc +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", artist=" + artist +
                '}';
    }
}
