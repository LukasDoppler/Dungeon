package dungeon;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * Created by Lukas on 3/8/2017.
 */
public class Dungeon {

    private int[][] field;
    //private Map map;
    private boolean vampiresMove;
    private VampireClan vampireClan;
    private Player player;
    private int length;
    private int height;

    //4,4,1,100,false
    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {


        this.vampireClan = new VampireClan(vampires, length, height);
        this.player = new Player(new Lamp(moves));
        this.length = length;
        this.height = height;
        this.field = new int[height][length];
        this.vampiresMove = vampiresMove;

    }

    public void printGameStatus() {


        System.out.println("You have " + player.getLamp().getCharges() + " turns left.");
        System.out.println();
        System.out.println("@ " + player.getCoordinate());
        for (Vampire vampire : vampireClan.getClan()) {
            System.out.println("v " + vampire.getCoordinate());
        }
        System.out.println();
        printMap();

    }

    public void printMap() {

        for (Vampire vampire : vampireClan.getClan()) {
            field[vampire.getY()][vampire.getX()] = 2;
            //map.setValue(vampire.getX(),vampire.getY(),2); // THIS IS A CHANGE
        }

        field[player.getY()][player.getX()] = 1;
        //map.setValue(player.getX(),player.getY(),1);

        for (int[] row : this.field) { // USED TO BE this.field
            for (int number : row) {
                if (number == 0) {
                    System.out.print(".");
                } else if (number == 1) {
                    System.out.print("@");
                } else if (number == 2) {
                    System.out.print("v");
                }
            }
            System.out.println();
        }

    }


    public void run() {




        Scanner reader = new Scanner(System.in);
        boolean isLost = false;
        boolean isWon = false;
        boolean doesItWin = false;

        System.out.println("~~~ Welcome and good luck! ~~~");
        System.out.println();
        System.out.println("@ is the player and V are vampires");
        System.out.println("Available commands are W A S D, standing for UP, LEFT, DOWN, RIGHT. Entering multiple is possible");
        System.out.println("If you visit the same field as a vampire, it dies");
        System.out.println("Goal is to kill all vampires, if you run out of turns, you lose");
        System.out.println();

        printGameStatus();

        while (!isLost && !isWon) {


            System.out.println();
            System.out.print("Enter your command/s: ");


            String commands = reader.nextLine();
            System.out.println();

            char[] directions = commands.toCharArray();


            for (char command : directions) {


                // WE MOVE THE PLAYER

                movePlayer(command);


                // THEN WE CHECK IF ANY VAMPIRES HAVE BEEN MET AND IF SO WE REMOVE THEM

                Predicate<Vampire> isAtPlayerLocation = vampire -> vampire.getCoordinate().equals(player.getCoordinate());
                vampireClan.getClan().removeIf(isAtPlayerLocation);


                // WE CHECK IF ALL VAMPIRES MIGHT BE DEAD

                if (vampireClan.getClan().isEmpty()) {

                    isWon = true;
                    break;

                }

                // THEN WE MOVE THE VAMPIRES

                if (this.vampiresMove) {
                    for (Vampire vampire : vampireClan.getClan()) {
                        moveVampire(vampire);
                    }
                }

                // IF ANY VAMPIRES MOVED INTO A PLAYER, THEY DIE


                vampireClan.getClan().removeIf(isAtPlayerLocation);


                // WE CHECK AGAIN IF ALL VAMPIRES ARE DEAD

                if (vampireClan.getClan().isEmpty()) {

                    isWon = true;
                    break;

                }

                // WE USE ONE CHARGE OF THE LAMP AND END THE GAME IF NO CHARGES LEFT AND VAMPIRES STILL ALIVE


                if (player.getLamp().isEmpty() && !vampireClan.getClan().isEmpty()) {

                    isLost = true;


                }

                player.getLamp().use();
                if(player.getLamp().isEmpty()&& !vampireClan.getClan().isEmpty()){
                    isLost = true;
                }







            }




            if (isLost) {
                System.out.println("YOU LOSE");
            } else if (isWon) {

                System.out.println("YOU WIN");
            }

            if (!isLost && !isWon) {
                printGameStatus();

            }
        }


    }

    public void movePlayer(char command) {


        if (command == 'w') {

            if (player.getY() > 0) {
                clearField(player.getX(), player.getY());
                player.setY(player.getY() - 1);
            }

        } else if (command == 's') {

            if (player.getY() < height - 1) {
                clearField(player.getX(), player.getY());
                player.setY(player.getY() + 1);
            }

        } else if (command == 'a') {

            if (player.getX() > 0) {
                clearField(player.getX(), player.getY());
                player.setX(player.getX() - 1);
            }

        } else if (command == 'd') {

            if (player.getX() < length - 1) {
                clearField(player.getX(), player.getY());
                player.setX(player.getX() + 1);
            }

        }


    }

    public void moveVampire(Vampire vampire) {

        // 0 = Up
        // 1 = Down
        // 2 = Right
        // 3 = Left
        int direction = ThreadLocalRandom.current().nextInt(4);

        if (direction == 0) {
            if (vampire.getY() != 0) {

                if (field[vampire.getY() - 1][vampire.getX()] != 2) {
                    clearField(vampire.getX(), vampire.getY());
                    vampire.setY(vampire.getY() - 1);
                }
            }

        } else if (direction == 1) {

            if (vampire.getY() != this.height - 1) {
                if (field[vampire.getY() + 1][vampire.getX()] != 2) {
                    clearField(vampire.getX(), vampire.getY());
                    vampire.setY(vampire.getY() + 1);
                }
            }
        } else if (direction == 2) {

            if (vampire.getX() != this.length - 1) {
                if (field[vampire.getY()][vampire.getX() + 1] != 2) {
                    clearField(vampire.getX(), vampire.getY());
                    vampire.setX(vampire.getX() + 1);
                }
            }
        } else if (direction == 3) {

            if (vampire.getX() != 0) {
                if (field[vampire.getY()][vampire.getX() - 1] != 2) {
                    clearField(vampire.getX(), vampire.getY());
                    vampire.setX(vampire.getX() - 1);
                }
            }
        }


    }

    public void clearField(int x, int y) {

        field[y][x] = 0;

    }

    public VampireClan getVampireClan() {
        return vampireClan;
    }
}
