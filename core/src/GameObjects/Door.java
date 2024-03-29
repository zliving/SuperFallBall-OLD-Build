package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

import Scenes.PlayScene;

public class Door implements IScript{
    //Size for Door object
    //These sizes are temporary
    private float width = 27.0f;
    private float height = 46.0f;

    //For collision rectangle
    private float scale = 3.0f;
    private boolean doorOpen = false;

    private Rectangle collisionRect;
    //May need entity for later but it is currently unused right now.
    private Entity doorEntity;

    //The transform component contains the position (x, y) of the door
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;

    //Need to import the ball so that we can check for collision.
    private Ball ball;
    private World world;

    //For drawing the collision rectangle for testing
    private ShapeRenderer shapeRenderer;

    public Door (World world)
    {
        this.world = world;
    }


    @Override
    public void init(Entity entity) {
        doorEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
        collisionRect = new Rectangle(transformComponent.x, transformComponent.y,
                dimensionsComponent.width, dimensionsComponent.height);

        shapeRenderer = new ShapeRenderer();
        ball = PlayScene.ball;

        //Testing for the collision rectangle with transform componenet
//        System.out.println("x: " + transformComponent.x);
//        System.out.println("y: " + transformComponent.y);
//        System.out.println("x coll: " + collisionRect.getX());
//        System.out.println("y coll: " + collisionRect.getY());
//        System.out.println("coll width: " + collisionRect.getWidth());
//        System.out.println("coll height: " + collisionRect.getHeight());
//        System.out.println("collision x: " + collisionRect.getX());
//        System.out.println("collision y: " + collisionRect.getY());
//        System.out.println("Collision width: " + collisionRect.getWidth());
//        System.out.println("Collision height: " + collisionRect.getHeight());

    }

    @Override
    public void act(float delta) {
        //Testing for collision rect
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.rect(collisionRect.x * scale, collisionRect.y * scale,
//                collisionRect.width * scale, collisionRect.height * scale);
//        shapeRenderer.end();

        if (isColliding(ball)) {
            System.out.println("Colliding");
            //Collides from the left side
//            if (ball.getCollisionRect().getX() <= collisionRect.getX()) {
//                ball.setX(transformComponent.x - ball.getWidth());
//            }
//
//            //Collides from the right side of the door
//            if (ball.getCollisionRect().getX() > collisionRect.getX()) {
//                ball.setX(transformComponent.x + width);
//            }
//
//            //Collides from the bottom
//            if (ball.getCollisionRect().getY() <= collisionRect.getY()) {
//                ball.setY(transformComponent.y - ball.getHeight());
//            }
//
//            //Collides from the top
//            if (ball.getCollisionRect().getY() > collisionRect.getY()) {
//                ball.setY(transformComponent.y + height);
//            }

        }
        rayCast();
    }

    @Override
    public void dispose() {

    }

    private void rayCast() {
        float rayGap = dimensionsComponent.width/2;
        float raySize =  5f;

        // Vectors of ray from middle bottom
        Vector2 rayFrom = new Vector2((transformComponent.x+ dimensionsComponent.width/2)* PhysicsBodyLoader.getScale(), (transformComponent.y + rayGap) * PhysicsBodyLoader.getScale());
        Vector2 rayTo = new Vector2((transformComponent.x + dimensionsComponent.width / 2) * PhysicsBodyLoader.getScale(), (transformComponent.y - raySize) * PhysicsBodyLoader.getScale());

        // Cast the ray
        world.rayCast(new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {

                System.out.println("Testing");

                return 1;
            }
        }, rayFrom, rayTo);
    }

    public boolean isColliding(Ball b) {
        return b.getCollisionRect().overlaps(collisionRect);
    }

    //Getters and Setters
    public float getX() {
        return transformComponent.x;
    }

    public float getY() {
        return transformComponent.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }



}
