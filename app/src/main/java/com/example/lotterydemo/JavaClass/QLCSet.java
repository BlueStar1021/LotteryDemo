package com.example.lotterydemo.JavaClass;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/23.
 */

public class QLCSet {

    private ArrayList<Integer> normal;
    private int special;

    public QLCSet(String strFromJson) {
        String[] norSet = {"","","","","","",""};
        norSet[0] = strFromJson.substring(0,2);
        norSet[1] = strFromJson.substring(3,5);
        norSet[2] = strFromJson.substring(6,8);
        norSet[3] = strFromJson.substring(9,11);
        norSet[4] = strFromJson.substring(12,14);
        norSet[5] = strFromJson.substring(15,17);
        norSet[6] = strFromJson.substring(18,20);

        String spe = strFromJson.substring(21,23);

        this.normal = new ArrayList<>();

        for(int i = 0 ; i < 7 ; i++){
            normal.add(strParseToInt(norSet[i]));
        }

        this.special = strParseToInt(spe);
    }

    public ArrayList<Integer> getNormal() {
        return normal;
    }

    public int getSpecial() {
        return special;
    }

    public int strParseToInt(String str){
        int numOfTens = Integer.valueOf(str.substring(0,1));
        int numOfSingle = Integer.valueOf(str.substring(1,2));
        return numOfSingle + numOfTens * 10;
    }
}
