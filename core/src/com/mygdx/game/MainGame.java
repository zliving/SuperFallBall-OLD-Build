package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

import GameHelpers.GameGestureListener;
import GameObjects.Ball;
import GameObjects.Door;


public class MainGame extends Game{
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 960;
	public SceneLoader sceneLoader;
	//Temporary
	public static Ball ball;
	private Door door1;
	public Viewport viewport;


	@Override
	public void create () {


		viewport = new FitViewport(210,330);
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewport);
		//Temporary

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
		ball = new Ball(sceneLoader.world);
		door1 = new Door();
		root.getChild("ball").addScript(ball);
		root.getChild("door1").addScript(door1);
		Gdx.input.setInputProcessor(new GestureDetector(new GameGestureListener(ball)));

	}


	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
		//((OrthographicCamera)viewport.getCamera()).position.x = ball.getX()+ball.getRadius()/2f;
	}

	public void update(){
	}
}
