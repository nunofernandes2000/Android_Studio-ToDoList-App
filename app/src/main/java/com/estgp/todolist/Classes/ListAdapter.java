package com.estgp.todolist.Classes;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estgp.todolist.ListDetails;
import com.estgp.todolist.MainActivity;
import com.estgp.todolist.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public static final String EXTRA_SELECT_LIST = "LIST_TASK";


    private ArrayList<task> Tasklist;
    private Activity activity;
    private  LayoutInflater inflater;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView listName;
        //public TextView listdate;
        public TextView listDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listName = itemView.findViewById(R.id.tv_list_name);
            //listdate = itemView.findViewById(R.id.tv_list_Date);
            listDescription = itemView.findViewById(R.id.tv_list_description);


        }
    }

    public ListAdapter(MainActivity mainActivity, ArrayList<task> tasklist, Activity activity) {
        this.Tasklist = tasklist;
        this.activity = activity;
        this.inflater = LayoutInflater.from(mainActivity);
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.task_list_rows, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

        task tasklist = Tasklist.get(position);
        holder.listName.setText(tasklist.getName());
        holder.listDescription.setText(tasklist.getDescription());

        //OnClick
        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(activity, ListDetails.class);
            intent.putExtra(EXTRA_SELECT_LIST, tasklist);
            activity.startActivity(intent);


        });


    }

    @Override
    public int getItemCount() {
        return Tasklist.size();

    }
}




