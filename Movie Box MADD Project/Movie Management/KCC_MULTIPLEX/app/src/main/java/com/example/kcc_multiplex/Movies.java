package com.example.kcc_multiplex;

public class Movies {
    private int id;
    private String movivename;
    byte[] movieimage;


    public Movies(int id, String movivename, byte[] movieimage) {
        this.id = id;
        this.movivename = movivename;
        this.movieimage = movieimage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovivename() {
        return movivename;
    }

    public void setMovivename(String movivename) {
        this.movivename = movivename;
    }

    public byte[] getMovieimage() {
        return movieimage;
    }

    public void setMovieimage(byte[] movieimage) {
        this.movieimage = movieimage;
    }
}



