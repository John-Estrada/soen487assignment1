package com.example.service;

import com.example.business.ArtistBusiness;
import com.example.core.Artist;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("artist")
public class ArtistRest {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("all")
    public String getAll(){
        ArrayList<Artist> all = ArtistBusiness.getAllArtists();
        StringBuilder sb = new StringBuilder();
        for (Artist artist : all) {
            sb.append(artist.toListItem());
            sb.append("\n");
        }
//        return ArtistBusiness.getAllArtists().stream().map(Object::toString).collect(Collectors.joining(".\n"));
        return sb.toString();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("detail/{nickname}")
    public String getDetails(@PathParam("nickname") String nickname){
        String SUCCESS_TEXT = "Successfully loaded details for artist " + nickname;
        String FAILURE_TEXT = "Artist " + nickname + " could not be found";

        Artist result = ArtistBusiness.getAllArtists().stream().filter(a -> a.getNickName().equals(nickname)).findFirst().orElse(null);
        if (result != null){
            System.out.println(SUCCESS_TEXT);
            return result.toString();
        } else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }
    }

    @POST
    @Path("add/{nickname}/{firstname}/{lastname}/{bio}")
    public String addArtist(@PathParam("nickname") String nickname, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("bio") String bio) throws Exception {
        String SUCCESS_TEXT = "Successfully inserted new artist " + nickname;
        String FAILURE_TEXT = "This artist already exists";

        Artist alreadyExists = ArtistBusiness.getAllArtists().stream().filter(a -> a.getNickName().equals(nickname)).findFirst().orElse(null);
        if (alreadyExists==null) {
            ArtistBusiness.insertArtist(new Artist(nickname, firstName, lastName, bio));
            System.out.println(SUCCESS_TEXT);
            return SUCCESS_TEXT;
        } else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }
    }

    @PUT
    @Path("update/{nickname}/{firstname}/{lastname}/{bio}")
    public String updateArtist(@PathParam("nickname") String nickname, @PathParam("firstname") String firstName, @PathParam("lastname") String lastName, @PathParam("bio") String bio){
        String SUCCESS_TEXT = "Successfully updated artist " + nickname;
        String FAILURE_TEXT = "The artist " + nickname + " does not exist";

        Artist alreadyExist = ArtistBusiness.getAllArtists().stream().filter(a -> a.getNickName().equals(nickname)).findFirst().orElse(null);

        if (alreadyExist!=null){
            alreadyExist.setFirstName(firstName);
            alreadyExist.setLastName(lastName);
            alreadyExist.setBio(bio);

            System.out.println(SUCCESS_TEXT);
            return SUCCESS_TEXT;
        } else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }
    }

    @DELETE
    @Path("delete/{nickname}")
    public String deleteArtist(@PathParam("nickname") String nickname){
        String SUCCESS_TEXT = "Artist " + nickname + " was successfully removed";
        String FAILURE_TEXT = "Artist " + nickname + " was not found";

        Artist toDelete = ArtistBusiness.getAllArtists().stream().filter(a -> a.getNickName().equals(nickname)).findFirst().orElse(null);

        if (toDelete!=null) {
            ArtistBusiness.removeArtist(toDelete);
            System.out.println(SUCCESS_TEXT);
            return SUCCESS_TEXT;
        } else {
            System.out.println(FAILURE_TEXT);
            return FAILURE_TEXT;
        }
    }
}
