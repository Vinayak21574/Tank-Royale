package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Home implements Screen {
    MyGdxGame game;

    public Home(MyGdxGame game) {
        this.game = game;
        home.game=start.game=quit.game=this.game;
    }

    Element home = new Element("Screens\\Home.png",800,450);
    Element start = new Element("Buttons\\Bright\\Start.png",home.width/2,315);
    Element quit = new Element("Buttons\\Bright\\Quit_Home.png",home.width/2,160);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        home.Draw();
        start.Draw();
        quit.Draw();

        if(Gdx.input.justTouched()){
            if(start.detect()){
                game.setScreen(new Menu(game));
                dispose();
            }
        }
        if(Gdx.input.justTouched()){
            if(quit.detect()){
                Gdx.app.exit();
            }
        }
        game.batch.end();
    }

    @Override
    public void dispose() {
        home.Erase();
        start.Erase();
        quit.Erase();
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
