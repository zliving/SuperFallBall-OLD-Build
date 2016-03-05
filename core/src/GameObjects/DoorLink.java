package GameObjects;

import com.mygdx.game.MainGame;

import GameHelpers.GameGestureListener;

public class DoorLink {

    private Door door1;
    private Door door2;
    private Ball ball;

    private final int collisionWithDoor1 = 1;
    private final int collisionWithDoor2 = 2;

    public DoorLink(Door d1, Door d2){
        door1 = d1;
        door2 = d2;
        ball = MainGame.ball;
    }

    public Door getDoor1(){
        return door1;
    }

    public Door getDoor2(){
        return door2;
    }

    //Teleports the ball depending on the collision with the given door.
    public void update(){
        if(isColliding()){
            int cDoor = getCollisionDoor();
            System.out.println(cDoor);
        }

    }

    public boolean isColliding(){
        return door1.isColliding(ball) || door2.isColliding(ball);
    }

    //Returns the door that the ball is colliding with and should be used with
    //the is colliding class once that there is confirmation that there was a collision.
    //Note that there is unknown consequences if this function is called before a collision
    //is actually detected.

    public int getCollisionDoor(){
        if(door1.isColliding(ball)){
            return collisionWithDoor1;
        } else {
            return collisionWithDoor2;
        }
    }

}

