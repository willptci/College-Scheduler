//class details fragment
package com.example.collegescheduler2340;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class ClassDetailsFragment extends Fragment {
    private ArrayList<ClassData> mondayDataList;
    private ArrayList<ClassData> tuesdayDataList;
    private ArrayList<ClassData> wednesdayDataList;
    private ArrayList<ClassData> thursdayDataList;
    private ArrayList<ClassData> fridayDataList;
    private ClassViewModel viewModel;
    private ActivityResultLauncher<Intent> launcher;
    private ClassAdapter mondayAdapter;
    private ClassAdapter tuesdayAdapter;
    private ClassAdapter wednesdayAdapter;
    private ClassAdapter thursdayAdapter;
    private ClassAdapter fridayAdapter;


    public ClassDetailsFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_details, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ClassViewModel.class);

        mondayDataList = viewModel.getMondayDataList();
        tuesdayDataList = viewModel.getTuesdayDataList();
        wednesdayDataList = viewModel.getWednesdayDataList();
        thursdayDataList = viewModel.getThursdayDataList();
        fridayDataList = viewModel.getFridayDataList();

        mondayAdapter = new ClassAdapter(requireContext(), new ArrayList<>());
        tuesdayAdapter = new ClassAdapter(requireContext(), new ArrayList<>());
        wednesdayAdapter = new ClassAdapter(requireContext(), new ArrayList<>());
        thursdayAdapter = new ClassAdapter(requireContext(), new ArrayList<>());
        fridayAdapter = new ClassAdapter(requireContext(), new ArrayList<>());

        InitializeRecyclerView(view);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    ClassData updatedClassData = (ClassData) data.getSerializableExtra("newClassData");
                    setViewModelArray(updatedClassData);
                }
            }
        });

        Button addClassButton = view.findViewById(R.id.addClassButton);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClassAddPopUp();
            }
        });

        return view;
    }

    private void openClassAddPopUp() {
        Intent addClassIntent = new Intent(requireContext(), ClassAdd.class);
        launcher.launch(addClassIntent);
    }

    public void setViewModelArray(ClassData data) {
        if (data.getDay().equals("M")) {
            Log.d("Will Parrish", "monday ");
            viewModel.addMondayClass(data);
            mondayAdapter.add(data);

        } else if (data.getDay().equals("T")) {
            Log.d("Will Parrish", "tuesday ");
            viewModel.addTuesdayClass(data);
            tuesdayAdapter.add(data);
        } else if (data.getDay().equals("W")) {
            Log.d("Will Parrish", "wednesday ");
            viewModel.addWednesdayClass(data);
            wednesdayAdapter.add(data);
        } else if (data.getDay().equals("TH")) {
            Log.d("Will Parrish", "thursday ");
            viewModel.addThursdayClass(data);
            thursdayAdapter.add(data);
        } else if (data.getDay().equals("F")) {
            Log.d("Will Parrish", "friday ");
            viewModel.addFridayClass(data);
            fridayAdapter.add(data);
        } else {
            Log.d("Will Parrish", "Did not set day");
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();

    }

    private void dataInitialize() {

        sortListByTime(mondayDataList);
        sortListByTime(tuesdayDataList);
        sortListByTime(wednesdayDataList);
        sortListByTime(thursdayDataList);
        sortListByTime(fridayDataList);
    }

    private void InitializeRecyclerView(View view) {
        RecyclerView mondayRecyclerView = view.findViewById(R.id.mondayRecyclerView);
        RecyclerView tuesdayRecyclerView = view.findViewById(R.id.tuesdayRecyclerView);
        RecyclerView wednesdayRecyclerView = view.findViewById(R.id.wednesdayRecyclerView);
        RecyclerView thursdayRecyclerView = view.findViewById(R.id.thursdayRecyclerView);
        RecyclerView fridayRecyclerView = view.findViewById(R.id.fridayRecyclerView);

        createRecyclerView(mondayRecyclerView, mondayDataList, mondayAdapter);
        createRecyclerView(tuesdayRecyclerView, tuesdayDataList, tuesdayAdapter);
        createRecyclerView(wednesdayRecyclerView, wednesdayDataList, wednesdayAdapter);
        createRecyclerView(thursdayRecyclerView, thursdayDataList, thursdayAdapter);
        createRecyclerView(fridayRecyclerView, fridayDataList, fridayAdapter);
    }
    private void createRecyclerView(RecyclerView r, ArrayList<ClassData> list, ClassAdapter adapter) {
        r.setLayoutManager(new LinearLayoutManager(requireContext()));
        r.setAdapter(adapter);
        r.setAdapter(new ClassAdapter(requireContext(), list));
    }

    private void sortListByTime(List<ClassData> classList) {
        classList.sort((o1, o2) -> {
            if (o1.getStartHour() != o2.getStartHour()) {
                return Integer.compare(o1.getStartHour(), o2.getStartHour());
            } else if (o1.getStartMinutes() != o2.getStartMinutes()) {
                return Integer.compare(o1.getStartMinutes(), o2.getStartMinutes());
            } else if (o1.getEndHour() != o2.getEndHour()) {
                return Integer.compare(o1.getEndHour(), o2.getEndHour());
            } else {
                return Integer.compare(o1.getEndMinutes(), o2.getEndMinutes());
            }
        });
    }
}