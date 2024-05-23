package com.example.screen_ui.models;

import java.util.Collections;
import java.util.List;

public class ItemClass {
    private List<Integer> drawableList;
    private String title;
    private String subTitle;

    public ItemClass(List<Integer> drawableList, String title, String subTitle) {
        this.drawableList = drawableList;
        this.title = title;
        this.subTitle = subTitle;
    }

    public List<Integer> getDrawableList() {
        return drawableList;
    }

    public void setDrawableList(List<Integer> drawableList) {
        this.drawableList = drawableList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}