/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.math.Vertex;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

/**
 *
 * @author Kappa
 */
public class Util {
    public static FloatBuffer createFloatBuffer(int size){
        return BufferUtils.createFloatBuffer(size);
    }
    public static FloatBuffer createFlipperBuffer(Vertex[] vertices){
        FloatBuffer buffer = createFloatBuffer(vertices.length*Vertex.SIZE);
        for(int i  = 0; i<vertices.length;i++){
            buffer.put(vertices[i].getPos().getX());
            buffer.put(vertices[i].getPos().getY());
            buffer.put(vertices[i].getPos().getZ());
            
        }
        buffer.flip();
        
        return buffer;
    }
}
