package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class Solo implements Screen {
    MyGdxGame game;

    public Solo(MyGdxGame game) {
        this.game = game;
        menu.game= player.game= players.game= load.game= quit.game= name.game= up.game= down.game= tank.game= play.game=this.game;
    }

    Element menu=new Element("Screens\\Overflown.png",800,450);

    Element player=new Element("Buttons\\Bright\\1Player.png",250,765);
    Element players=new Element("Buttons\\Dim\\2Players.png",250,554);
    Element load=new Element("Buttons\\Dim\\Load.png",250,345);
    Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);
    Element name=new Element("Buttons\\Dim\\DefaultName.png",1049,675);
    Element up=new Element("Buttons\\Dim\\Up.png",1049,556);
    Element down=new Element("Buttons\\Bright\\Down.png",1049,265);
    Element tank=new Element("Tanks\\Left\\A.png",1049,425);
    Element play=new Element("Buttons\\Bright\\Play.png",1049,123);

    displayTank current=new displayTank("Left",tank,up,down);
    Texture selected=new Texture("Buttons\\Bright\\EnterName.png");

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