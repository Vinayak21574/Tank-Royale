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
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.physics.bullet.Bullet;


import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class Element{
	private int midX,midY;
	private int startX,startY;
	private int height,width;
	private Texture txt;

	public int getMidX() {
		return midX;
	}

	public void setMidX(int midX) {
		this.midX = midX;
	}

	public int getMidY() {
		return midY;
	}

	public void setMidY(int midY) {
		this.midY = midY;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

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
	private int startX_;
	private int startY_;
	private int range_x;
	private int range_y;

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

class Tank {
	int x0,y0;
	boolean flipped=false;

	MyGdxGame game;

	Texture body=new Texture("Tanks\\Left\\Body.png");
	Texture cap=new Texture("Tanks\\Left\\CannonCap.png");
	Texture cannon=new Texture("Tanks\\Left\\Cannon.png");

	Sprite Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
	Sprite Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
	Sprite Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());

	void locate(int x,int y){
		Cannon.setPosition(x,y);
		if(!flipped){
			Body.setPosition(x - 180, y - 102);
			Cap.setPosition(x - 20, y - 8);
		}
		else{
			Body.setPosition(x -116, y - 102);
			Cap.setPosition(x -12, y - 8);
		}
		x0=x;
		y0=y;
	}

	void rotate(){
		int x=Gdx.input.getX();
		int y=900-Gdx.input.getY();
		float angle=(float)Math.toDegrees(Math.atan2(y-y0-cannon.getHeight()/2,x-x0));
		Cannon.setRotation(angle);
		Cannon.setOrigin(0,cannon.getHeight()/2);
		if( x<x0 && !flipped){
			Body.setFlip(true,false);
			Cap.setFlip(true,false);
			flipped=!flipped;

		}
		else if(x>x0 && flipped){
			Body.setFlip(false,false);
			Cap.setFlip(false,false);
			flipped=!flipped;
		}
		locate(x0,y0);
	}

	void draw(){
		Body.draw(game.batch);
		Cannon.draw(game.batch);
		Cap.draw(game.batch);
	}

	void move(boolean value){
		if(value){
			locate(x0-6,y0);
		}
		else{
			locate(x0+6,y0);
		}
		rotate();
	}


}


class Weapon extends Bullet {
	private float power;
	private Element type;
	private float radius;
	private float damage;
	private float gravity;

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}

	public Element getType() {
		return type;
	}

	public void setType(Element type) {
		this.type = type;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	void explode(){

	}
}
