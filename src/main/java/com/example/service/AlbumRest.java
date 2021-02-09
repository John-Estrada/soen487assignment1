package com.example.service;

import com.example.business.AlbumBusiness;
import com.example.business.ArtistBusiness;
import com.example.core.Album;
import com.example.core.Artist;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Path("album")
public class AlbumRest {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("all")
    public String listAllAlbums() throws Exception {
        return AlbumBusiness.getAllAlbums().stream().map(Object::toString).collect(Collectors.joining(".\n"));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("details/{isrc}")
    public String getAlbumDetails(@PathParam("isrc") int isrc) throws Exception {
        Album result = AlbumBusiness.getAllAlbums().stream().filter(album -> album.getIsrc() == isrc).findFirst().orElse(null);
        if (result != null) {
            return result.toString();
        } else {
            return "No album with this isrc found";
        }
    }

    @POST
    @Path("add/{title}/{description}/{year}/{artistNickName}")
    public String addAlbum(@PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artistNickName") String artistNickName) throws Exception {
        Artist artist = new Artist(artistNickName, "testfirst", "testlast", "testbio");
        Album album = new Album(title, description, year, artist);
        AlbumBusiness.addAlbum(album);
        System.out.println("Added album " + album.toString());

        return "Success";
    }

    @PUT
    @Path("update/{id}/{title}/{description}/{year}/{artistNickName}")
    public String updateAlbum(@PathParam("id") int isrc, @PathParam("title") String title, @PathParam("description") String description, @PathParam("year") int year, @PathParam("artistNickName") String artistNickName) throws Exception {
        String SUCCESS_TEXT = "Successfully updated album " + isrc;
        String FAILURE_TEXT = "Album " + isrc + " could not be found";

        Album albumToUpdate = AlbumBusiness.getAllAlbums().stream().filter(album -> album.getIsrc() == isrc).findFirst().orElse(null);
        Artist artist = ArtistBusiness.getAllArtists().stream().filter(a -> a.getNickName().equals(artistNickName)).findFirst().orElse(null);

        if (albumToUpdate != null) {
            albumToUpdate.setTitle(title);
            albumToUpdate.setDescription(description);
            albumToUpdate.setYear(year);
            if (artist!=null) {
                albumToUpdate.setArtist(artist);
            }

            System.out.println(SUCCESS_TEXT);
            return SUCCESS_TEXT;
        }  else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }


    }

    @DELETE
    @Path("delete/{id}")
    public String deleteAlbum(@PathParam("id") int isrc){
        String SUCCESS_TEXT = "Successfully deleted album "+isrc;
        String FAILURE_TEXT = "Album " + isrc + " could not be deleted - it is possible that this album does not exist";

        Album albumToDelete = AlbumBusiness.getAllAlbums().stream().filter(a -> a.getIsrc() == isrc).findFirst().orElse(null);
        if (albumToDelete!=null) {
            AlbumBusiness.removeAlbum(albumToDelete);
            System.out.println(SUCCESS_TEXT);
            return SUCCESS_TEXT;
        } else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }
    }
}
