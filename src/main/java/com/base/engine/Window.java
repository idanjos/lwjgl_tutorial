/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.math.Vector3f;
import com.base.engine.math.Vertex;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import java.util.Random;

import java.nio.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGenBuffers;


import static org.lwjgl.system.MemoryUtil.*;

/**
 *
 * @author Kappa
 */
public class Window implements Runnable {

    private static long window;
    private static long temp = 0;
    private float cap = 50000f;
    private static float[] color = new float[4];
    private int vsync = 0;
    private float frameCap = (float) (1.0 / cap);
    private int height;
    private int width;
    private String title;
    private Mesh mesh;
    private Shader shader;

    private static boolean isRunning = false;

    public void init(int w, int h, String title) {
        color[0] = 1.0f;
        color[1] = 0.0f;
        color[2] = 0.0f;
        color[3] = 0.0f;

        System.out.println("Hello LWJGL " + Version.getVersion() + "!");
        System.out.println(frameCap);
        height = h;
        width = w;
        this.title = title;

    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(temp);
    }

    public static boolean isRunning() {
        return isRunning;
    }

    public static long getWindow() {

        return window;
    }

    public void config() {

  
       
        Vertex[] square = new Vertex[]{new Vertex(new Vector3f(-1, -1, 0)),
            new Vertex(new Vector3f(-1, 1, 0)),
            new Vertex(new Vector3f(0, 1, 0))
           };
        shader = new Shader();
        shader.create("basic");
        mesh = new Mesh();
        mesh.addVertices(square);

       

       
        //TODO Depth Clamp for later
    
        input();
    }

    public void core() {

        int frames = 0;

        float last = (float) glfwGetTime();

        //glfwGetTime()
        while (!glfwWindowShouldClose(window) && isRunning) {
            float current = (float) glfwGetTime();

            if (current - last >= 1.0) {
                // Display the frame count here any way you want.
                //displayFPS(frameCount);
                //System.out.println(frames);
                // System.out.println(());
                glfwSetWindowTitle(window, title + " " + frames);
                frames = 0;
                last = current;

                // break;
            }

            render();
            frames++;

            try {
                Thread.sleep((long) Math.floor(frameCap * 1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void close() {
        glfwSetWindowShouldClose(window, true);
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        isRunning = false;
        // Terminate GLFW and free the error callback
        glfwTerminate();
        try {
            glfwSetErrorCallback(null).free();
        } catch (NullPointerException n) {
            // safe and sound
        }

    }

    public void input() {
        // glfwSetWindowIcon(window, images);
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            switch (button) {
                case GLFW.GLFW_MOUSE_BUTTON_1:
                    System.out.println("Mouse 1");
                    break;
                case GLFW.GLFW_MOUSE_BUTTON_2:
                    System.out.println("Mouse 2");
                    break;
                case GLFW.GLFW_MOUSE_BUTTON_3:
                    System.out.println("Mouse 3");
                    break;

            }
        });
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {

            // System.out.println(key);
            switch (key) {
                case GLFW.GLFW_KEY_ESCAPE:
                    if (action == GLFW.GLFW_RELEASE) {
                        close();
                    }
                    break;
                case GLFW.GLFW_KEY_A:
                    System.out.println("Left");
                    break;
                case GLFW.GLFW_KEY_W:
                    System.out.println("Up");
                    break;
                case GLFW.GLFW_KEY_S:
                    System.out.println("Down");
                    break;
                case GLFW.GLFW_KEY_D:
                    System.out.println("Right");
                    break;

                default:
                    System.out.println("Unknown");
            }

        });
    }

    public static void clearScreen() {

        color[0] = 0.0f;
        color[1] = 0.0f;
        color[2] = 0.0f;
        color[3] = 0.0f;
    }

    public static void changeColor() {

        Random r = new Random();

        color[0] = r.nextFloat();
        color[1] = r.nextFloat();
        color[2] = r.nextFloat();
        color[3] = r.nextFloat();
    }

    public void render() {
        //glClearColor(color[0], color[1], color[2], color[3]);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
      shader.useShader();
          mesh.draw();
        glfwSwapBuffers(window); // swap the color buffers
        
        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }

    @Override
    public void run() {

        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        //glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        window = glfwCreateWindow(width, height, title, NULL, NULL);
       
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(vsync); // Vertical Sync, vsync
         config();
        glfwShowWindow(window);
        isRunning = true;
       
        //System.out.println(window);
        core();
        System.out.println("Window ended");
        close();

    }

}
