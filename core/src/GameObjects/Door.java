package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Door implements IScript{
    //Size for Door object
    //These sizes are temporary
    private float width = 27.0f;
    private float height = 46.0f;

    //For collision rectangle
    private float scale = 0;
    private float scaledWidth;
    private float scaledHeight;

    private Rectangle collisionRect;
    //May need entity for later but it is currently unused right now.
    private Entity doorEntity;

    //The transform component contains the position (x, y) of the door
    private TransformComponent transformComponent;

    //Need to import the ball so that we can check for collision.
    private Ball ball;

    @Override
    public void init(Entity entity) {
        doorEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        scaledWidth = width - (2 * scale * width);
        scaledHeight = height - (2 * scale * height);
        collisionRect = new Rectangle(transformComponent.x + width * scale,
                transformComponent.y + height * scale, scaledWidth, scaledHeight);

        System.out.println("x: " + transformComponent.x);
        System.out.println("y: " + transformComponent.y);
        System.out.println("x coll: " + collisionRect.getX());
        System.out.println("y coll: " + collisionRect.getY());
        System.out.println("coll width: " + collisionRect.getWidth());
        System.out.println("coll height: " + collisionRect.getHeight());
        ball = MainGame.ball;
        //Testing for the collision rectangle
        System.out.println("collision x: " + collisionRect.getX());
        System.out.println("collision y: " + collisionRect.getY());
        System.out.println("Collision width: " + collisionRect.getWidth());
        System.out.println("Collision height: " + collisionRect.getHeight());
    }

    @Override
    public void act(float delta) {
        if(isColliding(ball)){
            System.out.println("Colliding");
            //Collides from the left side
            if(ball.getCollisionRect().getX() <= collisionRect.getX()) {
                ball.setX(transformComponent.x - ball.getWidth());
            }

            //Collides from the right side of the door
            if(ball.getCollisionRect().getX() > collisionRect.getX()){
                ball.setX(transformComponent.x + width);
            }

            //Collides from the bottom
            if(ball.getCollisionRect().getY() <= collisionRect.getY()){
                ball.setY(transformComponent.y - ball.getHeight());
            }

            //Collides from the top
            if(ball.getCollisionRect().getY() > collisionRect.getY()){
                ball.setY(transformComponent.y + height);
            }
        }
    }

    @Override
    public void dispose() {

    }

    public boolean isColliding(Ball b){
        return b.getCollisionRect().overlaps(collisionRect);
    }

    //Getters and Setters
    public float getX(){
        return transformComponent.x;
    }

    public float getY(){
        return transformComponent.y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

}
