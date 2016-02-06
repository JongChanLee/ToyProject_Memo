package com.tistory.wproject.fragmentpractice;

/**
 * Created by Lee on 2016-02-06.
 */
public class MemoItem {
    private int id;
    private String memo;
    private String state;

    public MemoItem(int id, String memo, String state) {
        this.id = id;
        this.memo = memo;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
