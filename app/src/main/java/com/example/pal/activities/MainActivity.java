package com.example.pal.activities;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pal.fragments.create.CreateBottomDialog;
import com.example.pal.R;
import com.example.pal.fragments.open.OpenBottomDialog;

public class MainActivity extends AppCompatActivity {
    //код проверки прав доступа, значение может быть каким угодно
    private static final int STORAGE_PERMISSION_CODE = 101;
    //Создание конфига приложения
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String pref_key_back;
    int backImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //установка макета для активности, на котором находятся
        // элементы управления
        setContentView(R.layout.activity_main);

        //взятие настроек из конфига
        pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        pref_key_back = this.getString(R.string.pref_save_back);

        backImage = pref.getInt(pref_key_back, 0);

        //если конфига еще не было, то его надо создать
        if (backImage == 0) {
            editor = pref.edit();
            editor.putInt(pref_key_back, R.drawable.back2);
            editor.commit();
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageBack);
        imageView.setImageResource(backImage);

    }
    // обработка кнопки "Открыть"
    public void onOpenClick(View view){
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        final OpenBottomDialog bottomDialog = new OpenBottomDialog();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }

    // обработка кнопки "Создать"
    public void onCreateClick(View view){

        //при первом запуске приложения необходимо запросить
        // права доступа к памяти на создание и чтение файлов
        checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);

        //вызов выдвигающегося меню Создания проекта
        final CreateBottomDialog bottomDialog = new CreateBottomDialog();
        bottomDialog.show(getSupportFragmentManager(), "ModalBottomSheet");
    }

    // обработка кнопки "Настройки"
    public void onOptionsClick(View view){
        // переход к Настройкам
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
        finish();
    }

    // обработка кнопки "Выход"
    public void onExitClick(View view){
        finish();

        //данная строка завершает работу приложения
        System.exit(0);
    }

    // метод запроса на проверку прав доступа к памяти устройства
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
    }

    // обработка события, когда приходит ответ на запрос прав доступа"
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //вызов всплывающего уведомления
                // если есть права доступа
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                //если прав нет
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}