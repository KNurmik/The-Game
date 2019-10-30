package com.example.phase1activity.Profile;

public class Profile {
    String username;
    String password;
    int gameLevel = 0;
    String colour;
    String difficulty;
    Stats statistics;

    Profile(String username, String password){
        this.username = username;
        this.password = password;
    }

    String getUsername(){
        return this.username;
    }

}
