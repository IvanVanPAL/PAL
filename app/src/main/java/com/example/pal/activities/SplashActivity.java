package com.example.pal.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.pal.R;
import com.example.pal.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    // данный метод срабатывает при создании активности
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // данная строка запрещает переход приложения в темную тему,
        // если на самом утсройстве она включена
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // установка темы
        setTheme(R.style.SplashTheme);

        //переход на новую активность MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //если активность больше не нужна, ее необходимо закрыть,
        // чтобы ОЗУ-память устройства не переполнялась
        finish();
    }
}