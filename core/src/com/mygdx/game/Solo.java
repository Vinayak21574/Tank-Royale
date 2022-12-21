package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Solo implements Screen {
    private MyGdxGame game;

    public Solo(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        load.setGame(this.game);
        quit.setGame(this.game);
    }

    private Element menu=new Element("Screens\\FutureRelease.png",800,450);
    private Element player=new Element("Buttons\\Bright\\1Player.png",250,765);
    private Element players=new Element("Buttons\\Dim\\2Players.png",250,554);
    private Element load=new Element("Buttons\\Dim\\Load.png",250,345);
    private Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);

    private Texture selected=new Texture("Buttons\\Bright\\EnterName.png");

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()){
            if(quit.detect()){
                //todo dialog box for quiting
            }
            else if(players.detect()){
                game.setScreen(new Duo(game));
            }
            else if(load.detect()){
                game.setScreen(new Load(game));
            }
        }
    }

    @Override
    public void dispose() {
        menu.Erase();
        player.Erase();
        players.Erase();
        load.Erase();
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