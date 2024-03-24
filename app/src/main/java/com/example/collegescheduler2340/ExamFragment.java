package com.example.collegescheduler2340;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class ExamFragment extends Fragment {

    private ExamViewModel viewModel;
    private ExamAdapter examAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(ExamViewModel.class);

        ArrayList<ExamItem> examItemList = viewModel.getExamItemList();

        examAdapter = new ExamAdapter(requireContext(), examItemList);

        ListView listView = view.findViewById(R.id.examListView);

        listView.setAdapter(examAdapter);

        Button addButton = view.findViewById(R.id.buttonAddExam);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addNewExam()) {
                    examAdapter.notifyDataSetChanged();

                    ToDoViewModel toDoViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
                    toDoViewModel.addTaskFromExam(examItemList.get(examItemList.size() - 1));
                } else {
                    Toast.makeText(requireContext(), "Exam name is required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean addNewExam() {
        EditText examNameEditText = requireView().findViewById(R.id.editTextExamName);
        EditText examDateEditText = requireView().findViewById(R.id.editTextExamDate);
        EditText examTimeEditText = requireView().findViewById(R.id.editTextExamTime);
        EditText examLocationEditText = requireView().findViewById(R.id.editTextExamLocation);
        EditText examCourseEditText = requireView().findViewById(R.id.editTextExamCourse);

        String examName = examNameEditText.getText().toString().trim();
        if (examName.isEmpty()) {
            examNameEditText.setError("Exam name is required");
            return false;
        }

        String examDate = examDateEditText.getText().toString().trim();
        String examTime = examTimeEditText.getText().toString().trim();
        String examLocation = examLocationEditText.getText().toString().trim();
        String examCourse = examCourseEditText.getText().toString().trim();

        if (examDate.isEmpty() && examTime.isEmpty()) {
            examDate = getCurrentDateTime();
        } else if (examDate.isEmpty()) {
            examDate = getCurrentDate() + " " + examTime;
        } else if (examTime.isEmpty()) {
            examDate += " " + getCurrentTime();
        } else {
            examDate = examDate + " " + examTime;
        }

        if (examLocation.isEmpty()) {
            examLocation = "No Location";
        }

        if (examCourse.isEmpty()) {
            examCourse = "No Course";
        }

        ExamItem newExam = new ExamItem(examName, formatDateTime(examDate), examCourse, examLocation);
        viewModel.getExamItemList().add(newExam);
        examAdapter.notifyDataSetChanged();

        examNameEditText.setText("");
        examDateEditText.setText("");
        examTimeEditText.setText("");
        examLocationEditText.setText("");
        examCourseEditText.setText("");

        return true;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String formatDateTime(String inputDateTime) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
            Date date = inputFormat.parse(inputDateTime);

            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}