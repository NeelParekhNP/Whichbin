package com.example.whichbin;

public class TileMapManager {
    public TileBasedMap map1;

    public TileMapManager(){
        map1 = new TileBasedMap();

        map1.setAllSquaresUnblocked();

        // Right side blocked
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

        // Left side blocked
        map1.setBlockedSquare(0, 0);
        map1.setBlockedSquare(0, 1);
        map1.setBlockedSquare(0, 2);
        map1.setBlockedSquare(0, 3);
        map1.setBlockedSquare(0, 4);
        map1.setBlockedSquare(0, 5);
        map1.setBlockedSquare(0, 6);
        map1.setBlockedSquare(0, 7);
        map1.setBlockedSquare(0, 8);
        map1.setBlockedSquare(0, 9);
        map1.setBlockedSquare(0, 10);
        map1.setBlockedSquare(0, 11);
        map1.setBlockedSquare(0, 12);
        map1.setBlockedSquare(0, 13);
        map1.setBlockedSquare(0, 14);
        map1.setBlockedSquare(0, 15);

        // middle top squares blocked
        map1.setBlockedSquare(1, 0);
        map1.setBlockedSquare(2, 0);
        map1.setBlockedSquare(3, 0);
        map1.setBlockedSquare(4, 0);
        map1.setBlockedSquare(5, 0);
        map1.setBlockedSquare(6, 0);
        map1.setBlockedSquare(7, 0);

        // middle bottom squares blocked
        map1.setBlockedSquare(1, 15);
        map1.setBlockedSquare(2, 15);
        map1.setBlockedSquare(3, 15);
        map1.setBlockedSquare(4, 15);
        map1.setBlockedSquare(5, 15);
        map1.setBlockedSquare(6, 15);
        map1.setBlockedSquare(7, 15);

        // Map1 specific squares
        map1.setBlockedSquare(1,1);
        map1.setBlockedSquare(2,1);
        map1.setBlockedSquare(3,1);
        map1.setBlockedSquare(4,1);
        map1.setBlockedSquare(5,1);
        map1.setBlockedSquare(6,1);
        map1.setBlockedSquare(7,1);
        map1.setBlockedSquare(1,2);
        map1.setBlockedSquare(1,3);
        map1.setBlockedSquare(3,4);
        map1.setBlockedSquare(4,4);
        map1.setBlockedSquare(5,4);
        map1.setBlockedSquare(1,6);
        map1.setBlockedSquare(2,6);
        map1.setBlockedSquare(3,6);
        map1.setBlockedSquare(4,6);
        map1.setBlockedSquare(5,6);
        map1.setBlockedSquare(7,6);
        map1.setBlockedSquare(4,7);
        map1.setBlockedSquare(5,11);
        map1.setBlockedSquare(6,11);
        map1.setBlockedSquare(7,11);



        map1.setNextActionInstruction("Turn down the temperature on the washing machine.");
        map1.addActionSquareLocation(4,8);
        map1.setActionSquareDescription(4, 8, "Turn down the temperature on the washing machine.");
        map1.setActionSquareOrder(4,8, 1);

        map1.setNextActionInstruction("Make sure the kettle isn't overfilled-just use the water you need.");
        map1.addActionSquareLocation(5,2);
        map1.setActionSquareDescription(5, 2, "Make sure the kettle isn't overfilled-just use the water you need.");
        map1.setActionSquareOrder(5,2, 2);

        map1.setNextActionInstruction("The thermostat is set too high-turn it down.");
        map1.addActionSquareLocation(7,10);
        map1.setActionSquareDescription(7, 10, "The thermostat is set too high-turn it down.");
        map1.setActionSquareOrder(7,10, 3);


        map1.setNextActionInstruction("Make sure the fridge door isn't left open.");
        map1.addActionSquareLocation(7,2);
        map1.setActionSquareDescription(7, 2, "Make sure the fridge door isn't left open.");
        map1.setActionSquareOrder(7,2, 4);
        map1.setNextActionInstruction("You completed the tasks-head to the door to leave!");
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
