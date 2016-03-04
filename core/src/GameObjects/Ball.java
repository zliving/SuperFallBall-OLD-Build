package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;


import org.w3c.dom.css.Rect;

import GameHelpers.GameGestureListener;
import sun.applet.Main;

//New Branch merge test
public class Ball implements IScript{

    private Entity ballEntity;
    private TransformComponent transformComponent;
    private float scaleUnits = 3.0f;
    private Rectangle collisionRect;
    private DimensionsComponent demensionCompent;
    private World world;

    private float velocity = 1.0f;
    private float gravity = -7.0f;
    private float radius = 12; //World units
    private float height, width;
    private float verticalSpeed = 1.5f;
    public Vector2 speed;
    private boolean isDropping = false;

    public boolean colliding = false;

    public Ball(World world)
    {
        this.world = world;
    }

    @Override
    public void init(Entity entity) {
        width = height = 2 * radius;
        ballEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        //automatically find width and height of object based on sprite size. FANCY
        demensionCompent = ComponentRetriever.get(entity, DimensionsComponent.class);
        collisionRect = new Rectangle(transformComponent.x, transformComponent.y, width, height);
        speed = new Vector2(0,-100f);
    }

    @Override
    public void act(float delta) {

        //transformComponent.y -= gravity;   //Gravity always acting on the ball;



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

        if(getDroppingStatus()) {
            speed.y += gravity * delta;
            transformComponent.y += (speed.y*delta);
        }
        else {
            speed.x += speed.x * delta;
            if(transformComponent.x >= 0 && transformComponent.x <= 330)
            {
            transformComponent.x += (speed.x*delta);
            }
        }




        updateBounds();
        rayCast();
//        Testing
//        System.out.println("Transform ball x: " + transformComponent.x);
//        System.out.println("Transform ball y: " + transformComponent.y);
//        System.out.println("Collision ball x: " + collisionRect.getX());
//        System.out.println("Collision ball y: " + collisionRect.getY());

    }

   private void rayCast() {
        float rayGap = demensionCompent.height/2;
        float raySize =  -(speed.y+Gdx.graphics.getDeltaTime())*Gdx.graphics.getDeltaTime();

      /*  if(raySize<5f){
            raySize = 5f;
        }*/
        if(speed.y>=0){return;}
       // Vectors of ray from middle bottom
       Vector2 rayFrom = new Vector2((transformComponent.x+demensionCompent.width/2)* PhysicsBodyLoader.getScale(), (transformComponent.y + rayGap) * PhysicsBodyLoader.getScale());
       Vector2 rayTo = new Vector2((transformComponent.x + demensionCompent.width / 2) * PhysicsBodyLoader.getScale(), (transformComponent.y - raySize) * PhysicsBodyLoader.getScale());

       // Cast the ray
       world.rayCast(new RayCastCallback() {
           @Override
           public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
               // Stop the player
               speed.y = 0;
               isDropping = false;

               // reposition player slightly upper the collision point
               transformComponent.y = point.y / PhysicsBodyLoader.getScale()+ 0.1f;

               return 0;
           }
       }, rayFrom, rayTo);
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
        return demensionCompent.width;
    }

    public float getHeight(){
        return demensionCompent.height;
    }

    public float getRadius() {
        return radius;
    }

    public Rectangle getCollisionRect(){
        return collisionRect;
    }

    public boolean getDroppingStatus()
    {return isDropping;}

}
