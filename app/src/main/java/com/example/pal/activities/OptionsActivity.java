package com.example.pal.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.pal.R;

import java.util.Collections;

public class OptionsActivity extends Activity {


    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String pref_key_back;

    int[] backgrounds = new int[]{
            R.drawable.back1,
            R.drawable.back2,
            R.drawable.back3,
            R.drawable.back4,
            R.drawable.back5,
            R.drawable.back6,
            R.drawable.back7,
            R.drawable.back8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.options_activity);

        pref = PreferenceManager.getDefaultSharedPreferences(OptionsActivity.this);
        pref_key_back = this.getString(R.string.pref_save_back);
        if (pref.getInt(pref_key_back, 0) == 0) {
            editor = pref.edit();
            editor.putInt(pref_key_back, R.drawable.back2);
            editor.commit();
        }

    }
    //обработка кнопки "Назад"

    public void onImageClick(View view){
        switch (view.getId()){
            case R.id.img1:
                savePref(backgrounds[0]);
                finish();

                break;
            case R.id.img2:
                savePref(backgrounds[1]);
                finish();
                break;
            case R.id.img3:
                savePref(backgrounds[2]);
                finish();
                break;
            case R.id.img4:
                savePref(backgrounds[3]);
                finish();
                break;
            case R.id.img5:
                savePref(backgrounds[4]);
                finish();
                break;
            case R.id.img6:
                savePref(backgrounds[5]);
                finish();
                break;
            case R.id.img7:
                savePref(backgrounds[6]);
                finish();
                break;
            case R.id.img8:
                savePref(backgrounds[7]);
                finish();
                break;
        }
    }

    private void savePref(int id){

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(pref_key_back, id);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}