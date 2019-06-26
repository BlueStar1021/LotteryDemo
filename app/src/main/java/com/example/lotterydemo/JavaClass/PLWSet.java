package com.example.lotterydemo.JavaClass;

import java.util.ArrayList;

/**
 * Created by BlueStar on 2019/6/24.
 */

public class PLWSet {

    private ArrayList<Integer> balls;

    public PLWSet(String strFromJson) {

        String[] ballSet = {"","","","",""};

        ballSet[0] = strFromJson.substring(0,1);
        ballSet[1] = strFromJson.substring(2,3);
        ballSet[2] = strFromJson.substring(4,5);
        ballSet[3] = strFromJson.substring(6,7);
        ballSet[4] = strFromJson.substring(8,9);

        this.balls = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            this.balls.add(Integer.valueOf(ballSet[i]));
        }

    }

    public ArrayList<Integer> getBalls() {
        return balls;
    }

}
