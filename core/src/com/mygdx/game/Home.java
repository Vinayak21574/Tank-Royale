package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Home implements Screen {
    private MyGdxGame game;

    public Home(MyGdxGame game) {
        this.game = game;
        home.setGame(this.game);
        start.setGame(this.game);
        quit.setGame(this.game);
    }

    private Element home = new Element("Screens\\Home.png",800,450);
    private Element start = new Element("Buttons\\Bright\\Start.png", home.getWidth() /2,315);
    private Element quit = new Element("Buttons\\Bright\\Quit_Home.png", home.getWidth() /2,160);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        game.batch.begin();
        home.Draw();
        start.Draw();
        quit.Draw();

        if(Gdx.input.justTouched()){
            if(start.detect()){
                //game.setScreen(new Terrain(game));

                //game.setScreen(new Play(game));

                game.setScreen(new Menu(game));
            }
            else if(quit.detect()){
                //TODO quit and start font match
                Gdx.app.exit();
            }
        }

//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            game.setScreen(new Play(game));
//        }

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
