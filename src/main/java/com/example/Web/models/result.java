package com.example.Web.models;

import java.util.List;
//Для хранения выбранных прилагательный или в целом для получения списков
public class result {
    private int[] selectedIds;
    private String[] selectedNames;

    public int[] getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(int[] selectedIds) {
        this.selectedIds = selectedIds;
    }

    public String[] getSelectedNames() {
        return selectedNames;
    }

    public void setSelectedNames(String[] selectedNames) {
        this.selectedNames = selectedNames;
    }

}
