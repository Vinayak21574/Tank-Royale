package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.io.*;

public class Loadstate {
    Play play;

    public Loadstate(Play play) {
        this.play = play;
    }
    public void save(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            Savestate s = new Savestate();
//            s.play=this.play;
//            s.TankL=play.tankL;
            s.x0= play.tankL.getX0();
            s.y0= play.tankL.getY0();
//            s.mine=play.tankL.mine;
            s.health1= play.tankL.getHealth();
            s.fuel1= play.tankL.getFuel();
//            s.TankR=play.tankR;
            s.x1= play.tankR.getX0();
            s.y1= play.tankR.getY0();
//            s.opp=play.tankR.mine;
            s.health2= play.tankR.getHealth();
            s.fuel2= play.tankR.getFuel();
            s.scale=play.current.scale;
            s.turn= play.turn;
            oos.writeObject(s);
        }
        catch(Exception e){
            System.out.println("save state exception");
        }
    }
    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            Savestate s = (Savestate)ois.readObject();
            play.tankL.setX0(s.x0);
            play.tankL.setY0(s.y0);
            play.tankL.setHealth(s.health1);
            play.tankL.setFuel(s.fuel1);
//            s.TankR=play.tankR;
            play.tankR.setX0(s.x1);
            play.tankR.setY0(s.y1);
            play.tankR.setHealth(s.health2);
            play.tankR.setFuel(s.fuel2);
            play.current.scale=s.scale;
            play.turn=s.turn;
        }
        catch(Exception e){
            System.out.println("Load state exception "+ e);
        }
    }
}