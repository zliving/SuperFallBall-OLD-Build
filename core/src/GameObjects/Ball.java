package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

import org.w3c.dom.css.Rect;

import sun.applet.Main;

//New Branch merge test
public class Ball implements IScript {

    private Entity ballEntity;
    private TransformComponent transformComponent;
    private float scaleUnits = 3.0f;
    private Rectangle collisionRect;

    private float velocity = 1.0f;
    private float gravity = 2.0f;
    private float radius = 12; //World units
    private float height, width;

    public boolean colliding = false;

    @Override
    public void init(Entity entity) {
        width = height = 2 * radius;
        ballEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        collisionRect = new Rectangle(transformComponent.x, transformComponent.y, width, height);
    }

    @Override
    public void act(float delta) {

        //If ball has reached the floor
        if(collisionRect.getY() <= 0){
            transformComponent.y = 0;
        } else {
            transformComponent.y -= gravity;   //Gravity always acting on the ball;

        }


        //Movement
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            transformComponent.x -= velocity;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            transformComponent.x += velocity;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            transformComponent.y += velocity;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            transformComponent.y -= velocity;
        }

        updateBounds();
//        Testing
//        System.out.println("Transform ball x: " + transformComponent.x);
//        System.out.println("Transform ball y: " + transformComponent.y);
//        System.out.println("Collision ball x: " + collisionRect.getX());
//        System.out.println("Collision ball y: " + collisionRect.getY());
    }

    @Override
    public void dispose() {

    }

    public void updateBounds(){
        collisionRect.setX(transformComponent.x);
        collisionRect.setY(transformComponent.y);
    }

    //Getters and setters
    public float getX() {
        return transformComponent.x;
    }

    public void setX(float x) {
        transformComponent.x = x;
    }

    public float getY() {
        return transformComponent.y;
    }

    public void setY(float y) {
        transformComponent.y = y;
    }

    public float getWidth(){
        return 2 * radius;
    }

    public float getHeight(){
        return 2 * radius;
    }

    public float getRadius() {
        return radius;
    }

    public Rectangle getCollisionRect(){
        return collisionRect;
    }
}
