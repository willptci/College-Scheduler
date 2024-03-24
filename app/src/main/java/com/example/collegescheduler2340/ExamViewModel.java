package com.example.collegescheduler2340;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class ExamViewModel extends ViewModel {
    private ArrayList<ExamItem> examItemList = new ArrayList<>();

    public ArrayList<ExamItem> getExamItemList() {
        return examItemList;
    }

    public void setExamItemList(ArrayList<ExamItem> examItemList) {
        this.examItemList = examItemList;
    }
}