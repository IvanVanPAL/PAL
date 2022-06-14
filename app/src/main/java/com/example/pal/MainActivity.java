package com.example.pal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void onOpenClick(View view){
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        startActivity(intent);
    }
    public void onExitClick(View view){
        finish();
        System.exit(0);
    }


}