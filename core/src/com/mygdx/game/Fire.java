package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Fire  implements Screen {
    MyGdxGame game;
    Tank tank;

    public Fire(MyGdxGame game, Tank tank) {
        this.game = game;
        this.tank = tank;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        tank.draw();
        tank.traj.follow(tank.Bullet);
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
