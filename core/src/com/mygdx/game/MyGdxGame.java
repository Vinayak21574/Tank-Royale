package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

class Element{
	int midX,midY;
	int startX,startY;
	int height,width;
	Texture txt;

	public Element(String name,int x,int y) {
		txt=new Texture(name);
		height=txt.getHeight();
		width=txt.getWidth();
		midX=x;
		midY=y;
		startX=x-txt.getWidth()/2;
		startY=y-txt.getHeight()/2;
	}

	public boolean detect(int x,int y){
		y=900-y;
		return startX <= x && x <= startX + width && startY <= y && y <= startY + height;
	}
}

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Element home;
	Element start;
	Element quit;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		home = new Element("Home.png",800,450);
		start=new Element("Start.png",home.width/2,315);
		quit=new Element("Quit.png",home.width/2,160);
	}


	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(home.txt, 0, 0,1600,900);
		//batch.draw(start,arrangeH(home.getWidth()/2,start),arrangeV(315,start));
		batch.draw(start.txt,start.startX,start.startY);
		batch.draw(quit.txt,quit.startX,quit.startY);
		//batch.draw(quit,arrangeH(home.getWidth()/2,quit),arrangeV(160,quit));

		if(Gdx.input.justTouched()){
			if(quit.detect(Gdx.input.getX(),Gdx.input.getY())){
				Gdx.app.exit();
			}
		}

		batch.end();
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		home.txt.dispose();
		start.txt.dispose();
		quit.txt.dispose();
	}
}
