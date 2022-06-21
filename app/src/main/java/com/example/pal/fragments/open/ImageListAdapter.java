package com.example.pal.fragments.open;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pal.R;

import java.io.File;
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

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        View view=inflater.inflate(this.layout, parent, false);

        ImageList  img = images.get(position);

        viewHolder.imageView.setImageDrawable(Drawable.createFromPath(img.getPathImg()));
        viewHolder.nameView.setText(img.getName());

        viewHolder.delButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File imgDel = new File(img.getPathImg());
                imgDel.delete();
                images.remove(position);
                notifyDataSetChanged();

            }
        });

        return convertView;
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView nameView;
        final Button delButt;
        ViewHolder(View view){
            imageView = view.findViewById(R.id.imageItem);
            nameView = view.findViewById(R.id.nameImageItem);
            delButt = view.findViewById(R.id.delButt);
        }
    }
}

