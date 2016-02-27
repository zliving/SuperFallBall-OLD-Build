package GameObjects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

public class Door implements IScript{
    //X and Y positions for the door.
    private int x, y;
    //Size for Door object
    //These sizes are temporary
    int SIZEX = 27;
    int SIZEY = 46;
    private Rectangle collisionRect;

    private Entity doorEntity;
    private TransformComponent transformComponent;

    //FIXME: Add collision detection method.

    @Override
    public void init(Entity entity) {
        doorEntity = entity;
        transformComponent = ComponentRetriever.get(entity, TransformComponent.class);
        collisionRect = new Rectangle(transformComponent.x, transformComponent.y, SIZEX, SIZEY);
        //Test to make sure that collision rectangle is same place as the actual door
//        System.out.println("Transform " + transformComponent.x + " " + transformComponent.y);
//        System.out.println("collisionRect: " + collisionRect.getX() + " " + collisionRect.getY());
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void dispose() {

    }
}
