package com.example.collegescheduler2340;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.AssignmentsViewHolder> {
    private Context context;
    private static ArrayList<AssignmentData> assignmentsDataList;
    private static AssignmentViewModel viewModel;

    public AssignmentsAdapter(Context context, ArrayList<AssignmentData> assignmentsDataList, AssignmentViewModel viewModel) {
        this.context = context;
        this.assignmentsDataList = assignmentsDataList;
        this.viewModel = viewModel;
    }

    public static void setData(ArrayList<AssignmentData> newData) {
        assignmentsDataList = newData;
        viewModel.setAssignmentDataList(newData);
        Log.d("Will Parrish", "setData");
    }

    @NonNull
    @Override
    public AssignmentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assignmet_card, viewGroup, false);
        return new AssignmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentsViewHolder viewHolder, final int position) {
        AssignmentData assignment = assignmentsDataList.get(position);
        viewHolder.bind(assignment);
    }

    @Override
    public int getItemCount() {
        return assignmentsDataList.size();
    }

    public static ArrayList<AssignmentData> getAssignmentsDataList() {
        return assignmentsDataList;
    }

    public class AssignmentsViewHolder extends RecyclerView.ViewHolder {
        TextView assignmentTitleView;
        TextView assignmentDueDateView;
        TextView assignmentAssociatedClass;
        TextView assignmentTimeView;
        Button assignmentEdit;
        Button assignmentDelete;
        Button assignmentComplete;
        View rootView;

        public AssignmentsViewHolder(@NonNull View itemView) {
            super(itemView);
            rootView = itemView;
            assignmentTitleView = itemView.findViewById(R.id.assignmentTitle);
            assignmentDueDateView = itemView.findViewById(R.id.assignmentDueDate);
            assignmentAssociatedClass = itemView.findViewById(R.id.assignmentAssociatedClass);
            assignmentTimeView = itemView.findViewById(R.id.assignmentTime);
            assignmentEdit = itemView.findViewById(R.id.assignmentEdit);
            assignmentDelete = itemView.findViewById(R.id.assignmentDelete);
            assignmentComplete = itemView.findViewById(R.id.assignmentComplete);
            assignmentEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPopUpWindow(getAdapterPosition());
                }
            });
            assignmentDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                }
            });
            assignmentComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {removeItem(getAdapterPosition());}
            });
        }

        public void bind(AssignmentData assignment) {
            assignmentTitleView.setText(assignment.getTitle());
            assignmentDueDateView.setText(assignment.getDueDate());
            assignmentAssociatedClass.setText(assignment.getAssociatedClass());
            assignmentTimeView.setText(assignment.getDueTime());
        }

        private void openPopUpWindow(int position) {
            Intent popUpWindow = new Intent(context, AssignmentAdd.class);
            popUpWindow.putExtra("position", position);
            context.startActivity(popUpWindow);
            removeItem(position);
        }

        private void removeItem(int position) {
            assignmentsDataList.remove(position);
            notifyItemRemoved(position);
            viewModel.setAssignmentDataList(assignmentsDataList);
        }

    }
}




