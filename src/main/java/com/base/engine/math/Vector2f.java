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
public class Vector2f {

    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f normalize() {
        float length = length();
        x = x / length;
        y = y / length;
        return this;
    }

    public Vector2f rotate(float angle) {
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        return new Vector2f((float) (x * cos - y * sin), (float) (x * sin + y * cos));
    }

    public Vector2f add(Vector2f r) {
        x += r.getX();
        y += r.getY();
        return new Vector2f(x, y);
    }

    public Vector2f add(float r) {
        x += r;
        y += r;
        return new Vector2f(x, y);
    }

    public Vector2f sub(Vector2f r) {
        x -= r.getX();
        y += r.getY();
        return new Vector2f(x, y);
    }

    public Vector2f sub(float r) {
        x -= r;
        y -= r;
        return new Vector2f(x, y);
    }

    public Vector2f mult(Vector2f r) {
        x *= r.getX();
        y *= r.getY();
        return new Vector2f(x, y);
    }

    public Vector2f mult(float r) {
        x *= r;
        y *= r;
        return new Vector2f(x, y);
    }

    public Vector2f div(Vector2f r) {
        x = x / r.getX();
        y = y / r.getY();
        return new Vector2f(x, y);
    }

    public Vector2f div(float r) {
        x = x / r;
        y = y / r;
        return new Vector2f(x, y);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f r) {
        return 0f;
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

}
