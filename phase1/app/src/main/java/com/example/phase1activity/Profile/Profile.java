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
