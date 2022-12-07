package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Terrain {
    ArrayList<Integer> list=new ArrayList<>();
    MyGdxGame game;
    Texture rock=new Texture("Terrain\\Rock.png");
    Sprite Rock=new Sprite(rock);
//    Sprite Rock = new Sprite(rock);
    int height=0;

    public Terrain(MyGdxGame game){
        this.game=game;
    }

    void initialise(){
        Random rnd=new Random();
        height=400;
        for(int i=0;i<1600;i++){
            if(height>=600){
                height-=1;
            }
            else if(height<=100){
                height+=1;
            }
            else{
                height+=-1+rnd.nextInt(3);
            }
            list.add(height);
        }
    }

    void Draw(){
//        Sprite Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
        for(int i=0;i<1600;i++){
            Rock.setPosition(i,0);
//            Rock.setPosition(i, list.get(i));
            Rock.setScale(1,list.get(i));
//            Gdx.app.log(String.valueOf(5*i),String.valueOf(height));
//            height+=list.get(i);
            Rock.draw(game.batch);
            if (Gdx.input.justTouched()){
                mutilation(Gdx.input.getX(), 10);
            }
        }
//        done=true;
    }
    void mutilation(int x, int m){
        float radius = 40;
        for (int i = x - m; i <= x+m ; i++){
            list.set(i, (int) (list.get(i)-radius/abs(x-i)));
        }
    }

}
