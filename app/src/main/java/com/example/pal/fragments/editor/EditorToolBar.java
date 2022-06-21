package com.example.pal.fragments.editor;

import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorShape;

import java.io.IOException;

public class EditorToolBar extends BottomSheetDialogFragment {

    ImageSaver imageSaver;
    EditorActivity editorActivity;

    Button saveImage;
    Button drawHeartImage;
    Button drawFreeImage;
    Button colorPicker;


    String nameImage;
    String typeImage;
    String pathImage;
    int quality = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_menu_sheet, container, false);
        Bundle arguments = getActivity().getIntent().getExtras();
        saveImage = (Button) view.findViewById(R.id.saveButt);
        drawHeartImage = (Button) view.findViewById(R.id.drawHeartButt);
        drawFreeImage = (Button) view.findViewById(R.id.drawFreeButt);
        colorPicker = (Button) view.findViewById(R.id.colorButt);

        editorActivity = (EditorActivity) getActivity();

        EditorActivity activity = (EditorActivity) getActivity();

        if(arguments != null){
            nameImage = arguments.get("name").toString();
            typeImage = arguments.get("type").toString();
            pathImage = arguments.get("path").toString();
        }

        imageSaver = new ImageSaver(nameImage, getType(typeImage), quality, pathImage);

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
                editorActivity.setSaveChange(editorActivity.NO_CHANGES);
                dismiss();
            }
        });

        drawHeartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editorActivity.setMode(editorActivity.MODE_DRAW_HEART);
                dismiss();

            }
        });

        drawFreeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editorActivity.setMode(editorActivity.MODE_DRAW_FREE);
                dismiss();
            }
        });

        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createColorPickerDialog(0);
            }
        });

        return view;
    }



    private void createColorPickerDialog(int id) {
        ColorPickerDialog.newBuilder()
                .setColor(Color.RED)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setAllowCustom(true)
                .setAllowPresets(true)
                .setColorShape(ColorShape.SQUARE)
                .setDialogId(id)
                .show(getActivity());
    }

    public Bitmap.CompressFormat getType(String type){
        switch (type) {
            case ".JPEG":
                return Bitmap.CompressFormat.JPEG;
            default:
                return Bitmap.CompressFormat.PNG;
        }
    }
}
