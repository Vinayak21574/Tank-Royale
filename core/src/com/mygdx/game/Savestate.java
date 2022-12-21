package com.mygdx.game;

import java.io.Serializable;
import java.util.ArrayList;

public class Savestate implements Serializable {
    //    Tank TankL;
    int x0,y0;
    //    Healthbar mine;
    float health1;
    float fuel1;
    //    Tank TankR;
    int x1,y1;
    //    Healthbar opp;
    float health2;
    float fuel2;
    ArrayList<Integer> scale;
    boolean turn;
//    Play play;
}