package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

import GameObjects.Ball;
import GameObjects.Door;


public class MainGame extends Game{
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 960;
	public SceneLoader sceneLoader;
	//Temporary
	private Ball ball;
	private Door door1;

	@Override
	public void create () {

		Viewport viewport = new FitViewport(210,330);
//		sceneLoader = new SceneLoader();
//		sceneLoader.loadScene("MainScene", viewport);
		//Temporary
		ball = new Ball();
		door1 = new Door();
//		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
//		root.getChild("player").addScript(ball);
//		root.getChild("door1").addScript(door1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Temporary. We will move into gameScreen class for rendering
//		ball.update();
//		ball.render();
//		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

	}
}
