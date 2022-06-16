package com.example.pal.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.pal.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OpenBottomDialog extends BottomSheetDialogFragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_open, container, false);

        EditText path = (EditText) view.findViewById(R.id.urlFile);
        Button folder = (Button) view.findViewById(R.id.buttFolder);
        Button open = (Button) view.findViewById(R.id.buttModalOpen);

        folder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                fileIntent.setType("*/*");
                startActivityForResult(fileIntent, 10);
            }
        });

        return view;
    }
}
