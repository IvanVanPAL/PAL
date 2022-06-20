package com.example.pal.fragments.open;

public class ImageList {

    private String name;
    private String pathImg;
    private String typeImg;

    public ImageList(String name, String pathImg, String typeImg){

        this.name = name;
        this.pathImg = pathImg;
        this.typeImg = typeImg;
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

    public String getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }
}