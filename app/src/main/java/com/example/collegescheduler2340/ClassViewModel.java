package com.example.collegescheduler2340;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ClassViewModel extends ViewModel {

    private ArrayList<ClassData> mondayDataList = new ArrayList<>();
    private ArrayList<ClassData> tuesdayDataList = new ArrayList<>();;
    private ArrayList<ClassData> wednesdayDataList = new ArrayList<>();;
    private ArrayList<ClassData> thursdayDataList = new ArrayList<>();;
    private ArrayList<ClassData> fridayDataList = new ArrayList<>();;

    public ArrayList<ClassData> getMondayDataList() {
        return mondayDataList;
    }

    public ArrayList<ClassData> getTuesdayDataList() {
        return tuesdayDataList;
    }

    public ArrayList<ClassData> getWednesdayDataList() {
        return wednesdayDataList;
    }

    public ArrayList<ClassData> getThursdayDataList() {
        return thursdayDataList;
    }

    public ArrayList<ClassData> getFridayDataList() {
        return fridayDataList;
    }

    public void setMondayDataList(ArrayList<ClassData> newList) {
        mondayDataList = newList;
    }

    public void setTuesdayDataList(ArrayList<ClassData> newList) {
        tuesdayDataList = newList;
    }

    public void setWednesdayDataList(ArrayList<ClassData> newList) {
        wednesdayDataList = newList;
    }

    public void setThursdayDataList(ArrayList<ClassData> newList) {
        thursdayDataList = newList;
    }

    public void setFridayDataList(ArrayList<ClassData> newList) {
        fridayDataList = newList;
    }

    public void addMondayClass(ClassData classData) {
        mondayDataList.add(classData);
    }

    public void addTuesdayClass(ClassData classData) {
        tuesdayDataList.add(classData);
    }

    public void addWednesdayClass(ClassData classData) {
        wednesdayDataList.add(classData);
    }

    public void addThursdayClass(ClassData classData) {
        thursdayDataList.add(classData);
    }

    public void addFridayClass(ClassData classData) {
        fridayDataList.add(classData);
    }
}

