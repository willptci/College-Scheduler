package com.example.collegescheduler2340;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ClassViewHolder extends RecyclerView.ViewHolder {
    TextView classNameView;
    TextView classNumberView;
    TextView classStartView;
    TextView classEndView;
    TextView classInstructorView;

    public ClassViewHolder(@NonNull View itemView) {
        super(itemView);
        classNameView
                = (TextView)itemView
                .findViewById(R.id.className);
        classNumberView
                = (TextView)itemView
                .findViewById(R.id.classNumber);
        classInstructorView
                = (TextView)itemView
                .findViewById(R.id.classInstructor);
        classStartView
                = (TextView)itemView
                .findViewById(R.id.classStart);
        classEndView
                = (TextView)itemView
                .findViewById(R.id.classEnd);
    }
}
