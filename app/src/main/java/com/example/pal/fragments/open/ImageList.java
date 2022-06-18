package com.example.pal.fragments.open;

public class ImageList {

    private String name;
    private String pathImg;
    private int res;

    public ImageList(String name, String pathImg, int res){

        this.name = name;
        this.pathImg = pathImg;
        this.res = res;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public int getFlagResource() {
        return this.res;
    }

    public void setFlagResource(int res) {
        this.res = res;
    }
}