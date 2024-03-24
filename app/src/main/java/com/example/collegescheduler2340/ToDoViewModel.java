package com.example.collegescheduler2340;

import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ToDoViewModel extends ViewModel {
    private ArrayList<ToDoTask> taskArrayList = new ArrayList<>();

    public ArrayList<ToDoTask> getTaskArrayList() {
        return taskArrayList;
    }

    public void setTaskArrayList(ArrayList<ToDoTask> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public void setTaskCompleted(int position) {
        if (position >= 0 && position < taskArrayList.size()) {
            taskArrayList.get(position).setCompleted(!taskArrayList.get(position).getCompleted());
        }
    }

    public void addTaskFromExam(ExamItem exam) {
        String taskName = exam.getName();
        String taskDate = exam.getDateTime();
        String taskCourse = exam.getCourse();

        ToDoTask newTask = new ToDoTask(taskName, parseDate(taskDate), taskCourse, "No Location");
        taskArrayList.add(newTask);
    }

    public void addTaskFromAssignments(AssignmentData assignment) {
        String taskName = assignment.getTitle();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(assignment.getYear()));
        calendar.set(Calendar.MONTH, Integer.parseInt(assignment.getMonth()) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(assignment.getDay()));

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        String taskDate = sdf.format(calendar.getTime());

        String taskCourse = assignment.getAssociatedClass();

        ToDoTask newTask = new ToDoTask(taskName, parseDate(taskDate), taskCourse, "No Location");
        taskArrayList.add(newTask);
    }

    private Date parseDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault());
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}