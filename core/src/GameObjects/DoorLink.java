package GameObjects;

public class DoorLink {
    private Door door1;
    private Door door2;
    public DoorLink(int x1, int y1, int x2, int y2){
        door1 = new Door(x1, y1);
        door2 = new Door(x2, y2);
    }

    public Door getDoor1(){
        return door1;
    }

    public Door getDoor2(){
        return door2;
    }

}
