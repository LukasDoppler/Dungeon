package dungeon;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lukas on 3/8/2017.
 */
public class VampireClan {

    private ArrayList<Vampire> clan;

    public VampireClan(int amountOfVampires, int length, int height) {

        this.clan = new ArrayList<>();
        fillClan(amountOfVampires, length, height);

    }

    public void fillClan(int amountOfVampires, int length, int height) {

        int i = 0;

        while (i < amountOfVampires) {

            //Creates a new Vampire
            Vampire vampire = new Vampire(ThreadLocalRandom.current().nextInt(length), ThreadLocalRandom.current().nextInt(height));

            if (!clan.contains(vampire)) {
                if (!(vampire.getY() == 0 && vampire.getX() == 0)) {
                    clan.add(vampire);
                    i++;
                }


            }
        }

    }

    public ArrayList<Vampire> getClan() {
        return clan;
    }
}
