package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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

import java.util.ArrayList;

import GameHelpers.GameGestureListener;
import GameObjects.Ball;
import GameObjects.Door;
import GameObjects.DoorLink;


public class MainGame extends Game{
	public static int GAME_WIDTH = 640;
	public static int GAME_HEIGHT = 960;
	public SceneLoader sceneLoader;
	//Temporary
	public static Ball ball;
	private Door door1;
	private Door door2;
	private DoorLink link1;
	private ArrayList<DoorLink> doorLinks;
	public Viewport viewport;

	public static final float worldWidthUnits = 213.33f;
	public static final float worldHeightUnits = 320.0f;

	public static final float scaleWorld = 1 / 3.0f;

	@Override
	public void create () {


		viewport = new FitViewport(worldWidthUnits,worldHeightUnits);
		sceneLoader = new SceneLoader();
		sceneLoader.loadScene("MainScene", viewport);

		//Temporary

		ItemWrapper root = new ItemWrapper(sceneLoader.getRoot());
		ball = new Ball(sceneLoader.world);
		door1 = new Door(sceneLoader.world);
		door2 = new Door(sceneLoader.world);
		link1 = new DoorLink(door1, door2);

		root.getChild("ball").addScript(ball);
		root.getChild("door1").addScript(door1);
		root.getChild("door2").addScript(door2);

		doorLinks = new ArrayList<DoorLink>();
		doorLinks.add(link1);

		System.out.println("door1");
		System.out.println("x: " + door1.getX());
		System.out.println("y: " + door1.getY());
		System.out.println("width " + door1.getWidth());
		System.out.println("height " + door1.getHeight());

		System.out.println("door2");
		System.out.println("x: " + door2.getX());
		System.out.println("y: " + door2.getY());
		System.out.println("width " + door2.getWidth());
		System.out.println("height " + door2.getHeight());

		System.out.println("door1 from link: " + link1.getDoor1().getX());
		System.out.println("door2 from link: " + link1.getDoor2().getX());
		System.out.println("world scale " + GAME_WIDTH * scaleWorld);
		System.out.println("world scale " + GAME_HEIGHT * scaleWorld);

		Gdx.input.setInputProcessor(new GestureDetector(new GameGestureListener(ball, doorLinks)));

	}


	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
	}

	public void update(){
		link1.update();
	}
}
