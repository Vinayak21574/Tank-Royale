package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

public class Settings implements Screen {
    private MyGdxGame game;

    public Settings(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        load.setGame(this.game);
        quit.setGame(this.game);
        close.setGame(this.game);
        sound.setGame(this.game);
        music.setGame(this.game);
        about.setGame(this.game);
    }

    private Element menu=new Element("Screens\\Menu_Blur.png",800,450);
    private Element player=new Element("Buttons\\Bright\\1Player.png",250,765);
    private Element players=new Element("Buttons\\Bright\\2Players.png",250,554);
    private Element load=new Element("Buttons\\Bright\\Load.png",250,345);
    private Element quit=new Element("Buttons\\Bright\\Quit.png",250,135);
    private Element close=new Element("Buttons\\Bright\\Close_Menu.png",1547,848);
    private Element sound=new Element("Buttons\\Bright\\Sound_Menu.png","Buttons\\Dim\\Sound_Menu.png",1525,740);
    private Element music=new Element("Buttons\\Bright\\Music_Menu.png","Buttons\\Dim\\Music_Menu.png",1525,627);
    private Element about=new Element("Buttons\\Bright\\About_Menu.png",1525,514);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        close.Draw();
        sound.Draw();
        music.Draw();
        about.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()){
            if(quit.detect()){
                //Todo AskBeforeQuit
                Gdx.app.exit();
            }
            else if(player.detect()){
                game.setScreen(new Solo(game));
            }
            else if(players.detect()){
                game.setScreen(new Duo(game));
            }
            else if(load.detect()){
                game.setScreen(new Load(game));
            }
            else if(close.detect()){
                game.setScreen(new Menu(game));
            }
            else if(sound.detect()){
                sound.invert();
            }
            else if(music.detect()){
                music.invert();
            }
            else if(about.detect()){
                //ToDo about screen
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
        close.Erase();
        sound.Erase();
        music.Erase();
        about.Erase();
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