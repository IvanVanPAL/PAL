package com.example.pal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;



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
