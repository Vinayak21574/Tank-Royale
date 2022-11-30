package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class Temp implements Screen{
    MyGdxGame game;
    Stage stage=new Stage();

    public Temp(MyGdxGame game){
        this.game=game;
        stage= new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin=new Skin();

        TextButton btn= new TextButton("Click me !",skin);
        btn.setPosition(300,300);
        btn.setSize(300,60);

        stage.addActor(btn);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }


    @Override
    public void show() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}






