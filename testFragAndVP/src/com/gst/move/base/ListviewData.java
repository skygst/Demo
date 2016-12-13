package com.gst.move.base;

import java.util.List;

/**
 * Created by gst-pc on 2016/11/3.
 */

public class ListviewData {
    private String name;
    private List<GridViewData> gridData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GridViewData> getGridData() {
        return gridData;
    }

    public void setGridData(List<GridViewData> gridData) {
        this.gridData = gridData;
    }
}
