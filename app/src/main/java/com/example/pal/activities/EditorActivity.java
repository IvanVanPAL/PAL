package com.example.pal.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.pal.R;


public class EditorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
    }
    public void onEditorClick(View view){
        finish();
    }
}
