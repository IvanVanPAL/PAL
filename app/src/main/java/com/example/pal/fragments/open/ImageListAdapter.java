package com.example.pal.fragments.open;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pal.R;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<ImageList> {

    private LayoutInflater inflater;
    private int layout;
    private List<ImageList> images;

    public ImageListAdapter(Context context, int resource, List<ImageList> images) {
            super(context, resource, images);
            this.images = images;
            this.layout = resource;
            this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.imageItem);
        TextView nameView = view.findViewById(R.id.nameImageItem);

        ImageList  img = images.get(position);

        imageView.setImageDrawable(Drawable.createFromPath(img.getPathImg()));
        nameView.setText(img.getName());

        return view;
    }
}

