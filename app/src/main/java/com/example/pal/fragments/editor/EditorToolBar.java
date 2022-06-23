package com.example.pal.fragments.editor;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import com.example.pal.R;
import com.example.pal.activities.editor.EditorActivity;
import com.example.pal.activities.editor.ImageSaver;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorShape;

import java.io.IOException;

public class EditorToolBar extends BottomSheetDialogFragment {

    //режимы рисования
    private final int MODE_DRAW_FREE = 21;
    private final int MODE_DRAW_HEART = 22;

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

        //здесь происходит смена выделения кнопки инструмента рисования
        switch (editorActivity.getModeStatus()){
            //выбрана свободная кисть
            case MODE_DRAW_FREE:
                if(editorActivity.getColorPaint() == Color.BLACK){
                    drawFreeImage.setTextColor(Color.WHITE);
                    drawFreeImage.setBackgroundColor(Color.BLACK);
                }else if(editorActivity.getColorPaint() == Color.WHITE){
                    drawFreeImage.setTextColor(Color.BLACK);
                    drawFreeImage.setBackgroundColor(Color.WHITE);
                }else {
                    drawFreeImage.setTextColor(Color.BLACK);
                    drawFreeImage.setBackgroundColor(editorActivity.getColorPaint());
                }
                drawHeartImage.setTextColor(Color.BLACK);
                drawHeartImage.setBackgroundColor(Color.WHITE);
                break;
                //выбраны сердца
            case MODE_DRAW_HEART:
                if(editorActivity.getColorPaint() == Color.BLACK){
                    drawHeartImage.setTextColor(Color.WHITE);
                    drawHeartImage.setBackgroundColor(Color.BLACK);
                }else if(editorActivity.getColorPaint() == Color.WHITE){
                    drawHeartImage.setTextColor(Color.BLACK);
                    drawHeartImage.setBackgroundColor(Color.WHITE);
                }else {
                    drawHeartImage.setTextColor(Color.BLACK);
                    drawHeartImage.setBackgroundColor(editorActivity.getColorPaint());
                }
                drawFreeImage.setTextColor(Color.BLACK);
                drawFreeImage.setBackgroundColor(Color.WHITE);
                break;

        }
        //изменение кнопки выбора цвета
        if(editorActivity.getColorPaint() == Color.BLACK){
            colorPicker.setTextColor(Color.WHITE);
            colorPicker.setBackgroundColor(Color.BLACK);
        }else if(editorActivity.getColorPaint() == Color.WHITE){
            colorPicker.setTextColor(Color.BLACK);
            colorPicker.setBackgroundColor(Color.WHITE);
        }else {
            colorPicker.setTextColor(Color.BLACK);
            colorPicker.setBackgroundColor(editorActivity.getColorPaint());
        }

        if(arguments != null){
            nameImage = arguments.get("name").toString();
            typeImage = arguments.get("type").toString();
            pathImage = arguments.get("path").toString();
        }

        imageSaver = new ImageSaver(nameImage, getType(typeImage), quality, pathImage);

        //сохранение изображения
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    imageSaver.setName(nameImage);
                    imageSaver.setType(getType(typeImage));
                    imageSaver.setPath(pathImage);
                    imageSaver.setQuality(quality);
                    String path = imageSaver.saveImage(editorActivity.getCanvas());

                    Toast.makeText(editorActivity, "Рисунок " + path + " сохранен!", Toast.LENGTH_SHORT).show();

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
                editorActivity.setMode(MODE_DRAW_HEART);
                dismiss();

            }
        });

        drawFreeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editorActivity.setMode(MODE_DRAW_FREE);
                dismiss();
            }
        });

        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createColorPickerDialog(0);
                dismiss();
            }
        });

        return view;
    }


    //вызов диалогового окна выбора цвета из скаченной библиотеки ColorPickerDialog
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

    //полчуение типа изображения
    public Bitmap.CompressFormat getType(String type){
        switch (type) {
            case ".JPEG":
                return Bitmap.CompressFormat.JPEG;
            default:
                return Bitmap.CompressFormat.PNG;
        }
    }
}
