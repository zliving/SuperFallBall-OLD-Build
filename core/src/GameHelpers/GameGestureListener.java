package GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGame;

import java.util.ArrayList;

import GameObjects.Ball;
import GameObjects.Door;
import sun.applet.Main;


public class GameGestureListener implements GestureDetector.GestureListener {

    private Ball ball;
    private ArrayList<Door> doors;
    private float scale = 1.0f / 3.0f;

    public GameGestureListener(Ball ball, ArrayList<Door> d)
    {
        super();
        this.ball = ball;
        this.doors = d;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        for(int i = 0; i < doors.size(); i++){
            Door currentDoor = doors.get(i);
            System.out.println(MainGame.worldHeightUnits - y * scale);
            System.out.println(currentDoor.getY());

            if(x * scale >= currentDoor.getX()
                    && x * scale <= currentDoor.getX() + currentDoor.getWidth()
                    && MainGame.worldHeightUnits - y * scale >= currentDoor.getY()
                    && MainGame.worldHeightUnits - y * scale <= currentDoor.getY() + currentDoor.getHeight()){

                System.out.println("Door: " + (i + 1) + " was tapped.");
                currentDoor.switchState();
            }
        }
        System.out.println("Screen was tapped");
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return false;

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        //Only move the ball if the touch is between the balls y bounds.
        if(MainGame.worldHeightUnits - (y * scale) <= ball.getY() + ball.getHeight()
                && MainGame.worldHeightUnits - (y * scale) >= ball.getY() - ball.getHeight()) {
            if (!ball.getDroppingStatus()) {
                ball.setX(((deltaX * scale) + ball.getX()));
            }
        }

        //Moving the ball in the y direction for testing
        ball.setY((-deltaY * scale) + ball.getY());

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance){

        return false;
    }

    @Override
    public boolean pinch (Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer){

        return false;
    }



}
