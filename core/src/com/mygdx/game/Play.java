package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import java.lang.Math;

public class Play implements Screen{
    private MyGdxGame game;

    Tank tank=new Tank(game);
    Terrain current=new Terrain(game);
    private Element map=new Element("Screens\\Fields\\Normal\\map.png",800,450);

    //private Element tank=new Element("Tanks\\Left\\B.png",300,150);

    //Sprite s_tank=new Sprite(tank.getTxt(),0, 0, tank.getWidth(), tank.getHeight());

    public Play(MyGdxGame game){
        this.game=game;
        tank.game=game;
        tank.traj.game=game;
        tank.locate(200,200);
        current.game=game;
        map.setGame(game);
    }

    float angle=0;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        map.Draw();
            current.Draw();
            if(Gdx.input.justTouched()) {
                //current.modify(Gdx.input.getX(),900-Gdx.input.getY(),false);
                current=new Terrain(game);
            }





//        tank.rotate();
//        tank.draw();
//
//        if(Gdx.input.justTouched()) {
//            Gdx.app.log("x>", String.valueOf(Gdx.input.getX()));
//            Gdx.app.log("y>", String.valueOf(900-Gdx.input.getY()));
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
//            if(Gdx.input.isKeyPressed(Input.Keys.A)){
//                tank.move(true);
//                //Gdx.app.log("Move>", "Forward");
//            }
//            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
//                tank.move(false);
//                //Gdx.app.log("Move>", "Backward");
//            }
//            else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//                game.setScreen(new Fire(game,tank,this));
//                Gdx.app.log("Fired","missile");
//            }
//        }
//        s_tank.draw(game.batch);
//        pause.Draw();

        game.batch.end();
    }

    @Override
    public void dispose() {
        //pause.Erase();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
}
