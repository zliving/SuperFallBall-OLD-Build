package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Door implements IScript{
    //Size for Door object
    //These sizes are temporary
    private float SIZEX = 27f;
    private float SIZEY = 46f;
    private Rectangle collisionRect;
    private float scaleCollisionRect = .15f;
    private float scaleWorldUnits = 3.0f;

    //May need entity for later but it is currently unused right now.
    private Entity doorEntity;

    //The transform component contains the position (x, y) of the door
    private TransformComponent transformComponent;

    //FIXME: Add collision detection method.

    @Override
    public void init(Entity entity) {
        doorEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);

        collisionRect = new Rectangle(transformComponent.x + SIZEX * scaleCollisionRect,
                transformComponent.y + SIZEY * scaleCollisionRect,
                SIZEX * (1.0f - 2.0f * scaleCollisionRect),     //scale is multiplied by 2 since we
                SIZEY * (1.0f - 2.0f * scaleCollisionRect));    //trim off from both sides

        //Testing for the collision rectangle
//        System.out.println("collision x: " + collisionRect.getX());
//        System.out.println("collision y: " + collisionRect.getY());
//        System.out.println("Collision width: " + collisionRect.getWidth());
//        System.out.println("Collision height: " + collisionRect.getHeight());
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void dispose() {

    }

    public float getX(){
        return transformComponent.x;
    }

    public float getY(){
        return transformComponent.y;
    }
}
