package com.jestarok.connect4;

/**
 * Created by mc on 9/30/2017.
 */

public class Chip {
    int id;
    int[] position;
    int val;

    public Chip(int x, int y){
        position = new int[2];
        position[0] = x;
        position[1] = y;
        val = 0;
    }

    public Chip(int id){
        id = id;
        val = 0;
    }
}
