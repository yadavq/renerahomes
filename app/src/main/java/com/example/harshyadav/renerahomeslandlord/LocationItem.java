package com.example.harshyadav.renerahomeslandlord;

public class LocationItem {
    private String mlocationname;
    private int mlocationimage;

    public LocationItem(String locationname, int locationimage){
        mlocationimage = locationimage;
        mlocationname = locationname;
    }

    public String getMlocationname() {
        return mlocationname;
    }

    public int getMlocationimage(){
        return mlocationimage;
    }
}
