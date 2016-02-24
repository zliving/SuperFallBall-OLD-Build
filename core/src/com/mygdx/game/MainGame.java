package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

import GameObjects.Ball;


public class MainGame extends Game {
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 960;
	public SceneLoader sceneLoader;
	//Temporary
	private Ball ball;

	@Override
	public void create () {
		Viewport viewport = new FitViewport(210,330);
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewport);
		//Temporary
		ball = new Ball(100, 100, 25);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Temporary. We will move into gameScreen class for rendering
		ball.update();
		ball.render();
		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

	}
}
