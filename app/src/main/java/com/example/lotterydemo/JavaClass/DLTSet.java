package com.example.lotterydemo.JavaClass;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/22.
 */

public class DLTSet {

    private ArrayList<Integer> prozone;
    private ArrayList<Integer> postzone;

    public DLTSet(String strFromJson) {

        String[] proSet = {"","","","",""};
        String[] postSet = {"",""};

        proSet[0] = strFromJson.substring(0,2);
        proSet[1] = strFromJson.substring(3,5);
        proSet[2] = strFromJson.substring(6,8);
        proSet[3] = strFromJson.substring(9,11);
        proSet[4] = strFromJson.substring(12,14);

        postSet[0] = strFromJson.substring(15,17);
        postSet[1] = strFromJson.substring(18,20);

        this.prozone = new ArrayList<>();
        this.postzone = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            this.prozone.add(strParseToInt(proSet[i]));
        }

        for(int i = 0 ; i < 2 ; i++){
            this.postzone.add(strParseToInt(postSet[i]));
        }

    }

    public ArrayList<Integer> getProzone() {
        return prozone;
    }

    public ArrayList<Integer> getPostzone() {
        return postzone;
    }

    public int strParseToInt(String str){
        int numOfTens = Integer.valueOf(str.substring(0,1));
        int numOfSingle = Integer.valueOf(str.substring(1,2));
        return numOfSingle + numOfTens * 10;
    }
}
