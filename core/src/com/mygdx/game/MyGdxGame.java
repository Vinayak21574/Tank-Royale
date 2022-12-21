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


interface _tank_ {
	void rotate();

	void refuel();

	void move(boolean value);

}

interface _element_{
	public void Draw();

	public boolean detect();

}

class Element implements _element_{
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

	@Override
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

	public int getCount() {
		return count;
	}

	public displayTank(String Orient, Element Tank, Element Up, Element Down){
		this.here=Tank;
		this.ArrowUp=Up;
		this.ArrowDown=Down;
		if(Objects.equals(Orient, "Left")){
			arr.add(new Texture("Tanks\\A\\Full_L.png"));
			arr.add(new Texture("Tanks\\B\\Full_L.png"));
			arr.add(new Texture("Tanks\\C\\Full_L.png"));
		}
		else{
			arr.add(new Texture("Tanks\\A\\Full_R.png"));
			arr.add(new Texture("Tanks\\B\\Full_R.png"));
			arr.add(new Texture("Tanks\\C\\Full_R.png"));
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

	private static MyGdxGame game=null;
	public static MyGdxGame MyGdxGame_getInstance(){
		if(game==null){
			game=new MyGdxGame();
		}
		return game;
	}
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

class Tank implements _tank_{
	//x0,y0->center midC
	private int x0,y0;
	private int x1,y1;
	private float Cangle;
	private float Gangle=0;
	private boolean flipped=false;

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public float getCangle() {
		return Cangle;
	}

	public void setCangle(float cangle) {
		Cangle = cangle;
	}

	public float getGangle() {
		return Gangle;
	}

	public void setGangle(float gangle) {
		Gangle = gangle;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public Healthbar getMine() {
		return mine;
	}

	public void setMine(Healthbar mine) {
		this.mine = mine;
	}

	public MyGdxGame getGame() {
		return game;
	}

	public void setGame(MyGdxGame game) {
		this.game = game;
	}

	public Trajectory getTraj() {
		return traj;
	}

	public void setTraj(Trajectory traj) {
		this.traj = traj;
	}

	public Vector2 getlFEET() {
		return lFEET;
	}

	public void setlFEET(Vector2 lFEET) {
		this.lFEET = lFEET;
	}

	public Vector2 getrFEET() {
		return rFEET;
	}

	public void setrFEET(Vector2 rFEET) {
		this.rFEET = rFEET;
	}

	public Terrain getGround() {
		return ground;
	}

	public void setGround(Terrain ground) {
		this.ground = ground;
	}

	public Texture getHealthcolour() {
		return healthcolour;
	}

	public void setHealthcolour(Texture healthcolour) {
		this.healthcolour = healthcolour;
	}

	public Texture getBody() {
		return body;
	}

	public void setBody(Sprite body) {
		Body = body;
	}

	public void setBody(Texture body) {
		this.body = body;
	}

	public Texture getCap() {
		return cap;
	}

	public void setCap(Sprite cap) {
		Cap = cap;
	}

	public void setCap(Texture cap) {
		this.cap = cap;
	}

	public Texture getCannon() {
		return cannon;
	}

	public void setCannon(Sprite cannon) {
		Cannon = cannon;
	}

	public void setCannon(Texture cannon) {
		this.cannon = cannon;
	}

	public Sprite getBullet() {
		return Bullet;
	}

	public void setBullet(Sprite bullet) {
		Bullet = bullet;
	}

	public ArrayList<Sprite> getCollect() {
		return collect;
	}

	public void setCollect(ArrayList<Sprite> collect) {
		this.collect = collect;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLength() {
		return Length;
	}

	public void setLength(int length) {
		Length = length;
	}

	public int getClearance() {
		return clearance;
	}

	public void setClearance(int clearance) {
		this.clearance = clearance;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public Vector2 getCenter() {
		return center;
	}

	public void setCenter(Vector2 center) {
		this.center = center;
	}

	public int getCriticalRadius() {
		return criticalRadius;
	}

	public void setCriticalRadius(int criticalRadius) {
		this.criticalRadius = criticalRadius;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getFuel() {
		return fuel;
	}

	public void setFuel(float fuel) {
		this.fuel = fuel;
	}

	public float getMilage() {
		return milage;
	}

	public void setMilage(float milage) {
		this.milage = milage;
	}

	public void setBullet(Texture bullet) {
		this.bullet = bullet;
	}

	private double power;
	private Healthbar mine;
	private MyGdxGame game;
	private Trajectory traj;
	private Vector2 lFEET=new Vector2();
	private Vector2 rFEET=new Vector2();
	private Terrain ground;
	private Texture healthcolour = new Texture("Terrain\\health1.png");

	private Texture body;
	private Texture cap;
	private Texture cannon;
	private Texture bullet;

	private Sprite Body;
	private Sprite Cap;
	private Sprite Cannon;
	private Sprite Bullet;
	private ArrayList<Sprite> collect=new ArrayList<>();

	private int speed;
	private int Length;
	private int clearance;
	private int left;
	private int right;
	private Vector2 center;
	private int criticalRadius;
	private float health;
	private float fuel;
	private float milage;

	public Tank(MyGdxGame game,Terrain terrain,int startX,int startY,boolean side,int choice){
		preprocessing(choice);
		this.game= game;
		collect.add(Body);collect.add(Cannon);collect.add(Cap);
		traj=new Trajectory(game,ground);
		traj.setGround(terrain);
		this.ground=terrain;
		this.x0=startX;
		this.y0=startY;
		mine=new Healthbar(game, healthcolour, side,this);
		initialise();
	}

	void preprocessing(int choice){
		if(choice==0){
			body=new Texture("Tanks\\A\\Body.png");
			cap=new Texture("Tanks\\A\\CannonCap.png");
			cannon=new Texture("Tanks\\A\\Cannon.png");
			bullet=new Texture("Bullet\\Bullet.png");
			Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
			Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
			Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());
			Bullet=new Sprite(bullet,0,0, bullet.getWidth(), bullet.getHeight());

			speed=3;
			Length=80;
			clearance=0;
			left=70;
			right=17;
			center=new Vector2(-25,-25);
			criticalRadius=100;
			health=1f;
			fuel=1;
			milage=0.01f;
		}
		else if(choice==1){
			body=new Texture("Tanks\\B\\Body.png");
			cap=new Texture("Tanks\\B\\CannonCap.png");
			cannon=new Texture("Tanks\\B\\Cannon.png");
			bullet=new Texture("Bullet\\Bullet.png");
			Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
			Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
			Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());
			Bullet=new Sprite(bullet,0,0, bullet.getWidth(), bullet.getHeight());

			speed=5;
			Length=120;
			clearance=15;
			left=20;
			right=65;
			center=new Vector2(17,4);
			criticalRadius=90;
			health=1f;
			fuel=1;
			milage=0.015f;
		}
		else{
			body=new Texture("Tanks\\C\\Body.png");
			cap=new Texture("Tanks\\C\\CannonCap.png");
			cannon=new Texture("Tanks\\C\\Cannon.png");
			bullet=new Texture("Bullet\\Bullet.png");
			Body=new Sprite(body,0,0, body.getWidth(), body.getHeight());
			Cap=new Sprite(cap,0,0, cap.getWidth(), cap.getHeight());
			Cannon=new Sprite(cannon,0,0, cannon.getWidth(), cannon.getHeight());
			Bullet=new Sprite(bullet,0,0, bullet.getWidth(), bullet.getHeight());

			speed=3;
			clearance=0;
			Length=100;
			left=73;
			right=33;
			center=new Vector2(-17,-20);
			criticalRadius=120;
			health=1f;
			fuel=1;
			milage=0.02f;
		}
	}
	void locate(int gap){
		y0 = ground.scale.get(x0)-clearance;

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

	public void rotate(){
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

		traj.setFlipped(flipped);
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
	@Override
	public void move(boolean value){
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
		x1=x0+(int)(Math.cos(Math.toRadians(angle))*(Length) + Math.sin(Math.toRadians(angle))*0);//length of cannon
		y1=y0+(int)(Math.sin(Math.toRadians(angle))*(Length) + Math.cos(Math.toRadians(angle))*0);
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

	@Override
	public void refuel(){
		fuel=1;
	}

	void impact(int distance,boolean right){
		Gdx.app.log(String.valueOf(distance),String.valueOf(x0));
		if(right){
			if(x0>1400){
				return;
			}
			locate(distance);
		}
		else{
			if(x0<200){
				return;
			}
			locate(-distance);
		}
	}

}

class Trajectory{
	//g==10
	private Terrain ground;

	public Terrain getGround() {
		return ground;
	}

	public void setGround(Terrain ground) {
		this.ground = ground;
	}

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public int getSlowfactor() {
		return slowfactor;
	}

	public void setSlowfactor(int slowfactor) {
		this.slowfactor = slowfactor;
	}

	public int getRangefactor() {
		return rangefactor;
	}

	public void setRangefactor(int rangefactor) {
		this.rangefactor = rangefactor;
	}

	public Texture getDOT() {
		return DOT;
	}

	public void setDOT(Texture DOT) {
		this.DOT = DOT;
	}

	public Sprite getDot() {
		return dot;
	}

	public void setDot(Sprite dot) {
		this.dot = dot;
	}

	public MyGdxGame getGame() {
		return game;
	}

	public void setGame(MyGdxGame game) {
		this.game = game;
	}

	private int x0,y0;
	private int start=0;
	private int gap;
	private double angle;
	private double velocity;
	private double damage=0.1f;
	private boolean flipped;
	private int slowfactor=4;
	private int rangefactor=5;
	private Texture DOT=new Texture("Tracks\\Trackball.png");
	private Sprite dot= new Sprite(DOT,0,0, DOT.getWidth(), DOT.getHeight());
	private MyGdxGame game;
	public Trajectory(MyGdxGame game,Terrain ground) {
		this.game = game;
		this.ground=ground;
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
		this.velocity=power/rangefactor;
		gap=(int)(power/8);
		if(gap>100){
			gap=100;
		}
	}

	double derivative(double x){
		return Math.toDegrees(Math.atan(((float)Math.tan(Math.toRadians(angle)) -10*x/(velocity*velocity*(float)(Math.pow(Math.cos(Math.toRadians(angle)),2))))));
	}

	void draw(boolean valid) {
		if (valid) {

			for (int i = 0; i < 8; i++) {
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

	void follow(Sprite missile,Tank enemy,Screen prev){
		int x=(int)(x0+velocity*Math.cos(Math.toRadians(angle))*start/slowfactor-30);
		int y=(int)(y0+velocity*Math.sin(Math.toRadians(angle))*start/slowfactor-5*start*start/(slowfactor*slowfactor)-6);
		int theta=(int)derivative(velocity*Math.cos(Math.toRadians(angle))*start/slowfactor);
		//Gdx.app.log("Angle",String.valueOf(derivative(start)));
		boolean collided=checkCollision(enemy,x,y,theta);
		if((x<1600 && x>0) && (y>=0) && !collided){
			missile.setOrigin(missile.getWidth()/2, missile.getHeight()/2);
			missile.setRotation(theta);
			missile.setPosition(x, y);
			missile.draw(game.batch);
			start+=1;
		}
		else{
			start=0;
			game.setScreen(prev);
		}
		enemy.initialise();
	}

	boolean checkCollision(Tank here,int startX,int startY,int angle){
		for(int i = 0; i< here.getBullet().getWidth()*Math.cos(Math.toRadians((angle))); i++){
			if((startX+i>=1600) || (startX+i<=0)){
				continue;
			}
			if(ground.scale.get(startX+i)>=startY+(i)*Math.tan(Math.toRadians((angle)))){
				ground.modify(startX+i,ground.scale.get(startX+i)-(int)(velocity/6),false);
				return true;
			}
			if(distance(startX+i,(int)(startY+(i)*Math.tan(Math.toRadians((angle)))),here)<= here.getCriticalRadius()){
				here.setHealth((float)(here.getHealth()-damage));

				if(Math.tan(Math.toRadians((angle)))<0){
					here.impact((int)(velocity)/3,true);
				}
				else{
					here.impact((int)(velocity)/3,false);
				}

				return true;
			}
		}
		return false;
	}

	int distance(int x,int y,Tank here){
		return (int)Math.pow(Math.pow(x-(here.getX0() + here.getCenter().x),2)+Math.pow(y-(here.getY0() + here.getCenter().y),2),0.5f);
	}
}

class Healthbar{
	private Texture border=new Texture("Terrain\\healthborder.png");
	private Sprite Border=new Sprite(border,0,0,border.getWidth(), border.getHeight());
	private MyGdxGame game;

	public Texture getBorder() {
		return border;
	}

	public void setBorder(Sprite border) {
		Border = border;
	}

	public MyGdxGame getGame() {
		return game;
	}

	public void setGame(MyGdxGame game) {
		this.game = game;
	}

	public Texture getHealth() {
		return health;
	}

	public void setHealth(Sprite health) {
		Health = health;
	}

	public boolean isSide() {
		return side;
	}

	public void setSide(boolean side) {
		this.side = side;
	}

	public Texture getFuelgauge() {
		return fuelgauge;
	}

	public void setFuelgauge(Sprite fuelgauge) {
		Fuelgauge = fuelgauge;
	}

	public Tank getTank() {
		return tank;
	}

	public void setTank(Tank tank) {
		this.tank = tank;
	}

	public void setFuelgauge(Texture fuelgauge) {
		this.fuelgauge = fuelgauge;
	}

	public void setHealth(Texture health) {
		this.health = health;
	}

	public void setBorder(Texture border) {
		this.border = border;
	}

	private Texture health;
	private Sprite Health;
	private boolean side;
	private Texture fuelgauge= new Texture("Terrain\\fuel.png");
	private Sprite Fuelgauge = new Sprite(fuelgauge,0,0,fuelgauge.getWidth(),fuelgauge.getHeight());
	private Tank tank;
	public Healthbar(MyGdxGame game, Texture health, boolean side, Tank tank){
		this.game=game;
		this.health= health;
		this.Health= new Sprite(health,0,0,health.getWidth(),health.getHeight());
		this.side = side;
		this.setPosition();
		this.tank = tank;
	}

//	void setLength(float value){
//		Health.setScale(value,1);
//	}

	void setPosition(){
		int x,y;
		if (side){
			x=901;
			y=821;
			Health.setFlip(true,false);
			Border.setFlip(true,false);
			Health.setOrigin(border.getWidth(), 0);
			Fuelgauge.setOrigin(Fuelgauge.getWidth(),0);
			Fuelgauge.setPosition(x+ border.getWidth()-Fuelgauge.getWidth(),y-10);
		}
		else{
			x= 47;
			y= 821;
			Health.setOrigin(0, 0);
			Fuelgauge.setOrigin(0,0);
			Fuelgauge.setPosition(x,y-10);
		}
		Health.setPosition(x,y);
		Border.setPosition(x,y);
	}

	void draw(){
		Health.setScale(tank.getHealth(),1);
		Fuelgauge.setScale(tank.getFuel(),1);
		Fuelgauge.draw(game.batch);
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
