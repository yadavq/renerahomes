package com.example.harshyadav.renerahomeslandlord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationAdapter extends ArrayAdapter<LocationItem> {
    public LocationAdapter(Context context, ArrayList<LocationItem> locationlist){
        super(context,0, locationlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.location_spinner_row,parent,false
            );
        }
        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView textView = convertView.findViewById(R.id.text_view);

        LocationItem currentItem = getItem(position);

        if (currentItem!=null) {
            imageView.setImageResource(currentItem.getMlocationimage());
            textView.setText(currentItem.getMlocationname());
        }

        return convertView;
    }
}
