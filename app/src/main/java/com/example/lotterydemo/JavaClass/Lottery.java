package com.example.lotterydemo.JavaClass;

/**
 * Created by BlueStar on 2019/6/26.
 */

public class Lottery {

    private String no;                                                                             //期号
    private String date;                                                                           //开奖日期

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "no='" + no + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
