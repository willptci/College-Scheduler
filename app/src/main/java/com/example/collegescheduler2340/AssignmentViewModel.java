package com.example.collegescheduler2340;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class AssignmentViewModel extends ViewModel {
    private ArrayList<AssignmentData> assignmentDataList = new ArrayList<>();
    private FragmentListener listener;

    public void setFragmentListener(FragmentListener listener) {
        this.listener = listener;
    }

    public ArrayList<AssignmentData> getAssignmentDataList() {
        return assignmentDataList;
    }

    public void setAssignmentDataList(ArrayList<AssignmentData> newList) {
        assignmentDataList = newList;
        Log.d("Will Parrish", "ViewModel");
        if (listener != null) {
            listener.onAssignmentDataChanged(newList);
        }
    }

    public interface FragmentListener {
        void onAssignmentDataChanged(ArrayList<AssignmentData> newList);
    }
}


