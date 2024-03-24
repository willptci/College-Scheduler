package com.example.collegescheduler2340;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;


public class AssignmentAdd extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignments_popup);

        createPopUp();

        Button saveButton = findViewById(R.id.assignmentEditDone);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
    }

    public void createPopUp() {
        setTheme(R.style.popMe);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width), (int) (height * .5));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }

    public void saveChanges() {
        EditText titleEditText = findViewById(R.id.popupEditAssignmentTitle);
        EditText dueDateEditText = findViewById(R.id.popupEditAssignmentDate);
        EditText timeEditText = findViewById(R.id.popupEditAssignmentTime);
        EditText classEditText = findViewById(R.id.popupEditAssignmentclass);

        String newTitle = titleEditText.getText().toString();
        String newDueDate = dueDateEditText.getText().toString();
        String newTime = timeEditText.getText().toString();
        String newClass = classEditText.getText().toString();

        if (newTitle.isEmpty() || newDueDate.isEmpty() || newTime.isEmpty() || newClass.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            String[] timeParts = newTime.split(":");
            String[] dateParts = newDueDate.split("/");

            if (timeParts.length == 2 && dateParts.length == 3) {
                try {
                    int hours = Integer.parseInt(timeParts[0]);
                    int minutes = Integer.parseInt(timeParts[1]);
                    String month = dateParts[0];
                    String day = dateParts[1];
                    String year = dateParts[2];

                    if (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59) {

                        AssignmentData newAssignment = new AssignmentData(newTitle, day, month, year, hours, minutes, newClass);

                        ArrayList<AssignmentData> assignmentsDataList = AssignmentsAdapter.getAssignmentsDataList();
                        assignmentsDataList.add(newAssignment);

                        ToDoViewModel toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
                        toDoViewModel.addTaskFromAssignments(newAssignment);

                        AssignmentsAdapter.setData(assignmentsDataList);

                        finish();
                    } else {
                        Toast.makeText(this, "Invalid time range", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid numeric input", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Invalid time or date format", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
