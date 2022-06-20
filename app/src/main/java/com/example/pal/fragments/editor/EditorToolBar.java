package com.example.pal.fragments.editor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pal.R;
import com.example.pal.activities.editor.EditorActivity;
import com.example.pal.activities.editor.ImageSaver;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;

public class EditorToolBar extends BottomSheetDialogFragment {

    ImageSaver imageSaver;

    Button saveImage;

    String nameImage;
    String typeImage;
    String pathImage;
    int quality = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_menu_sheet, container, false);
        Bundle arguments = getActivity().getIntent().getExtras();

        EditorActivity activity = (EditorActivity) getActivity();

        if(arguments != null){
            nameImage = arguments.get("name").toString();
            typeImage = arguments.get("type").toString();
            pathImage = arguments.get("path").toString();

        }
        imageSaver = new ImageSaver(nameImage, getType(typeImage), quality, pathImage);

        saveImage = (Button) view.findViewById(R.id.saveButt);

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    imageSaver.setName(nameImage);
                    imageSaver.setType(getType(typeImage));
                    imageSaver.setPath(pathImage);
                    imageSaver.setQuality(quality);

                    String path = imageSaver.saveImage(activity.getCanvas());

                    Toast.makeText(activity, "Рисунок " + path + " сохранен!", Toast.LENGTH_SHORT).show();

                }catch (IOException e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }


            }
        });



        return view;
    }
    private Bitmap.CompressFormat getType(String type){
        switch (type) {
            case ".JPEG":
                return Bitmap.CompressFormat.JPEG;
            default:
                return Bitmap.CompressFormat.PNG;
        }
    }
}
