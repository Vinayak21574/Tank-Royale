package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Duo implements Screen {
    private MyGdxGame game;

    public Duo(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        load.setGame(this.game);
        quit.setGame(this.game);
        up1.setGame(this.game);
        down1.setGame(this.game);
        tank1.setGame(this.game);
        play.setGame(this.game);
        up2.setGame(this.game);
        down2.setGame(this.game);
        tank2.setGame(this.game);
    }

    private Element menu=new Element("Screens\\Overflown.png",800,450);
    private Element player=new Element("Buttons\\Dim\\1Player.png",250,765);
    private Element players=new Element("Buttons\\Bright\\2Players.png",250,554);
    private Element load=new Element("Buttons\\Dim\\Load.png",250,345);
    private Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);
    private static Element up1=new Element("Buttons\\Dim\\Up.png",776,638);
    private static Element down1=new Element("Buttons\\Bright\\Down.png",776,327);
    private static Element tank1=new Element("Tanks\\A\\Full_L.png",776,507);
    private static Element up2=new Element("Buttons\\Dim\\Up.png",1297,647);
    private static Element down2=new Element("Buttons\\Bright\\Down.png",1297,327);
    private static Element tank2=new Element("Tanks\\A\\Full_R.png",1297,507);
    private Element play=new Element("Buttons\\Bright\\Play.png",1051,133);
    private static displayTank current1=new displayTank("Left",tank1,up1,down1);

    public static displayTank getCurrent1() {
        return current1;
    }

    public static displayTank getCurrent2() {
        return current2;
    }

    private static displayTank current2=new displayTank("Right",tank2,up2,down2);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        up1.Draw();
        down1.Draw();
        tank1.Draw();
        up2.Draw();
        down2.Draw();
        tank2.Draw();
        play.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()){
            if(quit.detect()){
                //todo dialog box for quiting
            }
            else if(player.detect()){
                game.setScreen(new Solo(game));
            }
            else if(load.detect()){
                game.setScreen(new Load(game));
            }
            else if(up1.detect()){
                current1.displayPrev();
            }
            else if(down1.detect()){
                current1.displayNext();
            }
            else if(up2.detect()){
                current2.displayPrev();
            }
            else if(down2.detect()){
                current2.displayNext();
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
        up1.Erase();
        down1.Erase();
        tank1.Erase();
        up2.Erase();
        down2.Erase();
        tank2.Erase();
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