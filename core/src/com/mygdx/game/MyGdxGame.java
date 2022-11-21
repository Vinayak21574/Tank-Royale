package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class Element{
	private int midX,midY;
	private int startX,startY;
	private int height,width;
	private Texture txt;

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	private Texture comp;
	private MyGdxGame game;
	private boolean inverted=false;

	public Texture getComp() {
		return comp;
	}

	public void setComp(Texture comp) {
		this.comp = comp;
	}

	public MyGdxGame getGame() {
		return game;
	}

	public void setGame(MyGdxGame game) {
		this.game = game;
	}

	public Texture getTxt() {
		return txt;
	}

	public void setTxt(Texture txt) {
		this.txt = txt;
	}

	public boolean isInverted() {
		return inverted;
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

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

	public Element(String name,String another,int x,int y) {
		txt=new Texture(name);
		comp=new Texture(another);
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

	public void invert(){
		inverted=!(inverted);
		Texture temp=comp;
		comp=txt;
		txt=temp;
		Overwrite(temp);
	}

	public boolean detect(){
		int x=Gdx.input.getX();
		int y=Gdx.input.getY();
		y=900-y;
		return startX <= x && x <= startX + width && startY <= y && y <= startY + height;
	}
}

class subElement extends Element{
	int startX_;
	int startY_;
	int range_x;
	int range_y;

	public subElement(String name, int x, int y,int X_,int Y_,int Rx,int Ry) {
		super(name,x,y);
		setElement(X_,Y_,Rx,Ry);
	}

	public void setElement(int X_,int Y_,int Rx,int Ry){
		startX_=X_;
		startY_=Y_;
		range_x=Rx;
		range_y=Ry;
	}
	public boolean subDetect(){
		int x=Gdx.input.getX();
		int y=Gdx.input.getY();
		y=900-y;
		return startX_ <= x && x <= startX_ + range_x && startY_ <= y && y <= startY_ + range_y;
	}
}
class displayTank {
	private Element here;
	private Element ArrowUp;
	private Element ArrowDown;

	private ArrayList<Texture> arr=new ArrayList<>();
	private int count=0;

	public displayTank(String Orient,Element Tank,Element Up,Element Down){
		this.here=Tank;
		this.ArrowUp=Up;
		this.ArrowDown=Down;
		if(Objects.equals(Orient, "Left")){
			arr.add(new Texture("Tanks\\Left\\A.png"));
			arr.add(new Texture("Tanks\\Left\\B.png"));
			arr.add(new Texture("Tanks\\Left\\C.png"));
		}
		else{
			arr.add(new Texture("Tanks\\Right\\A.png"));
			arr.add(new Texture("Tanks\\Right\\B.png"));
			arr.add(new Texture("Tanks\\Right\\C.png"));
		}
	}

	public void displayNext(){
		if(count!=2) {
			if(count==1){
				ArrowDown.setTxt(new Texture("Buttons\\Dim\\Down.png"));
			}
			here.setTxt(arr.get(++count));
			ArrowUp.setTxt(new Texture("Buttons\\Bright\\Up.png"));
			ArrowDown.Refresh();
			ArrowUp.Refresh();
			here.Refresh();
		}
	}

	public void displayPrev(){
		if(count!=0) {
			if(count==1){
				ArrowUp.setTxt(new Texture("Buttons\\Dim\\Up.png"));
			}
			here.setTxt(arr.get(--count));
			ArrowDown.setTxt(new Texture("Buttons\\Bright\\Down.png"));
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
	}
}
