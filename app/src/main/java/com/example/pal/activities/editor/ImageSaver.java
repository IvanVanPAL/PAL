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
    //получение имени изображения
    public String getName() {
        return name;
    }
    // получение директории
    public String getPath() {
        return path;
    }
    //получение качества
    public int getQuality() {
        return quality;
    }

    //получение типа
    public Bitmap.CompressFormat getType() {
        return type;
    }

    //установка имени файла
    public void setName(String name) {
        this.name = name;
    }

    //установка качества файла
    public void setQuality(int quality) {
        this.quality = quality;
    }

    //установка директории
    public void setPath(String path) {
        this.path = path;
    }

    //установка типа
    public void setType(Bitmap.CompressFormat type) {
        this.type = type;
    }

    //сохранение файла
    public String saveImage(Bitmap bitmap) throws IOException {
        File image = new File(generateImagePath());
        image.createNewFile();
        FileOutputStream fos = new FileOutputStream(image);
        bitmap.compress(type, quality, fos);
        fos.close();
     return image.getPath();
    }

    //генерация полного пути изображения
    public String generateImagePath(){
        return path + name + "." + type;
    }
}
