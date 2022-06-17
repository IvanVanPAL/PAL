package com.example.pal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.pal.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import net.rdrei.android.dirchooser.DirectoryChooserConfig;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;

public class OpenBottomDialogTest2 extends BottomSheetDialogFragment implements
        DirectoryChooserFragment.OnFragmentInteractionListener{


    EditText pathFolder;
    Button folder;
    Button open;
    DirectoryChooserFragment mDialog;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_open, container, false);

        final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                .newDirectoryName("DialogSample")
                .build();
        mDialog = DirectoryChooserFragment.newInstance(config);

        pathFolder = (EditText) view.findViewById(R.id.urlFile);
        folder = (Button) view.findViewById(R.id.buttFolder);
        open = (Button) view.findViewById(R.id.buttModalOpen);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show(getActivity().getFragmentManager(), null);
            }
        });



        return view;
    }


    @Override
    public void onSelectDirectory(String path) {
        pathFolder.setText(path);
        mDialog.dismiss();
    }

    @Override
    public void onCancelChooser() {
        mDialog.dismiss();
    }
}
