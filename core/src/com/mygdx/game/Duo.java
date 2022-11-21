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
    private final MyGdxGame game;

    public Duo(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        load.setGame(this.game);
        quit.setGame(this.game);
        name1.setGame(this.game);
        up1.setGame(this.game);
        down1.setGame(this.game);
        tank1.setGame(this.game);
        play.setGame(this.game);
        name2.setGame(this.game);
        up2.setGame(this.game);
        down2.setGame(this.game);
        tank2.setGame(this.game);
    }

    private final Element menu=new Element("Screens\\Overflown.png",800,450);

    private final Element player=new Element("Buttons\\Dim\\1Player.png",250,765);
    private final Element players=new Element("Buttons\\Bright\\2Players.png",250,554);
    private final Element load=new Element("Buttons\\Dim\\Load.png",250,345);
    private final Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);
    private final Element name1=new Element("Buttons\\Dim\\DefaultName.png",790,697);
    private final Element up1=new Element("Buttons\\Dim\\Up.png",790,527);
    private final Element down1=new Element("Buttons\\Bright\\Down.png",790,246);
    private final Element tank1=new Element("Tanks\\Left\\A.png",790,395);
    private final Element name2=new Element("Buttons\\Dim\\DefaultName.png",1309,697);
    private final Element up2=new Element("Buttons\\Dim\\Up.png",1309,527);
    private final Element down2=new Element("Buttons\\Bright\\Down.png",1309,246);
    private final Element tank2=new Element("Tanks\\Right\\A.png",1285,395);
    private final Element play=new Element("Buttons\\Bright\\Play.png",1049,123);
    private final displayTank current1=new displayTank("Left",tank1,up1,down1);
    private final displayTank current2=new displayTank("Right",tank2,up2,down2);

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        name1.Draw();
        up1.Draw();
        down1.Draw();
        tank1.Draw();
        name2.Draw();
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
            else if(name1.detect()){
                //Todo
            }
            else if(up2.detect()){
                current2.displayPrev();
            }
            else if(down2.detect()){
                current2.displayNext();
            }
            else if(name2.detect()){
                //Todo
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
        name1.Erase();
        up1.Erase();
        down1.Erase();
        tank1.Erase();
        name2.Erase();
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