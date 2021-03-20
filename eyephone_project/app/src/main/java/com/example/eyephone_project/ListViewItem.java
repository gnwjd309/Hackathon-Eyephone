package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListViewItem {

    private String titleStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
}