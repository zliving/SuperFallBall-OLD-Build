package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;


public class Ball implements IScript {

    //FIXME: Add collision detection method.
    private Entity ballEntity;
    private TransformComponent transformComponent;
    private PolygonComponent polygonComponent;
    private float velocity = 1.0f;
    private float gravity = 2.0f;

    private Circle collisionCircle;
    private float radius;

    @Override
    public void init(Entity entity) {
        ballEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        radius = transformComponent.x;
        polygonComponent = ComponentRetriever.get(entity, PolygonComponent.class);
        //polygonComponent.makeRectangle();
        //collisionCircle = new Circle(transformComponent.x, transformComponent.y, )
    }

    @Override
    public void act(float delta) {
        //If ball has reached the floor
        if(transformComponent.y <= 0){
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

    }

    @Override
    public void dispose() {

    }
}
