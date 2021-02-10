package com.example.client;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class AlbumClient {
    public static void createAlbum(String title, String description, int year, String artistNickName) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/repository/album/add/%s/%s/%d/%s", title, description, year, artistNickName));
            CloseableHttpResponse response = client.execute(httpPost);
            Scanner sc = new Scanner(response.getEntity().getContent());
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            System.out.println(sb.toString());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAlbum(int id, String title, String description, int year, String artistNickName){
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/repository/album/update/%d/%s/%s/%d/%s", id, title, description, year, artistNickName));
            CloseableHttpResponse response = client.execute(httpPut);
            Scanner sc = new Scanner(response.getEntity().getContent());
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            System.out.println(sb.toString());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAlbum(int id) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/repository/album/delete/%d", id));
            CloseableHttpResponse response = client.execute(httpDelete);
            Scanner sc = new Scanner(response.getEntity().getContent());
            StringBuilder sb = new StringBuilder();
            while(sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            System.out.println(sb.toString());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getAlbum(int id) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/repository/album/details/%d", id));
        CloseableHttpResponse response = client.execute(httpGet);
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()){
            sb.append(sc.nextLine());
            sb.append("\n");
        }
        response.close();
        client.close();
        return sb.toString();
    }

    public static String getAllAlbums() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/repository/album/all");

        CloseableHttpResponse response = client.execute(httpGet);
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while (sc.hasNext()) {
            stringResponse.append(sc.nextLine());
            stringResponse.append("\n");
        }
        response.close();
        client.close();
        return stringResponse.toString();
    }
}
