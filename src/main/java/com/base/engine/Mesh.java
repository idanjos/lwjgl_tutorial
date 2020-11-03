/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.math.Vertex;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

import static org.lwjgl.opengl.GL20.*;


/**
 *
 * @author Kappa
 */
public class Mesh {
    private int vbo;
    private int size;

    public Mesh() {
      
        vbo = glGenBuffers();
        size = 0;
    }
    public void addVertices(Vertex[] vertices){
        size = vertices.length * Vertex.SIZE;
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlipperBuffer(vertices), GL_STATIC_DRAW);
        
    }
    
    public void draw(){
        
        
        
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE*4, 0);
        glDrawArrays(GL_TRIANGLES, 0, size);
        glDisableVertexAttribArray(0);
    }
}
