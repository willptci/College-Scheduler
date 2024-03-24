package com.example.collegescheduler2340;

import androidx.annotation.NonNull;

import java.util.Objects;

public class AssignmentData {
    private String title;
    private String day;
    private String month;
    private String year;
    private int hour;
    private int minutes;
    private String dueTime;
    private String dueDate;
    private String associatedClass;

    public AssignmentData(String title, String day, String month, String year,
                          int hour, int minutes, String associatedClass) {
        this.title = title;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
        this.dueTime = String.format("%02d:%02d", hour, minutes);
        this.dueDate = monthFormatter(this.month) + " " + this.day + " " + this.year;
        this.associatedClass = associatedClass;
    }

    private String monthFormatter(String Month) {
        if (month.equals("1") || month.equals("01")) { return "Jan"; }
        if (month.equals("2") || month.equals("02")) { return "Feb"; }
        if (month.equals("3") || month.equals("03")) { return "March"; }
        if (month.equals("4") || month.equals("04")) { return "April"; }
        if (month.equals("5") || month.equals("05")) { return "May"; }
        if (month.equals("6") || month.equals("06")) { return "June"; }
        if (month.equals("7") || month.equals("07")) { return "July"; }
        if (month.equals("8") || month.equals("08")) { return "Aug"; }
        if (month.equals("9") || month.equals("09")) { return "Sep"; }
        if (month.equals("10")) { return "Oct"; }
        if (month.equals("11")) { return "Nov"; }
        if (month.equals("12")) { return "Dec"; }
        return "Jan";
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AssignmentData other = (AssignmentData) obj;
        return hour == other.hour &&
                minutes == other.minutes &&
                Objects.equals(title, other.title) &&
                Objects.equals(day, other.day) &&
                Objects.equals(month, other.month) &&
                Objects.equals(year, other.year) &&
                Objects.equals(dueTime, other.dueTime) &&
                Objects.equals(dueDate, other.dueDate) &&
                Objects.equals(associatedClass, other.associatedClass);
    }

    @NonNull
    public String toString() {
        return "AssignmentData{" +
                "title='" + title + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", associatedClass='" + associatedClass + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(int hour, int minutes) {
        this.dueTime = String.format("%02d:%02d", hour, minutes);
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        if (dueDate != null && dueDate.contains("/")) {
            String[] parts = dueDate.split("/");
            if (parts.length == 3) {
                this.month = parts[0];
                this.day = parts[1];
                this.year = parts[2];

                this.dueDate = monthFormatter(month) + " " + day + " " + year;
            } else {
                this.dueDate = dueDate;
            }
        } else {
            this.dueDate = dueDate;
        }
    }
    public String getAssociatedClass() {
        return associatedClass;
    }

    public void setAssociatedClass(String associatedClass) {
        this.associatedClass = associatedClass;
    }
}
