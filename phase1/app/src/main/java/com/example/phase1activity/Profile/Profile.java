package com.example.phase1activity.Profile;

public class Profile {
    String username;
    String password;
    int gameLevel = 0;
    String colour;
    String difficulty;

    String nickname;
    Stats statistics;

    Profile(String username, String password){
        this.username = username;
        this.password = password;
        this.nickname = username;
    }

    String getUsername(){
        return this.username;
    }

    void setcolour(String colour){
        this.colour = colour;
    }

    void setNickname(String nickname) {
        this.nickname = nickname;
    }

    String getNickname(){
        return this.nickname;
    }

}
