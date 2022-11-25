package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Solo implements Screen {
    private MyGdxGame game;

    public Solo(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        load.setGame(this.game);
        quit.setGame(this.game);
        name.setGame(this.game);
        up.setGame(this.game);
        down.setGame(this.game);
        tank.setGame(this.game);
        play.setGame(this.game);
    }

    private Element menu=new Element("Screens\\Overflown.png",800,450);
    private Element player=new Element("Buttons\\Bright\\1Player.png",250,765);
    private Element players=new Element("Buttons\\Dim\\2Players.png",250,554);
    private Element load=new Element("Buttons\\Dim\\Load.png",250,345);
    private Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);
    private Element name=new Element("Buttons\\Dim\\DefaultName.png",1049,675);
    private Element up=new Element("Buttons\\Dim\\Up.png",1049,556);
    private Element down=new Element("Buttons\\Bright\\Down.png",1049,265);
    private Element tank=new Element("Tanks\\Left\\A.png",1049,425);
    private Element play=new Element("Buttons\\Bright\\Play.png",1049,123);

    private displayTank current=new displayTank("Left",tank,up,down);
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
        name.Draw();
        up.Draw();
        down.Draw();
        tank.Draw();
        play.Draw();
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
            else if(up.detect()){
                current.displayPrev();
            }
            else if(down.detect()){
                current.displayNext();
            }
            else if(name.detect()){
                name.Overwrite(selected);

            }
            else if(play.detect()){
                game.setScreen(new Play(game));
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
        name.Erase();
        up.Erase();
        down.Erase();
        tank.Erase();
        play.Erase();
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