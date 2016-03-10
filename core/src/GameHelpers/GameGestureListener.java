package GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MainGame;

import java.util.ArrayList;

import GameObjects.Ball;
import GameObjects.Door;
import GameObjects.DoorLink;


public class GameGestureListener implements GestureDetector.GestureListener {

    private Ball ball;
    private ArrayList<DoorLink> doorLinks;
    private float scaleY = MainGame.worldHeightUnits/(float)Gdx.graphics.getHeight();
    private float scaleX = MainGame.worldWidthUnits/(float)Gdx.graphics.getWidth();

    public GameGestureListener(Ball ball, ArrayList<DoorLink> d)
    {
        super();
        this.ball = ball;
        this.doorLinks = d;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        for(int i = 0; i < doorLinks.size(); i++){
            DoorLink currentLink = doorLinks.get(i);

            if(x * scaleX >= currentLink.getDoor1().getX()
                    && x * scaleX <= currentLink.getDoor1().getX() + currentLink.getDoor1().getWidth()
                    && MainGame.worldHeightUnits - y * scaleY >= currentLink.getDoor1().getY()
                    && MainGame.worldHeightUnits - y * scaleY <= currentLink.getDoor1().getY() + currentLink.getDoor1().getY()){

                System.out.println("Door: " + (1) + " was tapped.");
                currentLink.switchState();
            } else if(x * scaleX >= currentLink.getDoor2().getX()
                    && x * scaleX <= currentLink.getDoor2().getX() + currentLink.getDoor2().getWidth()
                    && MainGame.worldHeightUnits - y * scaleY >= currentLink.getDoor2().getY()
                    && MainGame.worldHeightUnits - y * scaleY <= currentLink.getDoor2().getY() + currentLink.getDoor2().getY()){

                System.out.println("Door: " + 2 + " was tapped.");
                currentLink.switchState();
            }
        }
        System.out.println("Screen was tapped");

        if(!ball.getDroppingStatus()){
        ball.setDroppingStatus(true);
        }
        else
        {
            ball.setDroppingStatus(false);
        }
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
            //System.out.println(scaleY);
            System.out.println("Game height:"+ MainGame.worldHeightUnits+" Y Touched"+(MainGame.worldHeightUnits-(y*scaleY
            ))+" Ball Y "+ball.getY());
        //Only move the ball if the touch is between the balls y bounds.
        if(MainGame.worldHeightUnits - (y * scaleY) <= ball.getY() + ball.getHeight()+10
                && MainGame.worldHeightUnits - (y * scaleY) >= ball.getY() - ball.getHeight()-10) {
            System.out.println("Is touching ball");
            if (!ball.getDroppingStatus()) {
                ball.setX(((deltaX * scaleX) + ball.getX()));
            }
        }

        //Moving the ball in the y direction for testing
        //ball.setY((-deltaY * scale) + ball.getY());

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
