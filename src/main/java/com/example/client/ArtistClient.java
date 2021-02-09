package com.example.client;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class ArtistClient {
    public static void createArtist(String nickname, String firstname, String lastname, String bio) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/repository/artist/add/%s/%s/%s/%s", nickname, firstname, lastname, bio));
            CloseableHttpResponse response = client.execute(httpPost);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateArtist(String nickname, String firstname, String lastname, String bio) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/repository/artist/update/%s/%s/%s/%s", nickname, firstname, lastname, bio));
            CloseableHttpResponse response = client.execute(httpPut);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteArtist(String nickname) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/repository/artist/delete/%s", nickname));
            CloseableHttpResponse response = client.execute(httpDelete);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getArtist(String nickname) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/repository/artist/detail/%s", nickname));

        CloseableHttpResponse response = client.execute(httpGet);
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        response.close();
        client.close();
        return sb.toString();
    }

    public static String getAllArtists() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/repository/artist/all");

        CloseableHttpResponse response= client.execute(httpGet);
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        response.close();
        client.close();
        return sb.toString();
    }
}
