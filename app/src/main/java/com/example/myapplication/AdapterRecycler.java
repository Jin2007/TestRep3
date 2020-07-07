package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
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

    int rowPosition;
    int itemViewType;
    AlertDialog alertDialog;

    public AdapterRecycler(Context context, final List<Human> people){
        this.inflater = LayoutInflater.from(context);
        this.people = people;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Ты че, дурак?!")
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, final int id) {
                        // Use mListRowPosition for clicked list row...

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = App.getInstance().getDatabase();
                                HumanDao humanDao = db.humanDao();
                                humanDao.delete(people.get(rowPosition));
                                people.remove(rowPosition);
                            }
                        });
                        thread.start();
                        notifyItemRemoved(rowPosition);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object
        builder.setCancelable(false);
        alertDialog = builder.create();
    }

    void showDialog(int position){
        rowPosition = position;
        if (alertDialog != null){
            alertDialog.show();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(people.get(position).sex.equals("Male")) {
            itemViewType = R.layout.list_item;
        } else if (people.get(position).sex.equals("Female")){
            itemViewType = R.layout.list_female_item;
        }
        return itemViewType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "View type is " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(itemViewType, parent, false);
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
               showDialog(position);
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

        public MyViewHolder(@NonNull View v) {
            super(v);
            deleteButton = v.findViewById(R.id.deleteButton);
            imageView = v.findViewById(R.id.image);
            nameView = v.findViewById(R.id.name);
            secondNameView = v.findViewById(R.id.secondName);
            sexView = v.findViewById(R.id.sex);
        }
    }
}
