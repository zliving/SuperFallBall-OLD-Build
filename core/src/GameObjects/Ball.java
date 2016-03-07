package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

//New Branch merge test
public class Ball implements IScript{

    private Entity ballEntity;
    public TransformComponent transformComponent;
    private float scaleUnits = 3.0f;
    public Rectangle collisionRect;
    public DimensionsComponent dimensionsComponent;
    private World world;


    private float gravity = -7.0f;
    private float radius = 12; //World units
    private float height, width;

    public Vector2 speed;
    private boolean isDropping = true;


    public boolean colliding = false;

    private ShapeRenderer shapeRenderer;

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
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);

        speed = new Vector2(0, 0);
        //Ball will always start at the top of the screen
        transformComponent.x = (MainGame.worldWidthUnits/2) - dimensionsComponent.width / 2;
        transformComponent.y = MainGame.worldHeightUnits - dimensionsComponent.height;
        collisionRect = new Rectangle(transformComponent.x, transformComponent.y, dimensionsComponent.width,
                dimensionsComponent.height);

        shapeRenderer = new ShapeRenderer();
    }
    public void act(float delta) {

        if(getDroppingStatus()) {
            speed.y += gravity * delta;
            transformComponent.y += (speed.y*delta);
            speed.x+=speed.x * delta;

        }


            if(transformComponent.x >= 0 && transformComponent.x <= MainGame.worldWidthUnits)
            {
                transformComponent.x += (speed.x*delta);
            }
            else if(transformComponent.x <0) {
                transformComponent.x =(MainGame.worldWidthUnits - dimensionsComponent.width);
            }
            else
            {
                transformComponent.x = 0;
            }


        updateBounds();
        rayCast();

    }

   private void rayCast() {
        float rayGap = dimensionsComponent.height/2;
        float raySize =  -(speed.y+Gdx.graphics.getDeltaTime())*Gdx.graphics.getDeltaTime();

      /*  if(raySize<5f){
            raySize = 5f;
        }*/
        if(speed.y>=0){return;}
       // Vectors of ray from middle bottom
       Vector2 rayFrom = new Vector2((transformComponent.x+ dimensionsComponent.width/2)* PhysicsBodyLoader.getScale(), (transformComponent.y + rayGap) * PhysicsBodyLoader.getScale());
       Vector2 rayTo = new Vector2((transformComponent.x + dimensionsComponent.width / 2) * PhysicsBodyLoader.getScale(), (transformComponent.y - raySize) * PhysicsBodyLoader.getScale());

       // Cast the ray
       world.rayCast(new RayCastCallback() {
           @Override
           public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
               // Stop the player
               speed.y = 0;
               isDropping = false;

               // reposition player slightly upper the collision point
               transformComponent.y = point.y / PhysicsBodyLoader.getScale()+ 0.1f;
                System.out.println(fixture.getUserData());
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
        return dimensionsComponent.width;
    }

    public float getHeight(){
        return dimensionsComponent.height;
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
