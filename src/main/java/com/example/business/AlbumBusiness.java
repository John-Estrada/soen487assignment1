package com.example.business;

import com.example.core.Album;

import java.util.ArrayList;

public class AlbumBusiness {
    private static ArrayList<Album> allAlbums = new ArrayList<Album>();

    public static void addAlbum(Album album) {
        allAlbums.add(album);
    }

    public static void removeAlbum(Album album) {
        allAlbums.remove(album);
    }

    public static ArrayList<Album> getAllAlbums() {
        return allAlbums;
    }
}
