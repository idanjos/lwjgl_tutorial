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
public class Matrix4f {

    float[][] array;

    public Matrix4f() {
        array = new float[4][4];
    }

    public void initIdenty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    array[i][j] = 1;
                } else {
                    array[i][j] = 0;
                }
            }
        }
    }

    public Matrix4f mult(Matrix4f m) {
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                result.set(i, j, array[i][0] * m.get(0, j)
                        + array[i][1] * m.get(1, j)
                        + array[i][2] * m.get(2, j)
                        + array[i][3] * m.get(3, j)
                );
            }
        }
        return result;
    }

    public float get(int x, int y) {
        return array[x][y];
    }

    public void set(int x, int y, float value) {
        array[x][y] = value;
    }

}
