package com.example.collegescheduler2340;

import java.util.Date;

public class ToDoTask implements Comparable<ToDoTask> {
    private String text;
    private boolean completed;
    private Date date;
    private String course;
    private String location;

    public ToDoTask(String text, Date date, String course, String location) {
        this.text = text;
        this.date = date;
        this.course = course;
        this.completed = false;
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDate() {
        return date;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public int compareTo(ToDoTask otherTask) {
        return this.date.compareTo(otherTask.getDate());
    }
}
