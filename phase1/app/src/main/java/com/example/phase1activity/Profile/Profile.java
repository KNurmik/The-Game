package com.example.phase1activity.Profile;

public class Profile {
    String username;
    String password;
    int gameLevel;
    String colour;
    int song;
    String nickname;
    Stats stats;

    public Profile(String username, String password, String nickname, String colour, int gameLevel, int song) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.colour = colour;
        this.gameLevel = gameLevel;
        this.song = song;
        this.stats = new Stats();
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
