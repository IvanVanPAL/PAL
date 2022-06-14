package com.example.pal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);
    }
    public void onOptionClick(View view){
        finish();
    }
}