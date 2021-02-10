package com.example.core;

import java.util.ArrayList;

public class Artist {
    private String nickName;
    private String firstName;
    private String lastName;
    private String bio;

    //used to ensure that there are not two artists with the same nickname
    private static ArrayList<String> usedNickNames = new ArrayList<String>();
    private static ArrayList<Artist> allArtists = new ArrayList<Artist>();

    public Artist(String nickName, String firstName, String lastName, String bio) throws Exception {
        if (!this.usedNickNames.contains(nickName)) {
            this.nickName = nickName;
            this.usedNickNames.add(nickName);
        } else {
            throw new Exception("This nickname is already in use.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        if (!this.usedNickNames.contains(nickName)) {
            String prevName = this.nickName;
            this.nickName = nickName;
            this.usedNickNames.add(nickName);
            usedNickNames.remove(prevName);
        } else {
            System.out.println("This nickname is already in use.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static void removeNickName(String nickname) {
        usedNickNames.remove(nickname);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "nickName='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public String toListItem() {
        return "Nickname: " + nickName + ", Full Name: " + firstName + " " + lastName;
    }
}
