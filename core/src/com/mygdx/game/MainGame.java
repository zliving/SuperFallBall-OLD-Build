package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import Scenes.PlayScene;


public class MainGame extends Game{
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 960;


	public static final float worldWidthUnits = 213.33f;
	public static final float worldHeightUnits = 320.0f;

	public static final float scaleWorld = 1 / 3.0f;

	@Override
	public void create () {
		//Starts off by creating a playscene
		//Screen is a field of any class that extends game
		screen = new PlayScene();

		//Sets the current screen
		this.setScreen(screen);
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Call the screen's render method. Every screen we create
		//should implement screen so that we can easily render individual screens
		screen.render(Gdx.graphics.getDeltaTime());
	}
}
