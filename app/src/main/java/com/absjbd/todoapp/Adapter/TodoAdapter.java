package com.absjbd.todoapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.absjbd.todoapp.Model.TodoModel;
import com.absjbd.todoapp.R;

import java.util.ArrayList;

/**
 * Created by abs pc1 on 2017-08-26.
 */

public class TodoAdapter extends ArrayAdapter<TodoModel> {

    public TodoAdapter(Context context, ArrayList<TodoModel> todoModels) {
        super(context,0, todoModels);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoModel todo = getItem(position);


        if(convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customitem_todo, parent, false);
        }

        TextView tvTodoName = (TextView) convertView.findViewById(R.id.tvTodoName);
        TextView tvDueDate = (TextView) convertView.findViewById(R.id.tvTodoDate);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

        tvTodoName.setText(todo.getTodoName());
        tvDueDate.setText(todo.getDueDate());
        tvStatus.setText(todo.getStatus());

        if(todo.getPriority().equals("High")){
            tvPriority.setTextColor(Color.parseColor("#f44336"));
        }else if(todo.getPriority().equals("Medium")){
            tvPriority.setTextColor(Color.parseColor("#f9a825"));
        }else{
            tvPriority.setTextColor(Color.parseColor("#4caf50"));
        }

        tvPriority.setText(todo.getPriority());


        return convertView;
    }



}
