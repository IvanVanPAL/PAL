package com.example.pal.fragments.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pal.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class EditorToolBar extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tools_menu_sheet, container, false);



        return view;
    }
}
