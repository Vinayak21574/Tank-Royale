package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

class MenuScreen implements Screen {
    MyGdxGame game;
    Texture menu= new Texture("menu.jpeg");
    public MenuScreen(MyGdxGame game) {
        this.game = game;
    }
    @Override
    public void show() {

    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        game.batch.draw(menu,0,0,1600,900);
        game.batch.end();
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