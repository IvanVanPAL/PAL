package com.example.pal.fragments.open;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pal.R;
import com.example.pal.activities.editor.EditorActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class OpenBottomDialog extends BottomSheetDialogFragment {

    // константа, обозначающая, что переход к редактору выполнен через OpenBottomDialog
    private final int FROM_OPEN = 1;

    //объявление списка изображений класса ImageList
    ArrayList<ImageList> images = new ArrayList<ImageList>();
    ListView imageList;
    String nameFile;
    String pathFile;
    String typeFile;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_open, container, false);

        setInitialData();


        imageList = (ListView) view.findViewById(R.id.imageList);
        imageList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //объявление кастомного адаптера списка
        ImageListAdapter adapter = new ImageListAdapter(getActivity(), R.layout.image_item_list, images);

        //установка адаптера для списка
        imageList.setAdapter(adapter);

        //установка слушателя для списка
        imageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ImageList selectedImage = (ImageList)parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), EditorActivity.class);
                intent.putExtra("name", selectedImage.getName());
                intent.putExtra("type", selectedImage.getTypeImg());
                intent.putExtra("path",getPath());
                intent.putExtra("from", FROM_OPEN);

                //вызов уведомления, который показывает имя выбранного элемента
                Toast.makeText(getActivity(), "Был выбран файл " + selectedImage.getName(),
                        Toast.LENGTH_SHORT).show();
                startActivity(intent);
                dismiss();
            }
        });

        return view;
    }

    //выборка файлов из директории хранения
    private void setInitialData(){
        File dir = new File(getPath());

        //получение количества файлов
        File[] files = dir.listFiles();

        //если количество файлов не равно нулю, то в массив записываются данные о файлах
        if(files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                nameFile = files[i].getName().replaceFirst("[.][^.]+$", "");
                pathFile = files[i].getAbsolutePath();
                typeFile = files[i].getAbsolutePath().substring(files[i].getAbsolutePath().lastIndexOf("."));
                images.add(new ImageList(nameFile, pathFile, typeFile));

            }
            //иначе выводит сообщение, что файлов нет
        }else Toast.makeText(getActivity(), "Ничего нет :(", Toast.LENGTH_LONG).show();
    }

    // метод получение пути
    private String getPath() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String key = this.getString(R.string.pref_save_path_key);
        String path = pref.getString(key, null);
        if (path == null) {
            path = Environment.getExternalStoragePublicDirectory("Pictures") + File.separator + "PAL/";
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(key, path);
            editor.commit();
        }
        return path;
    }
}