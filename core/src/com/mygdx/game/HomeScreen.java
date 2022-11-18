package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HomeScreen implements Screen {
    MyGdxGame game;

    public HomeScreen(MyGdxGame game) {
        this.game = game;
    }

    Element home = new Element("Home.png",800,450);
    Element start = new Element("Start.png",home.width/2,315);
    Element quit = new Element("Quit.png",home.width/2,160);
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(home.txt, 0, 0,1600,900);
        //batch.draw(start,arrangeH(home.getWidth()/2,start),arrangeV(315,start));
        game.batch.draw(start.txt,start.startX,start.startY);
        game.batch.draw(quit.txt,quit.startX,quit.startY);
        //batch.draw(quit,arrangeH(home.getWidth()/2,quit),arrangeV(160,quit));
        if(Gdx.input.justTouched()){
            if(start.detect(Gdx.input.getX(),Gdx.input.getY())){
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        }
        if(Gdx.input.justTouched()){
            if(quit.detect(Gdx.input.getX(),Gdx.input.getY())){
                Gdx.app.exit();
            }
        }
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
