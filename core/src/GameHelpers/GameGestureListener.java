package GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import GameObjects.Ball;


public class GameGestureListener implements GestureDetector.GestureListener {

    private Ball ball;
    private float scaleX = 210.0f/(float)Gdx.graphics.getWidth();
    private float scaleY = 330.0f/(float)Gdx.graphics.getHeight();

    public GameGestureListener(Ball ball)
    {
        super();
        this.ball = ball;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

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
        if(330-(y*scaleY) <= ball.transformComponent.y+ball.dimensionsComponent.height && 330-(y*scaleY)>=ball.transformComponent.y-ball.dimensionsComponent.height ) {
            if (!ball.getDroppingStatus()) {
                ball.setX(((deltaX * scaleX) + ball.getX()));
            }
        }
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
