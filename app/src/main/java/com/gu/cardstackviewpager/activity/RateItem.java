package com.gu.cardstackviewpager.activity;

public class RateItem {
    private int id;
    private String movName;
    private String movHref;

    public RateItem() {
        this.movName = "";
        this.movHref = "";
    }

    public RateItem(String movName, String movHref) {
        this.movName = movName;
        this.movHref = movHref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovName() {
        return movName;
    }

    public void setMovName(String movName) {
        this.movName = movName;
    }

    public String getMovHref() {
        return movHref;
    }

    public void setMovHref(String movHref) {
        this.movHref = movHref;
    }
}
