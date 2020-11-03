/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine.game;

import com.base.engine.Mesh;
import com.base.engine.Window;
import com.base.engine.math.Vector3f;
import com.base.engine.math.Vertex;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 *
 * @author Kappa
 */
public class Game implements Runnable {

    private String version = "1.0.0";
    private GLFWKeyCallback cbfun;
    private Mesh mesh;

    public Game() {
        
    }

    public void init() {
        int i = 0;
        while (Window.getWindow() == 0) {
            if (i > 5) {
                System.out.println("Window not responding!!!");
            } else {
                i++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Game Initiated version " + version);
        config();
        //input();
        //other configs
    }

    public void config() {
        if (Window.isRunning()) {
           
        }
    }

    public void update() {

    }

    public void render() {
        //Call window static functions
        //Window.clearScreen();
       // mesh.draw();
    }

    public void close() {
        glfwSetWindowShouldClose(Window.getWindow(), true);
        // cbfun.free();
    }

    @Override
    public void run() {
        int i = 1;

        init();

        while (Window.isRunning()) {
            render();
            //System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Game ended");
    }
}
