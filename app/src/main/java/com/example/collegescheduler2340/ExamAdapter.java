package com.example.collegescheduler2340;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ExamItem> examItemList;

    public ExamAdapter(Context context, ArrayList<ExamItem> examItemList) {
        this.context = context;
        this.examItemList = examItemList;
    }

    @Override
    public int getCount() {
        return examItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return examItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_exam_item, parent, false);
        }

        TextView examItemName = convertView.findViewById(R.id.examItemName);
        TextView examItemDateTime = convertView.findViewById(R.id.examItemDateTime);
        TextView examItemCourse = convertView.findViewById(R.id.examItemCourse);
        TextView examItemLocation = convertView.findViewById(R.id.examItemLocation);
        ImageView trashIcon = convertView.findViewById(R.id.trashIcon);

        ExamItem currentExam = examItemList.get(position);

        if (currentExam != null) {
            examItemName.setText(currentExam.getName());
            examItemDateTime.setText(currentExam.getDateTime());
            examItemCourse.setText(currentExam.getCourse());
            examItemLocation.setText(currentExam.getLocation());
        }

        trashIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examItemList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}