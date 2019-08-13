package com.example.whichbin;

public class TileMapManager {
    public TileBasedMap map1;

    public TileMapManager(){
        map1 = new TileBasedMap();

        // Left side blocked
        map1.setBlockedSquare(8, 0);
        map1.setBlockedSquare(8, 1);
        map1.setBlockedSquare(8, 2);
        map1.setBlockedSquare(8, 3);
        map1.setBlockedSquare(8, 4);
        map1.setBlockedSquare(8, 5);
        map1.setBlockedSquare(8, 6);
        map1.setBlockedSquare(8, 7);
        map1.setBlockedSquare(8, 8);
        map1.setBlockedSquare(8, 9);
        map1.setBlockedSquare(8, 10);
        map1.setBlockedSquare(8, 11);
        map1.setBlockedSquare(8, 12);
        map1.setBlockedSquare(8, 13);
        map1.setBlockedSquare(8, 14);
        map1.setBlockedSquare(8, 15);



    }

    public TileBasedMap getMap(int mapNumber){
        if(mapNumber == 1){
            return map1;
        }
        else{
            return map1;
        }
    }
}
