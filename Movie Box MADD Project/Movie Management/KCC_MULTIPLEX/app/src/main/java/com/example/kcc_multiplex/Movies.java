package com.example.kcc_multiplex;

public class Movies {
    private int id;
    private String movivename;
    private String movietype;
    private String moviestatus;
    private String moviehours;
    private String description;
    byte[] movieimage;
    byte[] coverimage;


    public Movies(int id, String movivename, byte[] movieimage) {
        this.id = id;
        this.movivename = movivename;
        this.movieimage = movieimage;
    }

    public void MoivesUpdate(int id,String movivename,String movietype,String moviestatus,String moviehours,String description,byte[] movieimage,byte[] coverimage){

        this.id=id;
        this.movivename=movivename;
        this.movietype=movietype;
        this.moviehours=moviehours;
        this.moviestatus=moviestatus;
        this.description=description;
        this.movieimage=movieimage;
        this.coverimage=coverimage;

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



