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
import com.example.pal.activities.OpenFolderActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OpenBottomDialogTest extends BottomSheetDialogFragment {
    private static final String PREFS_FILE = "Config";
    private static final String PREF_NAME = "PathLastOpenFile";

    EditText pathFolder;
    Button folder;
    Button open;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_open, container, false);

        pathFolder = (EditText) view.findViewById(R.id.urlFile);
        folder = (Button) view.findViewById(R.id.buttFolder);
        open = (Button) view.findViewById(R.id.buttModalOpen);

        folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), OpenFolderActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }


}
