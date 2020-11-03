/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.engine;

import com.base.engine.game.Game;
import com.base.engine.game.GameConsole;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kappa
 */
public class MainComponent {

    private Game game;
    private Thread gameThread;
    private GameConsole console;
    private Thread consoleThread;
    private boolean isRunning;
    public static int HEIGHT = 600;
    public static int WIDTH = 800;
    public static String TITLE = "Test";
    public static final double frameCap = 5000.0;
    public Window2 window;
    public Thread windowThread;
    

    public MainComponent() {
        try {
            game = new Game();
            window = new Window2();
            console = new GameConsole();
            //window.init(WIDTH,HEIGHT,TITLE);
            windowThread = new Thread(window);
            gameThread = new Thread(game);
            consoleThread = new Thread(console);
            
            consoleThread.join();
            windowThread.join();
            gameThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }
    
    
    

    public static void main(String[] args) {
        System.out.println("Hello world");
       
        MainComponent mc = new MainComponent();
        
        mc.start();
        //Window.main();
       
       // mc.console();
      mc.tick();

    }

    public  void start() {
        gameThread.start();
        windowThread.start();
        //consoleThread.start();
        
    }

    public void stop() {
      gameThread.stop();
      windowThread.stop();
      consoleThread.stop();
    }

    public void run() {

    }

    public void render() {

    }
    
    public void tick(){
        while(true){
            if(gameThread.isAlive() || windowThread.isAlive()){
                try {
                    
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else
                break;
        }
        stop();
        System.out.println("Main component ended");
    }
    
    

}
