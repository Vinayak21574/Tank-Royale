package com.mygdx.game;

import com.badlogic.gdx.physics.bullet.Bullet;

import java.util.ArrayList;

public class Weapon extends Bullet {
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
