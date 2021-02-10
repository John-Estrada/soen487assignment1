package com.example.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) throws IOException, InterruptedException {
        String REPEATING_MESSAGE = "Choose an option:\n" +
                "1. Album\n" +
                "2. Artist\n" +
                "3. Quit\n";
        String[] params = null;

        Scanner scanner = new Scanner(System.in);
        try {
            boolean go = true;
            while (go) {
                System.out.println(REPEATING_MESSAGE);
                String input = scanner.nextLine();
                switch (input) {
                    case "1" :
                        System.out.println("Album operations:\n" +
                                "1. Create\n" +
                                "2. Update\n" +
                                "3. Delete\n" +
                                "4. Display All Albums\n" +
                                "5. Display One Album\n" +
                                "6. Cancel");
                        String album1 = scanner.nextLine();
                        switch (album1) {
                            case "1" :
                                System.out.println("Enter details: {title} {description} {year} {artistNickName}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    AlbumClient.createAlbum(params[0], params[1], Integer.parseInt(params[2]), params[3]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                System.out.println("Enter details: {id} {title} {description} {year} {artistNickName}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    AlbumClient.updateAlbum(Integer.parseInt(params[0]), params[1],params[2], Integer.parseInt(params[3]), params[4]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "3":
                                System.out.println("Enter details: {id}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    AlbumClient.deleteAlbum(Integer.parseInt(params[0]));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "4":

                                try {
                                    String all = AlbumClient.getAllAlbums();
                                    System.out.println(all);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "5":
                                System.out.println("Enter details: {id}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    String result = AlbumClient.getAlbum(Integer.parseInt(params[0]));
                                    System.out.println(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "6":
                                break;
                        }

                        break;
                    case "2":
                        System.out.println("Artist operations:\n" +
                                "1. Create\n" +
                                "2. Update\n" +
                                "3. Delete\n" +
                                "4. Display All Artists\n" +
                                "5. Display One Artist\n" +
                                "6. Cancel");
                        String artist1 = scanner.nextLine();
                        switch (artist1){
                            case "1":
                                System.out.println("Enter details: {nickname} {firstname} {lastname} {bio}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    ArtistClient.createArtist(params[0], params[1], params[2], params[3]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                System.out.println("Enter details: {nickname} {firstname} {lastname} {bio}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    ArtistClient.updateArtist(params[0], params[1], params[2], params[3]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "3":
                                System.out.println("Enter details: {nickname}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    ArtistClient.deleteArtist(params[0]);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "4":
                                try {
                                    String all = ArtistClient.getAllArtists();
                                    System.out.println(all);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "5":
                                System.out.println("Enter details: {nickname}");
                                params = scanner.nextLine().split(" ");
                                try {
                                    String result = ArtistClient.getArtist(params[0]);
                                    System.out.println(result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "6": break;
                        }


                        break;
                    case "3":
                        go = false;
                        System.out.println("Quitting");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
