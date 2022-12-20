package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class Terrain {
    ArrayList<Integer> scale=new ArrayList<>();
    MyGdxGame game;
    Texture rock=new Texture("Terrain\\Rock.png");
    Sprite Rock=new Sprite(rock);
    int limit=100;

//    int height=0;

    public Terrain(MyGdxGame game){
        this.game=game;
        initialise();
    }

    void initialise(){
        Random rnd=new Random();
        int range,center=0;

        for(int i=0;i<1600;i++){
            scale.add(limit);
        }

        for(int i=0;i<12;i++){
            center=rnd.nextInt(1600);
            range=rnd.nextInt(200);
            modify(center, range,true);
        }
    }

    void reset(){
        for(int i=0;i<1600;i++){
            scale.set(i,limit);
        }
        initialise();
    }

    void Draw(){
//        game.batch.begin();
        for(int i=0;i<1600;i++){
            Rock.setScale(1,scale.get(i));
            Rock.setPosition(i,0);
            Rock.draw(game.batch);
        }
//        game.batch.end();
    }

    void modify(int center,int range, boolean UP){
        boolean raise=false;

        if(!UP){
            range-=(scale.get(center));
        }
        else{
            int max=0;
            for(int i=center-range;i<=center+range;i++){
                if(i<1600 && i>=0) {
                    max = Math.max(max, scale.get(i));
                }
            }
            if ((Math.pow(2,0.5)*(range)+max>650)){
                modify(center,range/2,true);
                return;
            }
        }

        if(range>=0){
            raise=true;
        }

        range=abs(range);

        for(int i=1;i<range;i++){
            int height=(int)Math.pow((2 * range*range - i * i), 0.5);
            if(center-i>0){
                update(center-i,height,raise);
            }
            if(center+i<1600){
                update(center+i,height,raise);
            }
        }

        if(center<1600 && center>0){
            update(center,(int)(Math.pow(2,0.5)*range),raise);
        }

        for(int i=0;i<range;i++){
            if(center-range-i>0){
                update(center-range-i,range-i,raise);
            }
            if(center+range+i<1600){
                update(center+range+i,range-i,raise);
            }
        }
    }

    void update(int index,int value,boolean up){
        int temp=scale.get(index);
        if(up){
            scale.set(index, Math.min(value + temp, 650));
        }
        else{
            scale.set(index, Math.max(temp - value, 50));
        }
    }



}
