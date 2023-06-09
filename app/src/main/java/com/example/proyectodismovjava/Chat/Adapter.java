package com.example.proyectodismovjava.Chat;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodismovjava.R;
import com.example.proyectodismovjava.VideoLlamada;
import com.example.proyectodismovjava.models.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<HolderMsg>{

    private List<Message> listMsg = new ArrayList<>();
    private Context c;

    public Adapter(Context c) {
        this.c = c;
    }

    public void addMsg(Message m){
        listMsg.add(m);
        notifyItemInserted(listMsg.size());
    }

    @NonNull
    @Override
    public HolderMsg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.msg,parent,false);
        return new HolderMsg(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMsg holder, int position) {
        holder.getName().setText(listMsg.get(position).getName());
        holder.getMsg().setText(listMsg.get(position).getMsg());
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holder.getHour().setText(sdf.format(d));
        int p = position;
        holder.getImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             VideoLlamada context = listMsg.get(p).context;
                context.sendCallRequest(listMsg.get(p).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMsg.size();
    }
}
