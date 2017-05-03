package dungeon;

/**
 * Created by Lukas on 3/8/2017.
 */
public class Lamp {

    private int charges;


    public Lamp(int charges) {
        this.charges = charges;
    }

    public int getCharges() {
        return charges ;
    }

    public boolean isEmpty() {
        return charges == 0;
    }

    public void use() {

        this.charges--;

    }


}
