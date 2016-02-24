package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MainGame;


public class Ball implements InputProcessor{
    //Ball location
    private float x, y;
    private float radius;
    //Used to draw circle
    private ShapeRenderer shapeRenderer;
    //Booleans for checking for movement
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private float velocity = 2.0f;

    public Ball(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
        shapeRenderer = new ShapeRenderer();
        //"Listen" for user input to move the ball;
        Gdx.input.setInputProcessor(this);
    }

    public void update(){
        if(movingUp && y != MainGame.GAME_HEIGHT){
            y += velocity;
        }

        if(movingDown && y != 0){
            y -= velocity;
        }

        if(movingRight && x != MainGame.GAME_WIDTH){
            x += velocity;
        }

        if(movingLeft && x != 0){
            x -= velocity;
        }
    }

    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
    }

    @Override
    public boolean keyDown(int keycode){
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            movingUp = true;
        } else if(!Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            movingDown = true;
        } else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            movingLeft = true;
        } else if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            movingRight = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP){
            movingUp = false;
        } else if(keycode == Input.Keys.RIGHT){
            movingRight = false;
        } else if(keycode == Input.Keys.LEFT){
            movingLeft = false;
        } else if(keycode == Input.Keys.DOWN){
            movingDown = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
