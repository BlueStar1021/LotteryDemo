package com.example.lotterydemo.JavaClass;

import android.util.Log;

import java.util.ArrayList;

/**
 * Java类：双色球组合
 *
 * Created by dell on 2019/5/14.
 */

public class SSQSet {

    private ArrayList<Integer> red;
    private int blue;

    public SSQSet(ArrayList<Integer> red, int blue) {

        //起泡排序使红色球号码由小到大
        for(int i = 0 ; i < red.size() - 1 ; i++){
            for(int j = 0 ; j < red.size() - 1 ; j++){
                if( red.get(j) > red.get(j+1) ){
                    int temp = red.get(j+1);
                    red.set(j+1, red.get(j));
                    red.set(j, temp);
                }
            }
        }

        this.red = red;
        this.blue = blue;
    }

    public SSQSet(int red_1, int red_2, int red_3, int red_4, int red_5, int red_6, int blue){

        this.red.add(red_1);
        this.red.add(red_2);
        this.red.add(red_3);
        this.red.add(red_4);
        this.red.add(red_5);
        this.red.add(red_6);

        //起泡排序使红色球号码由小到大
        for(int i = 0 ; i < this.red.size() - 1 ; i++){
            for(int j = 0 ; j < this.red.size() - 1 ; j++){
                if( this.red.get(j) > this.red.get(j+1) ){
                    int temp = this.red.get(j+1);
                    this.red.set(j+1, this.red.get(j));
                    this.red.set(j, temp);
                }
            }
        }

        this.blue = blue;
    }

    public SSQSet(String strFromJson){
        String[] redSet = {"","","","","",""};
        redSet[0] = strFromJson.substring(0,2);
        redSet[1] = strFromJson.substring(3,5);
        redSet[2] = strFromJson.substring(6,8);
        redSet[3] = strFromJson.substring(9,11);
        redSet[4] = strFromJson.substring(12,14);
        redSet[5] = strFromJson.substring(15,17);
        String blue = strFromJson.substring(18,20);

        this.red = new ArrayList<>();

        for(int i = 0 ; i < 6 ; i++){
            this.red.add(strParseToInt(redSet[i]));
        }
        this.blue = strParseToInt(blue);

    }

    public ArrayList<Integer> getRed() {
        return red;
    }

    public void setRed(ArrayList<Integer> red) {
        //起泡排序使红色球号码由小到大
        for(int i = 0 ; i < red.size() - 1 ; i++){
            for(int j = 0 ; j < red.size() - 1 ; j++){
                if( red.get(j) > red.get(j+1) ){
                    int temp = red.get(j+1);
                    red.set(j+1, red.get(j));
                    red.set(j, temp);
                }
            }
        }

        this.red = red;
    }



    public void setRed(int red_1, int red_2, int red_3, int red_4, int red_5, int red_6) {

        this.red.add(red_1);
        this.red.add(red_2);
        this.red.add(red_3);
        this.red.add(red_4);
        this.red.add(red_5);
        this.red.add(red_6);

        //起泡排序使红色球号码由小到大
        for(int i = 0 ; i < this.red.size() - 1 ; i++){
            for(int j = 0 ; j < this.red.size() - 1 ; j++){
                if( this.red.get(j) > this.red.get(j+1) ){
                    int temp = this.red.get(j+1);
                    this.red.set(j+1, this.red.get(j));
                    this.red.set(j, temp);
                }
            }
        }
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int strParseToInt(String str){
        int numOfTens = Integer.valueOf(str.substring(0,1));
        int numOfSingle = Integer.valueOf(str.substring(1,2));
        return numOfSingle + numOfTens * 10;
    }

}
