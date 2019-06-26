package com.example.lotterydemo.JavaClass;

import java.util.ArrayList;

/**
 * Created by dell on 2019/5/23.
 */

public class FCSDSet {

    private ArrayList<Integer> balls;

    public FCSDSet(String strFromJson) {

        String[] ballSet = {"","",""};

        ballSet[0] = strFromJson.substring(0,1);
        ballSet[1] = strFromJson.substring(2,3);
        ballSet[2] = strFromJson.substring(4,5);

        this.balls = new ArrayList<>();

        for(int i = 0 ; i < 3 ; i++){
            this.balls.add(Integer.valueOf(ballSet[i]));
        }

    }

    public ArrayList<Integer> getBalls() {
        return balls;
    }
}
