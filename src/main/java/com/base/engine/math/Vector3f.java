/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.math;

/**
 *
 * @author Kappa
 */
public class Vector3f {

    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalize() {
        float length = length();
        x = x / length;
        y = y / length;
        z = z / length;
        return this;
    }
    
    public Vector3f rotate(){
        return null;
    }

    public Vector3f cross(Vector3f r){
        float x_ = y*r.getZ() - z*r.getY();
        float y_ = z*r.getX() - x*r.getZ();
        float z_ = x*r.getY() - y*r.getY();
        return new Vector3f(x_, y_, z_);
    }
    
    public Vector3f add(Vector3f r) {
        x += r.getX();
        y += r.getY();
        return new Vector3f(x, y, z);
    }

    public Vector3f add(float r) {
        x += r;
        y += r;
        return new Vector3f(x, y, z);
    }

    public Vector3f sub(Vector3f r) {
        x -= r.getX();
        y += r.getY();
        return new Vector3f(x, y, z);
    }

    public Vector3f sub(float r) {
        x -= r;
        y -= r;
        return new Vector3f(x, y, z);
    }

    public Vector3f mult(Vector3f r) {
        x *= r.getX();
        y *= r.getY();
        return new Vector3f(x, y, z);
    }

    public Vector3f mult(float r) {
        x *= r;
        y *= r;
        return new Vector3f(x, y, z);
    }

    public Vector3f div(Vector3f r) {
        x = x / r.getX();
        y = y / r.getY();
        return new Vector3f(x, y, z);
    }

    public Vector3f div(float r) {
        x = x / r;
        y = y / r;
        return new Vector3f(x, y, z);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
