package GameObjects;

import com.badlogic.gdx.math.Rectangle;

public class Door {
    //X and Y positions for the door.
    private int x, y;
    //Size for Door object
    //These sizes are temporary
    int SIZEX = 40;
    int SIZEY = 60;
    private Rectangle collisionRect;

    public Door(int x, int y){
        this.x = x;
        this.y = y;
        collisionRect = new Rectangle(x, y, SIZEX, SIZEY);
    }

    //FIXME: add collision detection method

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
