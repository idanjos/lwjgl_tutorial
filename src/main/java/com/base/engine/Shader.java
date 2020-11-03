/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL20.*;
/**
 *
 * @author Kappa
 */
public class Shader {
    private int vertexShader, fragmentShader,program;
    public boolean create(String shader){
        int success;
       // GLFW.glfwMakeContextCurrent(0);
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, readSource(shader+".vs"));
        glCompileShader(vertexShader);
        success = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            System.err.println("Error: shader failed to compile");
        }
        
        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, readSource(shader+".fs"));
        glCompileShader(fragmentShader);
        success = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            System.err.println("Error: fragment failed to compile");
        }
        program = glCreateProgram();
        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);
        glLinkProgram(program);
        success = glGetProgrami(program, GL_LINK_STATUS);
        if(success == GL_FALSE){
            System.err.println("Error: failed to link");
        }
        glValidateProgram(program);
         success = glGetProgrami(program, GL_VALIDATE_STATUS);
        if(success == GL_FALSE){
            System.err.println("Error: failed to validate");
            return false;
        }
       
        return true;
    }
    public void destroy(){
        glDetachShader(program, vertexShader);
        glDetachShader(program, fragmentShader);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDeleteProgram(program);
    }
    public void useShader(){
        glUseProgram(program);
    }
    public String readSource(String file){
        BufferedReader reader = null;
        StringBuilder string = new StringBuilder();
        try{  
            System.out.println(Shader.class.getClassLoader().getResourceAsStream(file));
            
           // Shader.class.getRes
            reader = new BufferedReader(new InputStreamReader(Shader.class.getClassLoader().getResourceAsStream(file)));
            String line;
            while((line = reader.readLine())!=null){
                string.append(line+"\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                if(reader!=null)
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Shader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return string.toString();
    }
}
