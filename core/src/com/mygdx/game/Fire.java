package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.ScreenUtils;

public class Fire  implements Screen {

    Element map=new Element("Screens\\Fields\\Normal\\map.png",800,450);
    Screen prev;
    MyGdxGame game;
    Tank tank1,tank2;

    Terrain current;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("Sounds\\rocket.mp3"));

    public Fire(MyGdxGame game, Tank tankL,Tank tankR, Screen here, Terrain current) {
        this.game = game;
        this.tank1 = tankL;
        this.tank2 = tankR;
        this.current=current;
        map.setGame(game);
        prev=here;
        this.tank1.refuel();
        long id = sound.play(1.0f);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        map.Draw();
        current.Draw();
        tank1.draw();
        tank2.draw();
        tank1.getTraj().follow(tank1.getBullet(),tank2,prev);
        tank1.initialise();
        game.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
