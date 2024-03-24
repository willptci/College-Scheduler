package com.example.collegescheduler2340;

public class ExamItem {
    private String name;
    private String dateTime;
    private String course;
    private String location;

    public ExamItem(String name, String dateTime, String course, String location) {
        this.name = name;
        this.dateTime = dateTime;
        this.course = course;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCourse() {
        return course;
    }

    public String getLocation() {
        return location;
    }
}
