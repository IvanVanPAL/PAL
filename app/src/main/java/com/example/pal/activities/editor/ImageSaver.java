package com.example.pal.activities.editor;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageSaver {

    String name;
    Bitmap.CompressFormat type;
    int quality;
    String path;

    public ImageSaver(String name, Bitmap.CompressFormat type, int quality, String path){
        this.name = name;
        this.type = type;
        this.quality = quality;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getQuality() {
        return quality;
    }

    public Bitmap.CompressFormat getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(Bitmap.CompressFormat type) {
        this.type = type;
    }

    public String saveImage(Bitmap bitmap) throws IOException {
        File image = new File(generateImagePath());
        image.createNewFile();
        FileOutputStream fos = new FileOutputStream(image);
        bitmap.compress(type, quality, fos);
        fos.close();

     return image.getPath();
    }

    public String generateImagePath(){
        return path + name + "." + type;
    }
}
