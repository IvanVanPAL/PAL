package com.example.pal.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class OpenFolderActivityTest extends Activity {


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