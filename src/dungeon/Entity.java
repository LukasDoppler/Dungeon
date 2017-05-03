package dungeon;

/**
 * Created by Lukas on 3/8/2017.
 */
public class Entity {

    private boolean isAlive;
    private int x;
    private int y;
    private int maxLength;
    private int maxHeight;

    public Entity(int x, int y) {

        this.x = x;
        this.y = y;
        this.maxLength = x;
        this.maxHeight = y;
        this.isAlive = true;

    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCoordinate() {

        String coordinate = "";
        coordinate += x;
        coordinate += " ";
        coordinate += y;

        return coordinate;

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void die() {
        this.isAlive = false;
    }


}
