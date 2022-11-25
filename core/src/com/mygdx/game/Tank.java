package com.mygdx.game;

import com.badlogic.gdx.physics.bullet.Bullet;

import java.util.ArrayList;

public class Tank {
    private Element skin;
    private ArrayList<Weapon> ammo;

    private float health;

    private float angle;

    private String state;

    private float weight;

    void move(){

    }

    void fire(){
        //this.angle
    }

    void takeDamage(float damage){
        setHealth(this.health-damage);
    }

    public Element getSkin() {
        return skin;
    }

    public void setSkin(Element skin) {
        this.skin = skin;
    }

    public ArrayList<Weapon> getAmmo() {
        return ammo;
    }

    public void setAmmo(ArrayList<Weapon> ammo) {
        this.ammo = ammo;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
