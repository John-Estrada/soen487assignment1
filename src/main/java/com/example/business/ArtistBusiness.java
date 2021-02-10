package com.example.business;

import com.example.core.Artist;
import com.example.service.ArtistServlet;

import java.util.ArrayList;

public class ArtistBusiness {
    private static ArrayList<Artist> allArtists = new ArrayList<Artist>();

    public static ArrayList<Artist> getAllArtists() {
        return allArtists;
    }

    public static void insertArtist(Artist artist) {
        allArtists.add(artist);
    }

    public static void removeArtist(Artist artist) {
        allArtists.remove(artist);
        Artist.removeNickName(artist.getNickName());
    }
}
