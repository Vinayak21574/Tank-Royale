//package com.mygdx.game;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Circle;
//import com.badlogic.gdx.math.Matrix4;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.*;
//import com.badlogic.gdx.physics.box2d.joints.*;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//import javax.management.StringValueExp;
//import java.util.Random;
//import java.util.Vector;
//
//import static java.lang.Math.abs;
//
//abstract class Figure {
//    Body here;
//    World init;
//    BodyDef def=new BodyDef();
//    FixtureDef fix=new FixtureDef();
//    Shape shape;
//
//    public Figure(World current){
//        this.init=current;
//    }
//
//
//    abstract void configureSHAPE(int x,int y);
//
//    void createFIX(float rho,float mu, float e){
//        fix.density=rho;
//        fix.friction=mu;
//        fix.restitution=e;
//        fix.shape=shape;
//    }
//
//    void setDynamic(boolean flag){
//        if(flag){
//            def.type=BodyDef.BodyType.DynamicBody;
//        }
//        else{
//            def.type=BodyDef.BodyType.StaticBody;
//        }
//    }
//
//    Vector2 locate(){
//        return here.getPosition();
//    }
//
//    void angIMP(int value,boolean flag){
//        here.applyAngularImpulse(value,flag);
//    }
//
//
//    void add(){
//        here=init.createBody(def);
//        here.createFixture(fix);
//    }
//
//    void garbage(){
//        shape.dispose();
//    }
//
//}
//
//class Ball extends Figure{
//    float radius;
//    public Ball(World current, int x, int y, float radius, float rho, float mu, float e) {
//        super(current);
//        this.radius=radius;
//        setDynamic(true);
//        configureSHAPE(x,y);
//        createFIX(rho,mu,e);
//        add();
//    }
//
//    @Override
//    void configureSHAPE(int x, int y) {
//        CircleShape shape_ =new CircleShape();
//        //shape_.setPosition(new Vector2(x,y));
//        shape_.setRadius(radius);
//        shape=shape_;
//    }
//
//    void move(boolean front){
//        int force=100000000;
//        if(front){
//            force=-force;
//        }
////        boolean touched=false;
////        for(Contact temp: init.getContactList()){
////            if(fix.equals(temp.getFixtureA()) || fix.equals(temp.getFixtureB())){
////                touched=true;
////                Gdx.app.log("Here","Touched");
////                break;
////            }Gdx.app.log("Here","No contact");
////        }
//        here.applyTorque(force,true);
//        //here.setAngularVelocity(1);
//
////        if(touched){
////            init.setGravity(new Vector2(0,0));
////            //here.applyForceToCenter(force,0,true);
////            here.applyTorque(force,true);
////
////        }
////        else{
////            //here.applyForceToCenter(force,-10000000,true);
////            here.applyTorque(force,true);
////        }
//
//    }
//}
//
//class Frame extends Figure{
//
//    public Frame(World current, int x, int y, int radius,int gap, float rho, float mu, float e,boolean dynamic){
//        super(current);
//        def.position.set(new Vector2(x,y));
//        setDynamic(dynamic);
//        configureSHAPE(gap,radius);
//        createFIX(rho,mu,e);
//        add();
//    }
//    @Override
//    void configureSHAPE(int gap,int radius) {
//        PolygonShape shape_=new PolygonShape();
//        shape_.setAsBox(gap/2,radius,new Vector2(0,0),0);
//        //shape_.set(new float[]{0,0,-gap/2,-2*radius,gap/2,-2*radius});
//        shape=shape_;
//    }
//}
//class Car{
//    World init;
//    Ball wheelL;
//    Ball wheelR;
//    Frame body;
//    Frame testing;
//
//    int seperation;
//    int wheelbase;
//
//    WheelJoint leftAxis,rightAxis;
//
//
//    public Car(World world, int gap, int radius){
//        this.init=world;
//        this.seperation=gap;
//        this.wheelbase=radius;
//        //body=new Frame(init,0,400,wheelbase,seperation,1,1000000000,0,true);
////        testing=new Frame(init,-gap/2,200,wheelbase,seperation,1,1000000000,0,true);
//        wheelL=new Ball(init,-gap/2,300-3*radius,10,1,1000000000,0);
//        wheelR=new Ball(init,+gap/2,300-3*radius,10,1,1000000000,0);
//
//
////        Body here;
////        BodyDef def=new BodyDef();
////        FixtureDef fix=new FixtureDef();
////        CircleShape shape=new CircleShape();
////
////        shape.setRadius(20);
////        shape.setPosition(new Vector2(-gap/2,300-3*radius));
////
////        fix.density=1;
////        fix.friction=1000000000;
////        fix.restitution=0;
////        fix.shape=shape;
////        def.type=BodyDef.BodyType.DynamicBody;
////        here=init.createBody(def);
////        here.createFixture(fix);
////        //wheelR=new Ball(init,gap/2,300-3*radius,wheelbase,1,1000000000,0);
////
////
//
//        RopeJointDef jnt=new RopeJointDef();
//        jnt.bodyB=wheelR.here;
//        jnt.bodyA=wheelL.here;
//        jnt.collideConnected=false;
//        jnt.maxLength=20;
//        init.createJoint(jnt);
//
//
//
//
////        WheelJointDef jnt=new WheelJointDef();
////        jnt.bodyA=body.here;
////        jnt.bodyB=wheelL.here;
////        //jnt.collideConnected=true;
////        jnt.localAnchorA.set(-gap/2,2*radius);
////        init.createJoint(jnt);
////
////        WheelJointDef Jnt=new WheelJointDef();
////        Jnt.bodyA=body.here;
////        Jnt.bodyB=wheelR.here;
////        //Jnt.collideConnected=true;
////        Jnt.localAnchorA.set(+gap/2,2*radius);
////        init.createJoint(Jnt);
//    }
//
//    void move(boolean flag){
//        if(flag){
//            wheelL.move(flag);
//            wheelR.move(flag);
//        }
//        else{
//            wheelR.move(flag);
//            wheelL.move(flag);
//        }
////        leftAxis.enableMotor(true);
////        leftAxis.setMaxMotorTorque(100000000);
//    }
//}
//
//class Ground extends Figure{
//    Vector2[] scale;
//    Terrain ground;
//    MyGdxGame game;
//    int x,y;
//    float rho,mu,e;
//
//
//    public Ground(World current, MyGdxGame game, int x, int y, float rho, float mu, float e) {
//        super(current);
//        this.game = game;
//        this.x = x;
//        this.y = y;
//        this.rho = rho;
//        this.mu = mu;
//        this.e = e;
//        ground=new Terrain(game);
//        setDynamic(false);
//        initialise();
//    }
//
//    @Override
//    void configureSHAPE(int x, int y) {
//        ChainShape shape_ =new ChainShape();
//        shape_.createChain(scale);
//        shape=shape_;
//    }
//
//    void initialise(){
//        if(here!=null) {
//            Gdx.app.log("Here","Nulled");
//            init.destroyBody(here);
//        }
//
//        ground.reset();
//        Gdx.app.log("Terrain","initialised");
//
//        this.scale=ground.surface();
//        def.position.set(new Vector2(x,y));
//        configureSHAPE(x,y);
//        createFIX(rho,mu,e);
//        add();
//        Gdx.app.log("Body","added");
//    }
//
//
//}
//
//public class Temp implements Screen{
//    MyGdxGame game;
//    int downward=10000;
//    World world=new World(new Vector2(0,-downward),true);
//    Box2DDebugRenderer debugRenderer=  new Box2DDebugRenderer();
//    OrthographicCamera camera=new OrthographicCamera(1600+10,900);
//    Ground gnd=new Ground(world,game,0,-400,1,1000000000,0);
//    //Ball wheel;
//    Car car;
//
//
//
//    public Temp(MyGdxGame game){
//        this.game=game;
//        //wheel=new Ball(world,0,450,6,1,1000000000,0);
//        car=new Car(world,50,10);
//        gnd.game=game;
//        gnd.initialise();
//    }
//
//    @Override
//    public void render(float delta) {
//        //world.step(Gdx.graphics.getDeltaTime(), 8,3);
//        ScreenUtils.clear(0,0,0,1);
//        debugRenderer.render(world,camera.combined);
//        game.batch.begin();
//
//        if(Gdx.input.justTouched()) {
//
//        }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
//            world.step(Gdx.graphics.getDeltaTime(), 8,3);
//            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//                gnd.initialise();
//            }
//            if(Gdx.input.isKeyPressed(Input.Keys.A)){
//                //wheel.move(false);
//                car.move(false);
//            }
//            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
//                //wheel.move(true);
//                car.move(true);
//            }
//
//        }
//
//        game.batch.end();
//    }
//
//
//    @Override
//    public void show() {
//        //wheel=new Ball(world,0,450,10,1,1,0);
//
//
//    }
//
//
//    @Override
//    public void resize(int width, int height) {
////        camera.viewportWidth=width;
////        camera.viewportHeight=height;
////        camera.update();
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void resume() {
//
//    }
//
//    @Override
//    public void hide() {
//        dispose();
//    }
//
//    @Override
//    public void dispose() {
//        //wheel.garbage();
//        gnd.garbage();
//        world.dispose();
//        debugRenderer.dispose();
//    }
//
//    void gravity(int index){
//        Gdx.app.log("HEre", String.valueOf(index));
//        if(index==800){
//            index--;
//        }
//        else if(index==0){
//            index++;
//        }
//
////        Vector2 temp=gnd.scale[index-1];
////        Gdx.app.log(String.valueOf(temp.x), String.valueOf(temp.y));
////        temp.sub(gnd.scale[index+1]);
////        Gdx.app.log(String.valueOf(temp.x), String.valueOf(temp.y));
////
////        temp.dot(new Vector2(0,-downward));
////
////
////        temp.sub(new Vector2(0,-downward));
////
////        world.setGravity(new Vector2(-temp.x,-temp.y));
//
//    }
//
//
//}
