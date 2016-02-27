package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MainGame;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;


public class Ball implements IScript {
    //Kept this just in case we need them again
  //Ball location
//    private float x, y;
//    private float radius;
//    //Used to draw circle
//    private ShapeRenderer shapeRenderer;
//    //Booleans for checking for movement
//    private boolean movingRight = false;
//    private boolean movingLeft = false;
//    private boolean movingUp = false;
//    private boolean movingDown = false;
//    private float velocity = 2.0f;
//
//    public Ball(float x, float y, float radius){
//        this.x = x;
//        this.y = y;
//        this.radius = radius;
//        shapeRenderer = new ShapeRenderer();
//    }

//    public void update(){
//        if(movingUp && y != MainGame.GAME_HEIGHT){
//            y += velocity;
//        }
//
//        if(movingDown && y != 0){
//            y -= velocity;
//        }
//
//        if(movingRight && x != MainGame.GAME_WIDTH){
//            x += velocity;
//        }
//
//        if(movingLeft && x != 0){
//            x -= velocity;
//        }
//    }
//
//    public void render(){
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.circle(x, y, radius);
//        shapeRenderer.end();
//    }

    //FIXME: Add collision detection method.
    private Entity ballEntity;
    private TransformComponent transformComponent;
    private float velocity = 1.0f;
    private float gravity = 1.0f;

    @Override
    public void init(Entity entity) {
        ballEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
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
