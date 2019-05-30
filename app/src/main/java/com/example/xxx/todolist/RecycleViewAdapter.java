package com.example.xxx.todolist;

import android.content.Context;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  {
   private List<Task> task = new LinkedList<>();
    private LayoutInflater Inflater;
    private ItemClickListener mClickListener;
    RecycleViewAdapter(Context context, List<Task> task) {
        this.Inflater = LayoutInflater.from(context);
this.task = task;
    }


    @Override

    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = Inflater.inflate(R.layout.recycle_grid, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.grid.setBackgroundResource(R.drawable.layout_bg_2);
        holder.timee.setTextColor(Color.WHITE);
        switch(task.get(position).getColor())
        {
            case 1:
            {
                holder.timee.setBackgroundResource(R.drawable.layout_bg);
                holder.namee.setTextColor(Color.parseColor("#8E0DCE"));
                holder.check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#8E0DCE")));

                break;
            }
            case 2:
            {
                holder.timee.setBackgroundResource(R.drawable.layout_bg3);
                holder.namee.setTextColor(Color.parseColor("#FF0000"));
                holder.check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                break;
            }
            case 3:
            {
                holder.timee.setBackgroundResource(R.drawable.layout_bg4);
                holder.namee.setTextColor(Color.parseColor("#009900"));
                holder.check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#009900")));
                break;
            }
            case 4:
            {
                holder.timee.setBackgroundResource(R.drawable.layout_bg4);
                holder.namee.setTextColor(Color.parseColor("#0080FF"));
                holder.check.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#0080FF")));
                break;
            }
        }
        if (task.get(position).getMinutes()<10)
{
    holder.timee.setText(task.get(position).getHours() + ":" + "0"+ task.get(position).getMinutes());
} else
{
    holder.timee.setText(task.get(position).getHours() + ":" + task.get(position).getMinutes());
}
        holder.namee.setText(task.get(position).getName());
if(task.get(position).getChecked()==1) {
    holder.namee.setPaintFlags(holder.namee.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    holder.check.setChecked(true);
}
    }


    @Override
    public int getItemCount() {
        return task.size();
    }

    public Task GetItem( int position) {return task.get(position);}


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView timee;
        TextView namee;
        CheckBox check;
        GridLayout grid;

        ViewHolder(View itemView) {
            super(itemView);
            timee = itemView.findViewById(R.id.time);
            namee = itemView.findViewById(R.id.name);
            check = itemView.findViewById(R.id.check);
            grid=itemView.findViewById(R.id.grid);
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if(buttonView.isChecked()==true)
                                                     namee.setPaintFlags(namee.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                                                     else
                                                        namee.setPaintFlags(0);

                                                 }
                                             }
            );
            check.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }
}




