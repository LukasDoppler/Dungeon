package dungeon;

/**
 * Created by Lukas on 3/8/2017.
 */
public class Vampire extends Entity {


    public Vampire(int x, int y) {
        super(x, y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vampire vampire = (Vampire) o;

        if (isAlive() != vampire.isAlive()) return false;
        if (getX() != vampire.getX()) return false;
        return getY() == vampire.getY();
    }

    @Override
    public int hashCode() {
        int result = (isAlive() ? 1 : 0);
        result = 31 * result + getX();
        result = 31 * result + getY();
        return result;

    }


}
