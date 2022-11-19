package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

class Element{
	int midX,midY;
	int startX,startY;
	int height,width;
	Texture txt;
	MyGdxGame game;

	public Element(){}
	public Element(String name,int x,int y) {
		txt=new Texture(name);
		height=txt.getHeight();
		width=txt.getWidth();
		midX=x;
		midY=y;
		startX=x-txt.getWidth()/2;
		startY=y-txt.getHeight()/2;
	}

	public void Draw(){
		game.batch.draw(this.txt,this.startX,this.startY);
	}

	public void Erase(){
		txt.dispose();
	}

	public void Refresh(){
		game.batch.begin();
		game.batch.draw(this.txt,this.startX,this.startY);
		game.batch.end();
	}

	public void Overwrite(Texture custom){
		this.txt=custom;
		game.batch.begin();
		game.batch.draw(this.txt,this.startX,this.startY);
		game.batch.end();
	}

	public void Kick(){
		Overwrite(new Texture("Buttons\\Dim\\Blank.png"));
	}

	public boolean detect(){
		int x=Gdx.input.getX();
		int y=Gdx.input.getY();
		y=900-y;
		return startX <= x && x <= startX + width && startY <= y && y <= startY + height;
	}
}

class Removable extends Element{
	int startX_;
	int startY_;
	int range;
	public Removable(String name,int x,int y,int X_,int Y_,int r){
		super(name,x,y);
		range=r;
		startX_=X_;
		startY_=Y_;
	}
	public boolean closure(){
		int x=Gdx.input.getX();
		int y=Gdx.input.getY();
		y=900-y;
		return startX_ <= x && x <= startX_ + range && startY_ <= y && y <= startY_ + range;
	}
}
class displayTank {
	Element here;
	Element ArrowUp;
	Element ArrowDown;

	Texture[] arr ={new Texture("Tanks\\Left\\A.png"),new Texture("Tanks\\Left\\B.png"),new Texture("Tanks\\Left\\C.png")};
	int count=0;

	public displayTank(Element Tank,Element Up,Element Down){
		this.here=Tank;
		this.ArrowUp=Up;
		this.ArrowDown=Down;
	}

	public void displayNext(){
		if(count!=2) {
			if(count==1){
				ArrowDown.txt=new Texture("Buttons\\Dim\\Down.png");
			}
			here.txt = arr[++count];
			ArrowUp.txt=new Texture("Buttons\\Bright\\Up.png");
			ArrowDown.Refresh();
			ArrowUp.Refresh();
			here.Refresh();
		}
	}

	public void displayPrev(){
		if(count!=0) {
			if(count==1){
				ArrowUp.txt=new Texture("Buttons\\Dim\\Up.png");
			}
			here.txt = arr[--count];
			ArrowDown.txt=new Texture("Buttons\\Bright\\Down.png");
			ArrowDown.Refresh();
			ArrowUp.Refresh();
			here.Refresh();
		}
	}
}

public class MyGdxGame extends Game {
	SpriteBatch batch;
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Home(this));
	}


	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public void dispose () {
		batch.dispose();
//		home.txt.dispose();
//		start.txt.dispose();
//		quit.txt.dispose();
	}
}
