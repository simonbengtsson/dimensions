package se.chalmers.tda367.vt13.dimensions.controller;

import se.chalmers.tda367.vt13.dimensions.view.MainMenuScreen;
import se.chalmers.tda367.vt13.dimensions.view.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Dimensions extends Game {
 

        private MainMenuScreen mainMenuScreen;
        private SplashScreen splashScreen;
        private GameScreen gameScreen;
 

       @Override
        public void create() {
                mainMenuScreen = new MainMenuScreen(this);
                gameScreen = new GameScreen(this);
                splashScreen = new SplashScreen(this);
                setScreen(gameScreen);              
        }
       
       public void newGame(){
    	   gameScreen = new GameScreen(this);
       }
       
       public Screen getMainMenuScreen(){
    	   return mainMenuScreen;
       }
       
       public Screen getGameScreen(){
    	   return gameScreen;
       }
       
       public Screen getSplashScreen(){
    	   return splashScreen;
       }
 }