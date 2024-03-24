package com.example.collegescheduler2340;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    private Context context;
    private ArrayList<ClassData> classDataList;

    public ClassAdapter(Context context, ArrayList<ClassData> classDataList) {
        this.context = context;
        this.classDataList = classDataList;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_card, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassViewHolder holder, int position) {
        holder.bind(classDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return classDataList.size();
    }

    public void add(ClassData newData) {
        classDataList.add(newData);
        notifyItemInserted(classDataList.size() - 1);
        notifyDataSetChanged();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView classNameView;
        TextView classNumberView;
        TextView classStartView;
        TextView classEndView;
        TextView classInstructorView;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classNameView = itemView.findViewById(R.id.className);
            classNumberView = itemView.findViewById(R.id.classNumber);
            classInstructorView = itemView.findViewById(R.id.classInstructor);
            classStartView = itemView.findViewById(R.id.classStart);
            classEndView = itemView.findViewById(R.id.classEnd);
        }

        public void bind(ClassData classData) {
            classNameView.setText(classData.getName());
            classNumberView.setText(classData.getNumber());
            classInstructorView.setText(classData.getInstructor());
            classStartView.setText(classData.getStart());
            classEndView.setText(classData.getEnd());
        }
    }
}


