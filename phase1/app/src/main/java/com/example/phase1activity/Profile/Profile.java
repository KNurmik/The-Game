package com.example.phase1activity.Profile;

public class Profile {
    String username;
    String password;
    int gameLevel;
    String colour;
    int song;
    String nickname;
    Stats statistics;

    public Profile(String username, String password) {
        this.username = username;
        this.password = password;
        this.nickname = username;
        this.colour = "Red";
        this.gameLevel = 0;
        this.song = 0;
    }

    String getUsername() {
        return this.username;
    }

    void setColour(String colour) {
        this.colour = colour;
    }

    void setNickname(String nickname) {
        this.nickname = nickname;
    }

    String getNickname() {
        return this.nickname;
    }

    void setSong(int n) {
        this.song = n;
    }

    int getSong(){
        return this.song;
}

}
