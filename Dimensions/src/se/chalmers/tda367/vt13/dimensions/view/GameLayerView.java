package se.chalmers.tda367.vt13.dimensions.view;

import se.chalmers.tda367.vt13.dimensions.model.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.sun.tools.internal.xjc.reader.gbind.Sequence;

public class GameLayerView {
	private GameWorld world;
	private Stage actionStage = new Stage();
	private Stage mainStage = new Stage();
	private Stage pauseStage = new Stage();
	private Table mainTable = new Table();
	private Table notMainTable = new Table();
	private BitmapFont headerFont = new BitmapFont(
			Gdx.files.internal("impact50.fnt"), false);
	private SpriteBatch batch = new SpriteBatch();
	private BitmapFont warningtext = new BitmapFont(Gdx.files.internal("impact50.fnt"),false);
	private boolean DimensionChange;
	
	

	public GameLayerView(GameWorld world) {
		this.world = world;
		initMainStage();
		initPauseStage();
		warningtext.setColor(Color.RED);
		initActionStage();
		
	}
	
	private void initActionStage(){
		notMainTable.debug();
		notMainTable.setFillParent(true);
		LabelStyle pauseLabelStyle = new LabelStyle(headerFont, Color.RED);
		notMainTable.add().expand();
		notMainTable.row();
		notMainTable.add(new Label("Warning, dimension unstable", pauseLabelStyle)).top();
		actionStage.addActor(notMainTable);
		
	}
	
	private void initAction(){
		
		ParallelAction para = Actions.parallel(Actions.moveTo(200,200,0.5f),Actions.fadeOut(0.5f));
		
		SequenceAction sequence = Actions.sequence(
	             Actions.moveTo(0, 200, 0.8f),
	              Actions.fadeOut(0.5f),
	              Actions.removeActor()             
	            );
		
		//actionStage.addAction(para);
		actionStage.addAction(sequence);
	
				
	}

	private void initMainStage() {
		mainTable.debug();
		mainTable.setFillParent(true);
		LabelStyle pauseLabelStyle = new LabelStyle(headerFont, Color.WHITE);
		mainTable.add().expand();
		mainTable.row();
		mainTable.add(new Label("chaser", pauseLabelStyle)).bottom();
		mainStage.addActor(mainTable);
	}
	public void setDimensionChange(boolean b) {
		this.DimensionChange = b;
	}
	private void initPauseStage() {
		Table mainTable = new Table();
		mainTable.debug();
		mainTable.setFillParent(true);
		LabelStyle pauseLabelStyle = new LabelStyle(headerFont, Color.WHITE);
		mainTable.bottom();
		mainTable.add(new Label("Unpause with ESC", pauseLabelStyle));
		pauseStage.addActor(mainTable);
	}
	
	

	/**
	 * Main draw method. Draws all sub views.
	 */
	public void draw() {
		mainStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		mainStage.draw();
		batch.begin();
		if(this.DimensionChange == true){
		initAction();
		actionStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		actionStage.draw();
		}
		batch.draw(new Texture(Gdx.files.internal("data/player-mini.png")),
				world.getProgress() * Gdx.graphics.getWidth(), 10, 40, 40);
		batch.draw(new Texture(Gdx.files.internal("data/PlayerImg.png")),
				world.getChaserProgress() * Gdx.graphics.getWidth(), 10, 40, 40);
		batch.end();
	}

	/**
	 * Draws pause background and text etc
	 */
	public void drawPaused() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		pauseStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		pauseStage.draw();
	}

}
