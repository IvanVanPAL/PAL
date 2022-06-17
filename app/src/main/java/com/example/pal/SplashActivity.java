package com.example.pal;

import static android.os.Environment.getExternalStorageDirectory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pal.activities.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class SplashActivity extends AppCompatActivity {
    File imagesFolder;
    private final static String FILE_NAME = "document.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        File f = new File(getExternalStorageDirectory().getPath() + File.separator + "VK" + File.separator + "NewTextFile.txt");
        try(FileOutputStream fos = new FileOutputStream(f)) {

            String text = "123123123";

            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
            fos.close();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, String.valueOf(getExternalStorageDirectory()), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}


//    File f = new File(getExternalStorageDirectory().getP + File.separator + "VK" + File.separator + "NewTextFile.txt");
//        try(FileOutputStream fos = new FileOutputStream(f)) {
//
//            String text = "123123123";
//
//            fos.write(text.getBytes());
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//            fos.close();
//        }
//        catch(IOException ex) {
//
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
//        }