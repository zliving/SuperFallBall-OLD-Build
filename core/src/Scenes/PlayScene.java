package Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

import java.util.ArrayList;

import GameHelpers.GameGestureListener;
import GameObjects.Ball;
import GameObjects.Door;
import GameObjects.DoorLink;


public class PlayScene implements Screen{
    public SceneLoader sceneLoader;

    public static Ball ball;
    private Door door1;
    private Door door2;
    private DoorLink link1;
    private ArrayList<DoorLink> doorLinks;
    public Viewport viewport;

    public static boolean isPaused = false;


    public PlayScene(){
        viewport = new FitViewport(MainGame.worldWidthUnits, MainGame.worldHeightUnits);

        sceneLoader = new SceneLoader();
        sceneLoader.loadScene("MainScene", viewport);


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
        System.out.println("world scale " + MainGame.GAME_WIDTH * MainGame.scaleWorld);
        System.out.println("world scale " + MainGame.GAME_HEIGHT * MainGame.scaleWorld);

        Gdx.input.setInputProcessor(new GestureDetector(new GameGestureListener(ball, doorLinks)));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(!isPaused) {
            sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());
        } else {
            sceneLoader.getEngine().update(0);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
