package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

public class Load implements Screen{
    private MyGdxGame game;

    public Load(MyGdxGame game) {
        this.game = game;
        menu.setGame(this.game);
        player.setGame(this.game);
        players.setGame(this.game);
        quit.setGame(this.game);
        load.setGame(this.game);
        Gamebar.setGame(this.game);
        play.setGame(this.game);
    }

    private Element menu=new Element("Screens\\Overflown.png",800,450);

    private Element player=new Element("Buttons\\Dim\\1Player.png",250,765);
    private Element players=new Element("Buttons\\Dim\\2Players.png",250,554);
    private Element load=new Element("Buttons\\Bright\\Load.png",250,345);
    private Element quit=new Element("Buttons\\Dim\\Quit.png",250,135);
    private Element play=new Element("Buttons\\Dim\\Start_Load.png","Buttons\\Bright\\Start_Load.png",1037,129);
    private subElement Gamebar=new subElement("Buttons\\Bright\\SelectGame.png",1037,683,1288,731,60,60);

    private Texture highlight=new Texture("Buttons\\Bright\\LoadGame.png");

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        menu.Draw();
        player.Draw();
        players.Draw();
        load.Draw();
        quit.Draw();
        play.Draw();
        Gamebar.Draw();
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
            else if(Gamebar.detect()){
                if(Gamebar.subDetect()){
                    Gamebar.Kick();
                    if(play.isInverted()){
                        play.invert();
                    }
                }
                else{
                    Gamebar.Overwrite(highlight);
                    if(!play.isInverted()){
                        play.invert();
                    }
                }
            }
            else if(play.detect()){
                if(play.isInverted()){
                    game.setScreen(new Play(game));
                }
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
        Gamebar.Erase();
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