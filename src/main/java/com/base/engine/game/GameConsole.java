package com.base.engine.game;


import com.base.engine.Window;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kappa
 */
public class GameConsole implements Runnable{

    @Override
    public void run() {
        console();
    }
    
    public void console(){
        Scanner sc = new Scanner(System.in);
       
        String input = sc.next();
        while(!input.equals("exit")){
            //something 
            switch(input){
                case "test":
                    System.out.println("Test command");
                    break;
                case "render":
                    Window.changeColor();
                  
                case "clear":
                    Window.clearScreen();
                    break;
                case "date":
                    Date d = new Date();
                    System.out.println(d.toString());
                    break;
                default:
                    System.out.println("Invalid command");
            }
            input = sc.next();
        }
        System.out.println("Console End");
      
    }
    
}
