//package dungeon;
//
///**
// * Created by Lukas on 3/10/2017.
// */
//public class Map {
//
//    private int[][] map;
//    private VampireClan vampireClan;
//    private Player player;
//
//    public Map(int x, int y, VampireClan vampireClan, Player player){
//
//        this.vampireClan = vampireClan;
//        this.player = player;
//        this.map = new int[y][x];
//
//    }
//
//    public void printMap() {
//
//        for (Vampire vampire : vampireClan.getClan()) {
//            //field[vampire.getY()][vampire.getX()] = 2;
//            map.setValue(vampire.getX(),vampire.getY(),2); // THIS IS A CHANGE
//        }
//
//        //field[player.getY()][player.getX()] = 1;
//        map.setValue(player.getX(),player.getY(),1);
//
//        for (int[] row : map.getMap()) { // USED TO BE this.field
//            for (int number : row) {
//                if (number == 0) {
//                    System.out.print(".");
//                } else if (number == 1) {
//                    System.out.print("@");
//                } else if (number == 2) {
//                    System.out.print("v");
//                }
//            }
//            System.out.println();
//        }
//
//    }
//
//
//
//    public int[][] getMap() {
//        return map;
//    }
//
//    public void setMap(int[][] map) {
//        this.map = map;
//    }
//
//    public void setValue(int x, int y, int value){
//
//        map[y][x] = value;
//
//    }
//}
