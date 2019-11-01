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

    public String getUsername() {
        return this.username;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setSong(int n) {
        this.song = n;
    }

    public int getSong(){
        return this.song;
}

}
