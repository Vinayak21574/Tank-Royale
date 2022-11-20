package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Pause implements Screen{
    MyGdxGame game;

    Element battlefield = new Element("Screens\\Fields\\Blur\\A.png",800,450);
    Element resume=new Element("Buttons\\Bright\\Resume.png",800,766);
    Element save=new Element("Buttons\\Bright\\Save.png",478,451);
    Element sound=new Element("Buttons\\Bright\\Sound.png","Buttons\\Dim\\Sound.png",700,451);
    Element music=new Element("Buttons\\Bright\\Music.png","Buttons\\Dim\\Music.png",922,451);
    Element quit=new Element("Buttons\\Bright\\Back2Menu.png",1144,451);

    public Pause(MyGdxGame game){
        this.game=game;
        //dummy constructor
        battlefield.game= resume.game= save.game= sound.game= music.game= quit.game=this.game;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        battlefield.Draw();
        resume.Draw();
        save.Draw();
        sound.Draw();
        music.Draw();
        quit.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()) {
            if(quit.detect()){
                game.setScreen(new Menu(game));
            }
            else if(save.detect()){
                //
            }
            else if(sound.detect()){
                sound.invert();
            }
            else if(music.detect()){
                music.invert();
            }
            else if(resume.detect()){
                game.setScreen(new Play(game));
            }
        }
    }

    @Override
    public void dispose() {
        resume.Erase();
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
