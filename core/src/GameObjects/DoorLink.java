package GameObjects;

public class DoorLink {

    private Door door1;
    private Door door2;
    public DoorLink(Door d1, Door d2){
        door1 = d1;
        door2 = d2;
    }

    public Door getDoor1(){
        return door1;
    }

    public Door getDoor2(){
        return door2;
    }

}

