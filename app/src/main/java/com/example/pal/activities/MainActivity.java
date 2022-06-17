package com.example.pal.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pal.fragments.CreateBottomDialog;
import com.example.pal.R;
import com.example.pal.fragments.CreateDialogFragment;
import com.example.pal.fragments.OpenBottomDialog;

import net.rdrei.android.dirchooser.DirectoryChooserActivity;
import net.rdrei.android.dirchooser.DirectoryChooserConfig;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_DIRECTORY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_main);


    }


    public void onOpenClick(View view){
        final OpenBottomDialog bottomDialog = new OpenBottomDialog();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }


    public void onCreateClick(View view){
        final CreateBottomDialog bottomDialog = new CreateBottomDialog();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }



//    public void viewDialogCreate(View view){
//        CreateDialogFragment dialog = new CreateDialogFragment();
//        dialog.show(getSupportFragmentManager(), "custom");
//    }

    public void onOptionsClick(View view){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        finish();
        System.exit(0);
    }

}