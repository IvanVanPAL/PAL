package com.example.pal.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pal.R;

import java.io.File;
import java.util.ArrayList;

public class OpenFolderActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("*/*");
        Intent f = Intent.createChooser(fileIntent, "2222");
        startActivityForResult(f, 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 10:
                if(resultCode == RESULT_OK){
                    String pathFile = data.getData().getEncodedPath();
                    System.out.println(pathFile);
                    Toast toast = Toast.makeText(this, pathFile, Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
        finish();
    }
}