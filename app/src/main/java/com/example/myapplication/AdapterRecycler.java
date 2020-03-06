package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {
     LayoutInflater inflater;
     List<Human> people;

    public AdapterRecycler(Context context, List<Human> people){
        this.inflater = LayoutInflater.from(context);
        this.people = people;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Human human = people.get(position);
        holder.nameView.setText(human.getName());
        holder.secondNameView.setText(human.getSecondName());
        holder.sexView.setText(human.getSex());

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameView, secondNameView, sexView;
        public MyViewHolder(@NonNull View v) {
            super(v);
            imageView = v.findViewById(R.id.image);
            nameView = v.findViewById(R.id.name);
            secondNameView = v.findViewById(R.id.secondName);
            sexView = v.findViewById(R.id.sex);
            Picasso.get().load("https://icdn.lenta.ru/images/2018/05/30/22/20180530221332205/detail_fb46603ad5bb18f91ecab39bc1a6c5b4.jpg").into(imageView);
        }
    }
}
