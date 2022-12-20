package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.Bullet;


import java.util.ArrayList;
import java.util.HashMap;
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
	//x0,y0->center midC
	int x0,y0;
	int x1,y1;
	float Cangle;
	float Gangle=0;
	boolean flipped=false;
	double power;
	int speed=3;
	Vector2 lFEET=new Vector2();
	Vector2 rFEET=new Vector2();
	int left=70,right=17;
	Terrain ground;

	Texture healthcolour = new Texture("Terrain\\health1.png");
	float health=0.5f;
	Healthbar mine;

	MyGdxGame game;
	Trajectory traj;

	Texture body=new Texture("Tanks\\Left\\Body.png");
	Texture cap=new Texture("Tanks\\Left\\CannonCap.png");
	Texture cannon=new Texture("Tanks\\Left\\Cannon.png");
	Texture bullet=new Texture("Bullet\\Bullet.png");

	Sprite Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
	Sprite Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
	Sprite Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());
	Sprite Bullet=new Sprite(bullet,0,0, bullet.getWidth(), bullet.getHeight());
	ArrayList<Sprite> collect=new ArrayList<>();
	float fuel=1;
	float milage=0.01f;

	public Tank(MyGdxGame game,Terrain terrain,int startX,int startY,boolean side){
		this.game= game;
		collect.add(Body);collect.add(Cannon);collect.add(Cap);
		traj=new Trajectory(game);
		this.ground=terrain;
		this.x0=startX;
		this.y0=startY;
		mine=new Healthbar(game, healthcolour, side);
		mine.setLength( (health));
		initialise();
	}
	void locate(int gap){
		y0 = ground.scale.get(x0);

		x0+=gap;

		lFEET.x=x0-left;
		rFEET.x=x0+right;

		lFEET.y=ground.scale.get((int)lFEET.x);
		rFEET.y=ground.scale.get((int)rFEET.x);

		Gangle=(float)Math.toDegrees(Math.atan2(rFEET.y-lFEET.y,rFEET.x-lFEET.x));
		if(Gangle<0){
			Gangle+=360;
		}

		for(Sprite i:collect){
			i.setPosition(x0-(108),y0);	//lowermost corner
			if(i.equals(Cannon)){
				if(validAngle()) {
					i.setRotation(Cangle);
				}
			}
			else {
				i.setRotation(Gangle);
			}
		}
		y0+=60;
	}

	void rotate(){
		int x=Gdx.input.getX();
		int y=900-Gdx.input.getY();

		Cangle=(float)Math.toDegrees(Math.atan2(y-y0,x-x0));

		if(Cangle<0){
			Cangle+=360;
		}

//		Gdx.app.log(String.valueOf(Cangle), String.valueOf(Gangle));

		if(validAngle()){
			Cannon.setRotation(Cangle);
			Cannon.setOrigin(108, 60); //mid pt of 450,250

			if (Cangle>90 && Cangle<270 && !flipped) {
				flip(true);

			} else if (flipped && !(Cangle>90 && Cangle<270)) {
				flip(false);
			}

			locate(0);

		}

		getTip(Cangle);

		traj.flipped=flipped;
		setPower(x,y);
		if(validAngle()){
			traj.setStart(x1,y1,Cangle,power);
			traj.draw(validAngle());
		}

	}

	void initialise(){
		fuel=1f;
		int x=Gdx.input.getX();
		int y=900-Gdx.input.getY();

		Cangle=(float)Math.toDegrees(Math.atan2(y-y0,x-x0));

		if(Cangle<0){
			Cangle+=360;
		}

//		Gdx.app.log(String.valueOf(Cangle), String.valueOf(Gangle));

		if(validAngle()){
			Cannon.setRotation(Cangle);
			Cannon.setOrigin(108, 60); //mid pt of 450,250

			if (Cangle>90 && Cangle<270 && !flipped) {
				flip(true);

			} else if (flipped && !(Cangle>90 && Cangle<270)) {
				flip(false);
			}

			locate(0);

		}
		getTip(Cangle);
	}

	void draw(){
		for(Sprite i:collect){
			i.draw(game.batch);
		}
		mine.draw();
	}

	void move(boolean value){
		if(fuel>0) {
			fuel-=milage;
			int number = (int) (speed * Math.cos(Math.toRadians(Gangle)));

			if (x0 < 100 || x0 > 1500) {
				if (x0 < 100 && value) {
					return;
				} else if (x0 > 1500 && !value) {
					return;
				}
			}

			if (value) {
				locate(-number);
			} else {
				locate(number);
			}
			rotate();
		}
	}

	void getTip(double angle){
		x1=x0+(int)(Math.cos(Math.toRadians(angle))*(80) + Math.sin(Math.toRadians(angle))*0);//length of cannon
		y1=y0+(int)(Math.sin(Math.toRadians(angle))*(80) + Math.cos(Math.toRadians(angle))*0);
	}


	boolean validAngle(){
		return true;
	}

	void flip(boolean value){
		for (Sprite i : collect) {
			if (i != Cannon) {
				i.setFlip(value, false);
			}
		}
		Bullet.setFlip(value,false);

		locate(0);
		int temp=left;
		left=right;
		right=temp;
		flipped = !flipped;
	}

	void setPower(int x,int y){
		power= Math.pow(Math.pow(x-x1,2)+Math.pow(y-y1,2),0.5f);
	}

	void refuel(){
		fuel=1;
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
	int slowfactor=5;

	Texture DOT=new Texture("Tracks\\Trackball.png");
	Sprite dot= new Sprite(DOT,0,0, DOT.getWidth(), DOT.getHeight());
	MyGdxGame game;

	public Trajectory(MyGdxGame game) {
		this.game = game;
	}

	int getY(int x){
		return (int)(x*(float)Math.tan(Math.toRadians(angle)) -5*x*x/((velocity*velocity)*(float)(Math.pow(Math.cos(Math.toRadians(angle)),2))));
	}
	void setStart(int x,int y,double angle,double power){
		x0=x;
		y0=y;
		this.angle=angle;
		if(angle<0){
			this.angle+=360;
		}
		this.velocity=power/6;
		gap=(int)(power/10);
	}

	double derivative(double x){
		return Math.toDegrees(Math.atan(((float)Math.tan(Math.toRadians(angle)) -10*x/(velocity*velocity*(float)(Math.pow(Math.cos(Math.toRadians(angle)),2))))));
	}

	void draw(boolean valid) {
		if (valid) {

			for (int i = 0; i < 10; i++) {
				if (angle <= 87 || angle >= 93) {
					dot.setPosition(x0 + start - 10, y0 + getY(start) - 10);
				} else {
					dot.setPosition(x0 - 10, y0 + gap * 8 - 10);
					dot.draw(game.batch);
					dot.setPosition(x0 - 10, y0 + gap * 4 - 10);
					dot.draw(game.batch);
					dot.setPosition(x0 - 10, y0 - 10);
					dot.draw(game.batch);
					break;
				}

				if (flipped) {
					start -= gap;
				} else {
					start += gap;
				}

				if (y0 + getY(start) - 10 < 0) {
					break;
				}
				dot.draw(game.batch);
			}
			start = 0;
		}
	}

	void follow(Sprite missile,Screen prev){
		int x=(int)(x0+velocity*Math.cos(Math.toRadians(angle))*start/slowfactor-30);
		int y=(int)(y0+velocity*Math.sin(Math.toRadians(angle))*start/slowfactor-5*start*start/(slowfactor*slowfactor)-6);
		//Gdx.app.log("Angle",String.valueOf(derivative(start)));

		if((x<1600 && x>0) && (y>=0)){
			missile.setOrigin(missile.getWidth()/2, missile.getHeight()/2);
			missile.setRotation((int)derivative(velocity*Math.cos(Math.toRadians(angle))*start/slowfactor));
			missile.setPosition(x, y);
			missile.draw(game.batch);
			start+=1;
		}
		else{
			start=0;
			game.setScreen(prev);
		}
	}

}

class Healthbar{
	Texture border=new Texture("Terrain\\healthborder.png");
	Sprite Border=new Sprite(border,0,0,border.getWidth(), border.getHeight());
	MyGdxGame game;
	Texture health;
	Sprite Health;
	boolean side;
	public Healthbar(MyGdxGame game, Texture health, boolean side){
		this.game=game;
		this.health= health;
		this.Health= new Sprite(health,0,0,health.getWidth(),health.getHeight());
		this.side = side;
		this.setPosition();
	}

	void setLength(float value){
		Health.setScale(value,1);
	}

	void setPosition(){
		int x,y;
		if (side){
			x=901;
			y=821;
			Health.setFlip(true,false);
			Border.setFlip(true,false);
		}
		else{
			x= 47;
			y= 821;
		}
		Health.setPosition(x,y);
		Border.setPosition(x,y);
	}

	void draw(){
		Border.draw(game.batch);
		Health.draw(game.batch);
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
