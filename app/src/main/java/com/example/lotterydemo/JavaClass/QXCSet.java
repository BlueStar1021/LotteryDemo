package com.example.lotterydemo.JavaClass;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/23.
 */

public class QXCSet {

    private ArrayList<Integer> balls;

    public QXCSet(String strFromJson) {

        String[] ballSet = {"","","","","","",""};

        ballSet[0] = strFromJson.substring(0,1);
        ballSet[1] = strFromJson.substring(2,3);
        ballSet[2] = strFromJson.substring(4,5);
        ballSet[3] = strFromJson.substring(6,7);
        ballSet[4] = strFromJson.substring(8,9);
        ballSet[5] = strFromJson.substring(10,11);
        ballSet[6] = strFromJson.substring(12,13);

        this.balls = new ArrayList<>();

        for(int i = 0 ; i < 7 ; i++){
            this.balls.add(Integer.valueOf(ballSet[i]));
        }

    }

    public ArrayList<Integer> getBalls() {
        return balls;
    }
}
