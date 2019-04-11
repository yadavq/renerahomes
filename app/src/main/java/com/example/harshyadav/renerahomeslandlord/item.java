package com.example.harshyadav.renerahomeslandlord;

import android.util.Log;

public class item {

    int background;
    String type;

    public item(){
    }

    public item(int background, String type) {
        this.background = background;
        this.type = type;
    }

    public int getBackground() {
        return background;
    }

    public String getType() {
        return type;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setType(String type) {
        this.type = type;
    }
}
