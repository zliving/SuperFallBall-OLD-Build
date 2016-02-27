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

//New Branch merge test
public class Ball implements IScript {

    //FIXME: Add collision detection method.
    private Entity ballEntity;
    private TransformComponent transformComponent;
    private float scaleUnits = 3.0f;

    private float x;
    private float y;
    private float velocity = 1.0f;
    private float gravity = 2.0f;
    private float radius = 36.0f; //World units is given in overlap

    private Circle collisionCircle;
    private ShapeRenderer debugRenderer;

    @Override
    public void init(Entity entity) {
        ballEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        //Transform component is given in world units
        x = transformComponent.x * scaleUnits;
        y = transformComponent.y * scaleUnits;

        collisionCircle = new Circle(x + radius, y + radius, radius);
        debugRenderer = new ShapeRenderer();
        System.out.println("transformComponent x: " + transformComponent.x);
        System.out.println("transformComponent y: " + transformComponent.y);
        //Testing
        System.out.println("collision circle x: " + collisionCircle.x);
        System.out.println("collision circle y: " + collisionCircle.y);
    }

    @Override
    public void act(float delta) {
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.circle(collisionCircle.x, collisionCircle.y, radius);
        debugRenderer.end();

        //If ball has reached the floor
        if(y <= 0){
            transformComponent.y = 0;
        } else {
//            transformComponent.y -= gravity;   //Gravity always acting on the ball;
            return;
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

        update();

    }

    @Override
    public void dispose() {

    }

    //Update the collision circle
    public void update(){
        collisionCircle.setX((transformComponent.x * scaleUnits) + radius);
        collisionCircle.setY((transformComponent.y * scaleUnits) + radius);
    }

}
