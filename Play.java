package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class Play implements Screen{
    private MyGdxGame game;
    private Element battlefield = new Element("Screens\\Fields\\Normal\\A.png",800,450);
    private Element pause=new Element("Buttons\\Bright\\Pause.png",800,766);

    public Play(MyGdxGame game){
        this.game=game;
        //dummy constructor
        battlefield.setGame(this.game);
        pause.setGame(this.game);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        game.batch.begin();
        battlefield.Draw();
        pause.Draw();
        game.batch.end();

        if(Gdx.input.justTouched()) {
            if(pause.detect()){
                game.setScreen(new Pause(game));
            }
        }
    }

    @Override
    public void dispose() {
        pause.Erase();
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
