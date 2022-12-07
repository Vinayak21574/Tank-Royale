package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Random;

public class Terrain {
    ArrayList<Integer> list=new ArrayList<>();
    MyGdxGame game;
    Texture rock=new Texture("Terrain\\Rock.png");
//    Sprite Rock=new Sprite(rock);
    Sprite Rock = new Sprite(rock);
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
                height+=1+rnd.nextInt(1);
            }
            list.add(height);
        }
//        list.set(800,list.get(800)-10);
//        list.set(799,list.get(799)-5);
//        list.set(801,list.get(801)-5);
//        list.set(802,list.get(802)-2);
//        list.set(798,list.get(798)-2);
    }

    void Draw(){
//        Sprite Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
        for(int i=0;i<1600;i++){
            Rock.setPosition(i,0);
            Rock.setScale(1,list.get(i)/rock.getHeight());
//            Rock.setPosition(i, list.get(i));
//            Gdx.app.log(String.valueOf(5*i),String.valueOf(height));
//            height+=list.get(i);
            Rock.draw(game.batch);
        }
//        done=true;
    }

}
