package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.Bullet;


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

	public void Draw(int x,int y){
		midX=x;
		midY=y;
		startX=x-txt.getWidth()/2;
		startY=y-txt.getHeight()/2;
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
	int x1,y1;
	float Cangle;
	boolean flipped=false;
	double power;
	boolean fired;

	MyGdxGame game;
	Trajectory traj;

	Texture body=new Texture("Tanks\\Left\\Body.png");
	Texture cap=new Texture("Tanks\\Left\\CannonCap.png");
	Texture cannon=new Texture("Tanks\\Left\\Cannon.png");

	Sprite Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
	Sprite Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
	Sprite Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());

	ArrayList<Sprite> collect=new ArrayList<>();

	public Tank(MyGdxGame game){
		this.game= game;
		collect.add(Body);collect.add(Cannon);collect.add(Cap);
		traj=new Trajectory(game);
	}
	void locate(int x,int y){
		for(Sprite i:collect){
			i.setPosition(x-225,y-125);
		}
		x0=x;
		y0=y;
	}

	void rotate(){
		int x=Gdx.input.getX();
		int y=900-Gdx.input.getY();

		Cangle=(float)Math.toDegrees(Math.atan2(y-y0,x-x0));

		if(Cangle<0){
			Cangle+=360;
		}

		if(validAngle()){
			Cannon.setRotation(Cangle);
			Cannon.setOrigin(225, 125);

			if (Cangle>90 && Cangle<270 && !flipped) {
				flip(true);

			} else if (flipped && !(Cangle>90 && Cangle<270)) {
				flip(false);
			}

			locate(x0, y0);

		}

		getTip(Cangle);
		traj.flipped=flipped;
		setPower(x,y);
		if(validAngle()){
			traj.setStart(x1,y1,Cangle,power);
		}
		traj.draw();

	}

	void draw(){
		for(Sprite i:collect){
			i.draw(game.batch);
		}
	}

	void move(boolean value){
		if(value){
			locate(x0-6,y0);
		}
		else{
			locate(x0+6,y0);
		}
	}

	void getTip(double angle){
		x1=x0+(int)(Math.cos(Math.toRadians(angle))*(150) + Math.sin(Math.toRadians(angle))*0);
		y1=y0+(int)(Math.sin(Math.toRadians(angle))*(150) + Math.cos(Math.toRadians(angle))*0);
	}


	boolean validAngle(){
		return !(Cangle<=340 && Cangle>=200);
	}

	void flip(boolean value){
		for (Sprite i : collect) {
			if (i != Cannon) {
				i.setFlip(value, false);
			}
		}
		flipped = !flipped;
	}

	void setPower(int x,int y){
		power= 6/Math.pow(Math.pow(x-x1,2)+Math.pow(y-y1,2),0.5f);
	}

	void fire(){

	}



}

class Trajectory{
	//g==10
	int x0,y0;
	int start=0;
	int gap;
	double angle;
	double velocity;
	boolean flipped;


	Texture DOT=new Texture("Tracks\\Trackball.png");
	Sprite dot= new Sprite(DOT,0,0, DOT.getWidth(), DOT.getHeight());
	MyGdxGame game;

	public Trajectory(MyGdxGame game) {
		this.game = game;
	}

	int getY(int x){
		return (int)(x*(float)Math.tan(Math.toRadians(angle)) -5*x*x*velocity*velocity/(float)(Math.pow(Math.cos(Math.toRadians(angle)),2)));
	}
	void setStart(int x,int y,double angle,double power){
		x0=x;
		y0=y;
		this.angle=angle;
		this.velocity=power;
		gap=(int)(Math.pow(power,-1)/(float)2);
	}

	void draw(){
		for(int i=0;i<10;i++){
			if(flipped){
				dot.setPosition(x0 + start - 10, y0 + getY(start) - 10);
				start-=gap;
			}
			else{
				dot.setPosition(x0 + start - 10, y0 + getY(start) - 10);
				start+=gap;
			}
			if(y0 + getY(start) - 10<0){
				break;
			}

			dot.draw(game.batch);
		}
		start=0;
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
