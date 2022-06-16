package com.example.pal.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.pal.R;
import com.example.pal.activities.EditorActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CreateBottomDialog extends BottomSheetDialogFragment {
    String typeImg;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_create, container, false);

        Button buttCreate = (Button) view.findViewById(R.id.buttModalOpen);
        EditText name = (EditText) view.findViewById(R.id.urlFile);
        RadioGroup radio = (RadioGroup) view.findViewById(R.id.radioGroupTypeImg);
        radio.clearCheck();
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioJPG:
                        typeImg = ".jpg";
                        break;
                    case R.id.radioPNG:
                        typeImg = ".png";
                        break;
                }
            }
        });



        buttCreate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(),
                    "Рисунок \"" + name.getText() +  typeImg + "\" создан!!!", Toast.LENGTH_SHORT)
                    .show();
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                startActivity(intent);
                dismiss();

        }});

        return view;
    }


}
