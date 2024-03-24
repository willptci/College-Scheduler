package com.example.collegescheduler2340;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Comparator;

public class AssignmentsFragment extends Fragment {

    private AssignmentViewModel viewModel;
    private AssignmentsAdapter adapter;

    public AssignmentsFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AssignmentViewModel.class);
        ArrayList<AssignmentData> AssignmentDataList = viewModel.getAssignmentDataList();

        adapter = new AssignmentsAdapter(requireContext(), AssignmentDataList, viewModel);

        RecyclerView assignmentRecyclerView = view.findViewById(R.id.recyclerViewAssignments);
        createRecyclerView(assignmentRecyclerView, AssignmentDataList);

        AssignmentDataList.add(new AssignmentData("HW1", "10", "05", "2024", 8, 10, "CS 2340"));
        AssignmentDataList.add(new AssignmentData("HW2", "11", "04", "2024", 9, 30, "CS 2340"));
        AssignmentDataList.add(new AssignmentData("HW3", "12", "06", "2024", 10, 40, "CS 2340"));
        AssignmentDataList.add(new AssignmentData("HW4", "13", "07", "2023", 11, 50, "CS 2340"));


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button addButton = view.findViewById(R.id.btnAddAssignment);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAssignmentPopUp();
            }
        });
    }

    private void createRecyclerView(RecyclerView r, ArrayList<AssignmentData> list) {
        r.setLayoutManager(new LinearLayoutManager(requireContext()));
        r.setAdapter(adapter);
    }

    private void openAddAssignmentPopUp() {
        Intent addAssignmentIntent = new Intent(requireContext(), AssignmentAdd.class);
        startActivity(addAssignmentIntent);
    }


    public void sortListByDateTime(ArrayList<AssignmentData> assignmentList) {
        assignmentList.sort(new Comparator<AssignmentData>() {
            @Override
            public int compare(AssignmentData o1, AssignmentData o2) {
                if (!o1.getYear().equals(o2.getYear())) {
                    return o1.getYear().compareTo(o2.getYear());
                }
                else if (!o1.getMonth().equals(o2.getMonth())) {
                    return o1.getMonth().compareTo(o2.getMonth());
                }
                else if (!o1.getDay().equals(o2.getDay())) {
                    return o1.getDay().compareTo(o2.getDay());
                }
                else if (o1.getHour() != o2.getHour()) {
                    return Integer.compare(o1.getHour(), o2.getHour());
                }
                else {
                    return Integer.compare(o1.getMinutes(), o2.getMinutes());
                }
            }
        });

        AssignmentsAdapter.setData(assignmentList);
        adapter.notifyDataSetChanged();
    }
}



