package com.example.pal.fragments.open;

public class ImageList {

    private String name;
    private String pathImg;

    public ImageList(String name, String pathImg){

        this.name = name;
        this.pathImg = pathImg;
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
}