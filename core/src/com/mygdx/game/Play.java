package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import java.lang.Math;

public class Play implements Screen{
    private MyGdxGame game;

    Terrain current=new Terrain(game);
    Element gameover = new Element("Screens\\gameover.png",800,900);
    Element map=new Element("Screens\\Fields\\Normal\\map.png",800,450);
    Element pause=new Element("Buttons\\Bright\\Pause.png",800,800);
    Tank tankL,tankR;
    Loadstate ls = new Loadstate(this);
    boolean turn=true;

    Sound music = Gdx.audio.newSound(Gdx.files.internal("Sounds\\tank-battle.mp3"));

    long id = music.play(1.0f);



    //Element tank=new Element("Tanks\\Left\\B.png",300,150);

    //Sprite s_tank=new Sprite(tank.getTxt(),0, 0, tank.getWidth(), tank.getHeight());

    public Play(MyGdxGame game){
        tankL=new Tank(game,current,200,500,false,Duo.getCurrent1().getCount());
        tankR=new Tank(game,current,1300,500,true,Duo.getCurrent2().getCount());
        this.game=game;
        tankL.getTraj().setGame(game);
        tankR.getTraj().setGame(game);
        current.game=game;
        pause.setGame(game);
        map.setGame(game);
        gameover.setGame(game);
    }

    float angle=0;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        map.Draw();
        current.Draw();

        if(!(tankL.getHealth() >0 && tankR.getHealth() >0)){
//            pause.Kick();
            gameover.Draw();
            if(gameover.getStartY()>=350){
                gameover.setStartY(gameover.getStartY()-1);
            }
            else{
                game.setScreen(new Menu(game));
            }
        }
        else {
            if(turn){
                tankL.rotate();
            }
            else{
                tankR.rotate();
            }

            tankL.draw();
            tankR.draw();
            if (Gdx.input.justTouched()) {
                Gdx.app.log("x>", String.valueOf(Gdx.input.getX()));
                Gdx.app.log("y>", String.valueOf(900 - Gdx.input.getY()));
                if (pause.detect()) {
                    game.setScreen(new Pause(game, this));
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    if (turn) {
                        tankL.move(true);
                    } else {
                        tankR.move(true);
                    }
                    //Gdx.app.log("Move>", "Forward");
                } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (turn) {
                        tankL.move(false);
                    } else {
                        tankR.move(false);
                    }
                    //Gdx.app.log("Move>", "Backward");
                } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    if (turn) {
                        game.setScreen(new Fire(game, tankL, tankR, this, current));
                    } else {
                        game.setScreen(new Fire(game, tankR, tankL, this, current));
                    }

                    turn = !turn;
                    Gdx.app.log("Fired", "missile");
                } else if (Gdx.input.isKeyJustPressed(Input.Keys.ALT_RIGHT)) {
                    tankL.getGround().reset();
                    tankL.initialise();
                    tankR.initialise();
                }
            }
        }
//        s_tank.draw(game.batch);
        pause.Draw();

        game.batch.end();
    }

    @Override
    public void dispose() {
        pause.Erase();
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
