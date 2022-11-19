package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

public class Menu implements Screen {
    MyGdxGame game;

    public Menu(MyGdxGame game) {
        this.game = game;
        menu.game= player.game= players.game= load.game= quit.game= settings.game=this.game;
    }

    Element menu=new Element("Screens\\Menu.png",800,450);

    Element player=new Element("Buttons\\Bright\\1Player.png",250,765);
    Element players=new Element("Buttons\\Bright\\2Players.png",250,554);
    Element load=new Element("Buttons\\Bright\\Load.png",250,345);
    Element quit=new Element("Buttons\\Bright\\Quit.png",250,135);
    Element settings=new Element("Buttons\\Dim\\Settings.png",1547,848);
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        settings.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()){
            if(quit.detect()){
                //Todo AskBeforeQuit
                Gdx.app.exit();
            }
            else if(player.detect()){
                game.setScreen(new Player(game));
            }
            else if(players.detect()){
                game.setScreen(new Players(game));
            }
            else if(load.detect()){
                game.setScreen(new Load(game));
            }
            else if(settings.detect()){
                //ToDo
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
        settings.Erase();
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