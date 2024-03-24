package com.example.collegescheduler2340;

import java.io.Serializable;
public class ClassData implements Serializable {
    String name;
    String number;
    int startHour;
    int startMinutes;
    int endHour;
    int endMinutes;
    String instructor;
    String day;
    String start;
    String end;

    public ClassData(String name, String number,
                     int startHour, int startMinutes,
                     int endHour, int endMinutes,
                     String instructor, String day) {
        this.name = name;
        this.number = number;
        this.start = String.format("%02d:%02d", startHour, startMinutes);
        this.end = String.format("%02d:%02d", endHour, endMinutes);
        this.instructor = instructor;
        this.day = day;
        this.startHour = startHour;
        this.startMinutes = startMinutes;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) { this.number = number; }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public void setStartMinutes(int startMinutes) {
        this.startMinutes = startMinutes;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public void setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDay() { return day; }

    public void setDay(String day) { this.day = day; }

    public String toString() {
        return "Name: " + name + ", Number: " + number + ", Instructor: " + instructor + ", Day: " + day;
    }
}
