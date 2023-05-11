package com.example.Web;

public class AdjectiveCount {
    private String traitName;
    private Long count;

    public AdjectiveCount(String traitName, Long count) {
        this.traitName = traitName;
        this.count = count;
    }

    public String gettraitName() {
        return traitName;
    }

    public void settraitName(String traitName) {
        this.traitName = traitName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
