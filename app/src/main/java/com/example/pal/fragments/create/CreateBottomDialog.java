package com.example.pal.fragments.create;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.pal.R;
import com.example.pal.activities.editor.EditorActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.io.File;
import java.io.IOException;

public class CreateBottomDialog extends BottomSheetDialogFragment {

    // константа, обозначающая, что переход к редактору выполнен через CreateBottomDialog
    private final int FROM_CREATE = 0;

    String typeImg = ".JPEG";

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String pref_key_type;


    //обработка события появления фрагмента
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_create, container, false);

        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        pref_key_type = this.getString(R.string.pref_save_type);



        // переменные элементов активности привязываются к элементам из макета
        Button buttCreate = (Button) view.findViewById(R.id.buttModalOpen);
        EditText name = (EditText) view.findViewById(R.id.urlFile);
        RadioGroup radio = (RadioGroup) view.findViewById(R.id.radioGroupTypeImg);

        //включение флага типа JPG

        int idCheck = pref.getInt(pref_key_type, 0);
        if (idCheck == 0) {
            editor = pref.edit();
            editor.putInt(pref_key_type, R.id.radioJPG);
            editor.commit();
        }

        radio.check(idCheck);

        //установка слушателя на группу флагов
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    //если выбран JPG
                    case R.id.radioJPG:
                        typeImg = ".JPEG";
                        savePref(R.id.radioJPG);
                        break;
                    //если выбран PNG
                    case R.id.radioPNG:
                        typeImg = ".PNG";
                        savePref(R.id.radioPNG);
                        break;
                }
            }
        });

        //установка слушателя на кнопку создания файла
        buttCreate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                //создание папки с изображениями
                File dir = new File(getPath());

                //если папки нет, то она создается, иначе ничего не происходит
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdirs();
                }

                if(checkName(name.getText().toString())){
                    //передача информации для следующей активности
                    Intent intent = new Intent(getActivity(), EditorActivity.class);

                    //строка записывает под указаный ключ значение типа String
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("type", typeImg);
                    intent.putExtra("path", getPath());
                    intent.putExtra("from", String.valueOf(FROM_CREATE));
                    startActivity(intent);

                    //закрытие фрагмента
                    dismiss();
                }else {
                    Toast.makeText(getActivity(), "Введены запрещенные символы!", Toast.LENGTH_SHORT).show();

                }
        }});

        return view;
    }

    //метод получения из конфигурации приложения путя хранения изображения
    private String getPath() {
        //получение конфига приложения
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //название ключа берется из ресурсов
        String key = this.getString(R.string.pref_save_path_key);

        //запись в переменную пути из конфига приложения
        String path = pref.getString(key, null);

        //если path пустой, то создается конфиг приложения
        if (path == null) {
            // путь установлен на публичную папку устройства "Pictures"
            path = Environment.getExternalStoragePublicDirectory("Pictures") + File.separator + "PAL/";
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, path);
            editor.commit();
        }
        return path;
    }

    private void savePref(int id){

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(pref_key_type, id);
        editor.commit();
    }

    //Проверка имени на недопустимые символы
    public boolean checkName(String name){
        char[] badChar = new char[]{'/', '\'', '\"', '\n', ',', '.', '^', ':', ';', '?', '!', '(', ')'};
        int count = 11;

        if(name.length() == 0){ return false;}

        for (int i = 0; i < name.length(); i++) {
            for(int j : badChar){
                if (name.charAt(i) == j){
                    return false;
                }
            }
        }
        return true;
    }
}
