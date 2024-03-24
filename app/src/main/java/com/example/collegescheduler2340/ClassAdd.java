package com.example.collegescheduler2340;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ClassAdd extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_popup);

        createPopUp();

        Button saveButton = findViewById(R.id.ClassEditDone);
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
        EditText editClassTitle = findViewById(R.id.popupEditClass);
        EditText editClassNumber = findViewById(R.id.popupEditClassNumber);
        EditText editClassInstructor = findViewById(R.id.popupEditClassInstructor);
        EditText editClassStartTime = findViewById(R.id.popupEditStartTime);
        EditText editClassEndTime = findViewById(R.id.editClassEndTime);
        EditText editClassDay = findViewById(R.id.editClassDay);

        String newTitle = editClassTitle.getText().toString();
        String newNumber = editClassNumber.getText().toString();
        String newInstructor = editClassInstructor.getText().toString();
        String newStartTime = editClassStartTime.getText().toString();
        String newEndTime = editClassEndTime.getText().toString();
        String newDay = editClassDay.getText().toString();


        if (newTitle.isEmpty() || newNumber.isEmpty() || newInstructor.isEmpty() || newStartTime.isEmpty() || newEndTime.isEmpty() || newDay.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

        } else if (!newDay.equals("M") && !newDay.equals("T") && !newDay.equals("W") && !newDay.equals("TH") && !newDay.equals("F")) {
            Toast.makeText(this, "Invalid Day", Toast.LENGTH_SHORT).show();
        }else {
            String[] startTimeParts = newStartTime.split(":");
            String[] endTimeParts = newEndTime.split(":");

            if (startTimeParts.length == 2 && endTimeParts.length == 2) {
                try {
                    int startHours = Integer.parseInt(startTimeParts[0]);
                    int startMinutes = Integer.parseInt(startTimeParts[1]);
                    int endHours = Integer.parseInt(endTimeParts[0]);
                    int endMinutes = Integer.parseInt(endTimeParts[1]);

                    if (startHours >= 0 && startHours <= 23 && startMinutes >= 0 && startMinutes <= 59 && endHours >= 0 && endHours <= 23 && endMinutes >= 0 && endMinutes <= 59) {

                        ClassData newClass = new ClassData(newTitle, newNumber, startHours, startMinutes, endHours, endMinutes, newInstructor, newDay);

                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("newClassData", newClass);
                        setResult(Activity.RESULT_OK, resultIntent);

                        Log.d("Will Parrish", "class add ");

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
