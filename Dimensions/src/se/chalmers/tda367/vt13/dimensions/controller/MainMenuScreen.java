package se.chalmers.tda367.vt13.dimensions.controller;


import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen implements Screen {

	private Dimensions game;
	private SpriteBatch batch;
	private BitmapFont font;
	private final Table table;
	private final TextButton start;
	private final TextButton option;
	private final TextButton exit;
	private TextButtonStyle buttonStyle = new TextButtonStyle();
	private Stage stage;
	

	public MainMenuScreen(final Dimensions game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		table = new Table();
		stage = new Stage();
		buttonStyle.font = new BitmapFont();
	    buttonStyle.fontColor = Color.WHITE;
	    buttonStyle.overFontColor = Color.RED;
	    buttonStyle.pressedOffsetY = 1f;
	    buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);
		start = new TextButton("Start game",buttonStyle);
		option = new TextButton("Options",buttonStyle);
		exit = new TextButton("Exit game", buttonStyle);
		
		stage = new Stage();
		table.setFillParent(true);
		table.add(start);
		 start.addListener(new ClickListener(){ 
			 public void clicked(InputEvent e,float x,float y)
			 	{
	        		game.setScreen(game.getGameScreen());
	        		System.out.println("starbutton");
			 	}
			 }
		 );
		 
		 option.addListener(new ClickListener(){
			 public void clicked(InputEvent e, float x, float y)
			 	{
				 game.setScreen(new OptionScreen());
			 	}
		 	}
		);
		 
		 exit.addListener(new ClickListener(){
			 public void clicked(InputEvent e, float x, float y)
			 	{
				 int result = JOptionPane.showConfirmDialog(null, "Do you wish to exit the game", null, JOptionPane.OK_CANCEL_OPTION);
				 if(result == 0){
					 
					 System.exit(0);
				 }
					 
			 	}
		 	}
		);
		
        table.row();
        table.add(option);
        table.row();
        table.add(exit);
        
       
    
        
       
       
        
        stage.addActor(table);
        
        Gdx.input.setInputProcessor(stage);
    }
	

	
	

	@Override
	public void render(float delta) {
		// Clear screen with white color
		Gdx.gl.glClearColor(0, 0, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
		batch.begin();
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		//font.draw(batch, str, 380, 240);
		batch.end();

		/*if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.UP)) {
			game.setScreen(game.getGameScreen());
		}
*/
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
       
    

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
