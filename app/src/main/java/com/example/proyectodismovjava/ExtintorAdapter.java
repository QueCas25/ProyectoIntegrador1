package com.example.proyectodismovjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExtintorAdapter extends ArrayAdapter<ExtintoresModel> {
    public ExtintorAdapter(Context context, int resource, List<ExtintoresModel> extintoresModels){
        super(context, resource, extintoresModels);
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExtintoresModel extintor = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_view_row,parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.textView);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvType = (TextView) convertView.findViewById(R.id.textView3);

        tvName.setText(extintor.getExtintorName());
        tvPrice.setText(extintor.getExtintorPrice());
        tvType.setText(extintor.getExtintorType());
        imageView.setImageResource(extintor.getImage());

        return convertView;
    }
}
