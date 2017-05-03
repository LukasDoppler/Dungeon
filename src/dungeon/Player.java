package dungeon;

/**
 * Created by Lukas on 3/8/2017.
 */
public class Player extends Entity {

    private Lamp lamp;


    public Player(Lamp lamp) {
        super(0, 0);
        this.lamp = lamp;

    }

    public Lamp getLamp() {
        return lamp;
    }


}
