package com.example.pal.fragments.open;

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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.util.ArrayList;


public class OpenBottomDialog extends BottomSheetDialogFragment {
    Button open;
    ArrayList<ImageList> images = new ArrayList<ImageList>();
    ListView imageList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_open, container, false);

        setInitialData();
        open = (Button) view.findViewById(R.id.buttModalOpen);

        imageList = (ListView) view.findViewById(R.id.imageList);

        ImageListAdapter adapter = new ImageListAdapter(getActivity(), R.layout.image_item_list, images);

        imageList.setAdapter(adapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                ImageList selectedImage = (ImageList)parent.getItemAtPosition(position);
                imageList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                Toast.makeText(getActivity(), "Был выбран пункт " + selectedImage.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        imageList.setOnItemClickListener(itemListener);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getActivity(), "test", Toast.LENGTH_LONG);
                toast.show();
            }
        });



        return view;
    }

    private void setInitialData(){
        File dir = new File(getPath());
        File[] files = dir.listFiles();

        if(files.length != 0) {
            for (int i = 0; i < files.length; i++) {
                images.add(new ImageList(files[i].getName(), files[i].getAbsolutePath()));

            }
        }
    }

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