package com.example.pal.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pal.fragments.CreateBottomDialog;
import com.example.pal.R;
import com.example.pal.fragments.CreateDialogFragment;
import com.example.pal.fragments.OpenBottomDialog;

public class MainActivity extends AppCompatActivity {

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



    public void viewDialogCreate(View view){
        CreateDialogFragment dialog = new CreateDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void onOptionsClick(View view){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        finish();
        System.exit(0);
    }


}