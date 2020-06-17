package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder> {
    private static final String TAG = "States";
    LayoutInflater inflater;
     List<Human> people;
//     private OnProfilePositionListener onProfilePositionListener;

    public AdapterRecycler(Context context, final List<Human> people){
        this.inflater = LayoutInflater.from(context);
        this.people = people;
//        this.onProfilePositionListener = onProfilePositionListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Human human = people.get(position);
        holder.nameView.setText(human.getName());
        holder.secondNameView.setText(human.getSecondName());
        holder.sexView.setText(human.getSex());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity mA1 = new MainActivity();
               mA1.showDialogMethod(position);
            }
        });
        Picasso.get().load(Uri.parse(human.getPhoto())).resize(150,150).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, secondNameView, sexView;
        Button deleteButton;
//        OnProfilePositionListener onProfilePositionListener;

        public MyViewHolder(@NonNull View v) {
            super(v);
            deleteButton = v.findViewById(R.id.deleteButton);
            imageView = v.findViewById(R.id.image);
            nameView = v.findViewById(R.id.name);
            secondNameView = v.findViewById(R.id.secondName);
            sexView = v.findViewById(R.id.sex);
//            this.onProfilePositionListener = onProfilePositionListener;
//            v.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            this.onProfilePositionListener.onProfileClick(getAdapterPosition());
//        }
    }

//    public interface OnProfilePositionListener{
//        void onProfileClick(int position);
//    }
}
