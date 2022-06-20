package com.example.pal.fragments.create;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
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

    private final int FROM_CREATE = 0;


    String typeImg = ".JPEG";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_create, container, false);

        Button buttCreate = (Button) view.findViewById(R.id.buttModalOpen);
        EditText name = (EditText) view.findViewById(R.id.urlFile);
        RadioGroup radio = (RadioGroup) view.findViewById(R.id.radioGroupTypeImg);
        radio.check(R.id.radioJPG);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioJPG:
                        typeImg = ".JPEG";
                        break;
                    case R.id.radioPNG:
                        typeImg = ".PNG";
                        break;
                }
            }
        });



        buttCreate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                File dir = new File(getPath());
                if (!dir.exists() || !dir.isDirectory()) {
                    dir.mkdirs();
                }


//                File imageFile = new File(dir.getAbsolutePath(), name.getText().toString() + typeImg);
//                if(!imageFile.exists() || !imageFile.isFile()){
//                    try {
//                        imageFile.createNewFile();
//                        Toast.makeText(getActivity(),
//                                "Рисунок \"" + imageFile.getAbsolutePath() + "\" создан!!!", Toast.LENGTH_SHORT)
//                                .show();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        Toast.makeText(getActivity(),
//                                e.toString(), Toast.LENGTH_SHORT)
//                                .show();
//                    }
//                }
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("type", typeImg);
                intent.putExtra("path", getPath());
                intent.putExtra("from", String.valueOf(FROM_CREATE));
                startActivity(intent);
                dismiss();

        }});

        return view;
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
