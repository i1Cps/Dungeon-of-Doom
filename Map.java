import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Reads and contains in memory the map of the game.
 *
 */

public class Map {


    /* amount of exit in map */
    private int availableExit;
    /* amount of gold in map */
    private int availableGold;
    /* checks if gold1 exists */
    private int gold1;
    /* checks if gold2 exists */
    private int gold2;
    /* checks if gold3 exists */
    private int gold3;
    /* checks if gold4 exists */
    private int gold4;
    /* checks if gold5 exists */
    private int gold5;
    /* checks if exit1 exists */
    private int exit1;
    /* checks if exit2 exists */
    private int exit2;
    /* 5x5 map representation */
    private char[][] fiveByFive;
    /* Representation of the map */
    private char[][] map;
    /* exit1 positionX */
    private int exit1PositionX;
    /* exit1 positionY */
    private int exit1PositionY;
    /* exit2 positionX */
    private int exit2PositionX;
    /* exit2 positionY */
    private int exit2PositionY;
    /* gold1 positionX */
    private int gold1PositionX;
    /* gold1 positionY */
    private int gold1PositionY;
    /* gold2 positionX */
    private int gold2PositionX;
    /* gold2 positionY */
    private int gold2PositionY;
    /* gold3 positionX */
    private int gold3PositionX;
    /* gold3 positionY */
    private int gold3PositionY;
    /* gold4 positionX */
    private int gold4PositionX;
    /* gold4 positionY */
    private int gold4PositionY;
    /* gold5 positionX */
    private int gold5PositionX;
    /* gold5 positionY */
    private int gold5PositionY;
    /* Player X position */
    private int playerPositionX;
    /* Player Y position */
    private int playerPositionY;
    /* Bot X position */
    private int botPositionX;
    /* Bot Y position */
    private int botPositionY;
    /* Map name */
    private String mapName;
    /* Gold required for the human player to win */
    private int goldRequired;


    /**
     * Default constructor, creates the default map "Very small Labyrinth of doom".
     */
    public Map() {

        mapName = "Very small Labyrinth of Doom";
        goldRequired = 2;
        /* if gold = 1 it means it exists, if gold = 0 it means it does not exist */
        gold1 = 1;
        gold2 = 1;
        gold3 = 1;
        gold4 = 1;
        gold5 = 1;
        /* if exit1 or exit2 = 1 it means it exists, if exit1 or exit2 = 0 it means it does not exist */
        exit1 = 1;
        exit2 = 1;

        this.playerPositionX = 0;
        this.playerPositionY = 0;
        this.botPositionX = 0;
        this.botPositionY = 0;
        fiveByFive = new char[][]{
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},

        };


        map = new char[][]{
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'E', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', 'E', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        availableGold = howManyGold();
        availableExit = howManyExit();
        findGold();
        findExit();
        if ( availableGold == 1) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = 0;
            gold2PositionY = 0;
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 2) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 3) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 4) {

            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 5) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = getGold5PositionX();
            gold5PositionY = getGold5PositionY();
        }
        if ( availableExit == 1) {
            gold1PositionX = getExit1PositionX();
            gold1PositionY = getExit1PositionY();
            gold2PositionX = 0;
            gold2PositionY = 0;
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 2) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 3) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 4) {

            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 5) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = getGold5PositionX();
            gold5PositionY = getGold5PositionY();
        }




        exit1PositionX = getExit1PositionX();
        exit1PositionY = getExit1PositionY();
        exit2PositionX = getExit2PositionX();
        exit2PositionY = getExit2PositionY();
    }

    /**
     * Constructor that accepts a map to read in from. (Not completed)
     *
     * @param fileName: The filename of the map file.
     */
    public Map(String fileName) {
        readMap(fileName);

        this.playerPositionX = 0;
        this.playerPositionY = 0;
        this.botPositionX = 0;
        this.botPositionY = 0;
        fiveByFive = new char[][]{
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},

        };

        availableGold = howManyGold();
        availableExit = howManyExit();
        findGold();
        findExit();

        if( availableExit == 1) {
            /* if exit1 or exit2 = 1 it means it exists, if exit1 or exit2 = 0 it means it does not exist */
            exit1 = 1;
            exit2 = 0;
        }
        else if( availableExit == 2) {
            /* if exit1 or exit2 = 1 it means it exists, if exit1 or exit2 = 0 it means it does not exist */
            exit1 = 1;
            exit2 = 1;
        }

        if ( availableGold == 1) {
            /* if gold = 1 it means it exists, if gold = 0 it means it does not exist */
            gold1 = 1;
            gold2 = 0;
            gold3 = 0;
            gold4 = 0;
            gold5 = 0;
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = 0;
            gold2PositionY = 0;
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 2) {
            /* if gold = 1 it means it exists, if gold = 0 it means it does not exist */
            gold1 = 1;
            gold2 = 1;
            gold3 = 0;
            gold4 = 0;
            gold5 = 0;
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = 0;
            gold3PositionY = 0;
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 3) {
            /* if gold = 1 it means it exists, if gold = 0 it means it does not exist */
            gold1 = 1;
            gold2 = 1;
            gold3 = 1;
            gold4 = 0;
            gold5 = 0;
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = 0;
            gold4PositionY = 0;
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 4) {

            /* if gold = 1 it means it exists, if gold = 0 it means it does not exist */
            gold1 = 1;
            gold2 = 1;
            gold3 = 1;
            gold4 = 1;
            gold5 = 0;
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = 0;
            gold5PositionY = 0;
        }
        else if ( availableGold == 5) {
            gold1PositionX = getGold1PositionX();
            gold1PositionY = getGold1PositionY();
            gold2PositionX = getGold2PositionX();
            gold2PositionY = getGold2PositionY();
            gold3PositionX = getGold3PositionX();
            gold3PositionY = getGold3PositionY();
            gold4PositionX = getGold4PositionX();
            gold4PositionY = getGold4PositionY();
            gold5PositionX = getGold5PositionX();
            gold5PositionY = getGold5PositionY();
        }




        exit1PositionX = getExit1PositionX();
        exit1PositionY = getExit1PositionY();
        exit2PositionX = getExit2PositionX();
        exit2PositionY = getExit2PositionY();


    }

    /**
     * When player uses look they only see a 5x5 grid relative to where they are
     * @return fiveByFive map
     */
    protected char[][] mapTo5x5Grid() {
        // loops for fiveByFive[][] adding in the elements taking from map[][].
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[0][i] = map[playerPositionY - 2][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[0][i] = '#';
            }
         }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[1][i] = map[playerPositionY - 1][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[1][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[2][i] = map[playerPositionY][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[2][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[3][i] = map[playerPositionY + 1][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[3][i] = '#';
            }
        }
        for(int i = 0; i<5; i++) {
            try {
                fiveByFive[4][i] = map[playerPositionY + 2][playerPositionX + (i - 2)];
            } catch (Exception ex) {
                fiveByFive[4][i] = '#';
            }
        }
        fiveByFive[2][2] = 'P';
        return fiveByFive;
    }

    /**
     * find how many gold is in the map.
     * @return how many gold is in the map.
     */
    protected int howManyGold() {
        int gold = 0;
        for (int i = 0; i < mapSizeX(); i++) {
            for (int j = 0; j < mapSizeY(); j++) {
                if (map[j][i] == 'G') {
                    gold++;
                }
            }
        }
        return gold;
    }

    /**
     * find how many exit is in the map.
     * @return how many exit is in the map.
     */
    protected int howManyExit() {
        int exit = 0;
        for( int i = 0; i < mapSizeX(); i++) {
            for ( int j = 0; j < mapSizeY(); j++) {
                if (map[j][i] == 'E') {
                    exit ++;
                }
            }
        }
        return exit;
    }

    /**
     * Finds position of gold tile pieces and stores them as map attribute
     */
    private void findGold() {
        int g1x = 0;
        int g1y = 0;
        int g2x = 0;
        int g2y = 0;
        int g3x = 0;
        int g3y = 0;
        int g4x = 0;
        int g4y = 0;
        int g5x = 0;
        int g5y = 0;
        for ( int i = 0; i < mapSizeX(); ++i ) {
            for ( int j = 0; j < mapSizeY(); ++j ) {
                if ( map[j][i] == 'G' ) {
                    if (g1x == 0 && g1y == 0) {
                        g1x = i;
                        g1y = j;
                        setGold1PositionX(g1x);
                        setGold1PositionY(g1y);
                    }
                    else if(g2x == 0 && g2y == 0) {
                        g2x = i;
                        g2y = j;
                        setGold2PositionX(g2x);
                        setGold2PositionY(g2y);
                    }
                    else if(g3x == 0 && g3y == 0) {
                        g3x = i;
                        g3y = j;
                        setGold3PositionX(g3x);
                        setGold3PositionY(g3y);
                    }
                    else if(g4x == 0 && g4y == 0) {
                        g4x = i;
                        g4y = j;
                        setGold4PositionX(g4x);
                        setGold4PositionY(g4y);
                    }
                    else if(g5x == 0 && g5y == 0) {
                        g5x = i;
                        g5y = j;
                        setGold5PositionX(g5x);
                        setGold5PositionY(g5y);
                    }
                    
                }
            }
        }
    }

    /**
     * Finds position of exit tile pieces and stores them as map attribute
     */
    private void findExit() {
        int g1x = 0;
        int g1y = 0;
        int g2x = 0;
        int g2y = 0;
        for ( int i = 0; i < mapSizeX(); ++i ) {
            for ( int j = 0; j < mapSizeY(); ++j ) {
                if ( map[j][i] == 'E' ) {
                    if (g1x == 0 && g1y == 0) {
                        g1x = i;
                        g1y = j;
                        setExit1PositionX(g1x);
                        setExit1PositionY(g1y);
                    }
                    else {
                        g2x = i;
                        g2y = j;
                        setExit2PositionX(g2x);
                        setExit2PositionY(g2y);
                    }
                }
            }
        }
    }


    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 1 tile on map.
     */
    protected void resetGold1Tile() {
        int y = getGold1PositionY();
        int x = getGold1PositionX();
        //replaces gold1 tile
        map[y][x] = 'G';
    }


    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 2 tile on map.
     */
    protected void resetGold2Tile() {
        int y = getGold2PositionY();
        int x = getGold2PositionX();
        //replaces gold2 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 3 tile on map.
     */
    protected void resetGold3Tile() {
        int y = getGold3PositionY();
        int x = getGold3PositionX();
        //replaces gold3 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 4 tile on map.
     */
    protected void resetGold4Tile() {
        int y = getGold4PositionY();
        int x = getGold4PositionX();
        //replaces gold4 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a gold tile but does not pick it up and proceeds to move off tile. this method replaces
     * gold 5 tile on map.
     */
    protected void resetGold5Tile() {
        int y = getGold5PositionY();
        int x = getGold5PositionX();
        //replaces gold5 tile
        map[y][x] = 'G';
    }

    /**
     * if a player move onto a exit tile but does not pick it up and proceeds to move off tile. this method replaces
     * exit 1 tile on map.
     */
    protected void resetExit1Tile() {
        int y = getExit1PositionY();
        int x = getExit1PositionX();
        //replaces exit1 tile
        map[y][x] = 'E';
    }

    /**
     * if a player move onto a exit tile but does not pick it up and proceeds to move off tile. this method replaces
     * exit 2 tile on map.
     */
    protected void resetExit2Tile() {
        int y = getExit2PositionY();
        int x = getExit2PositionX();
        //replaces exit2 tile
        map[y][x] = 'E';
    }

    /**
     * setter for attribute which shows if gold 1 is present or not .
     *
     * @param x if x = 1 gold 1 is present else if x = 0 gold 1 is not present ( x should only ever equal 1 or 0 ).
     */
    public void setGold1(int x) {
        this.gold1 = x;
    }

    /**
     * setter for attribute which shows if gold 2 is present or not .
     *
     * @param x if x = 1 gold 2 is present else if x = 0 gold 2 is not present ( x should only ever equal 1 or 0 ).
     */
    protected void setGold2(int x) {
        this.gold2 = x;
    }

    /**
     * setter for attribute which shows if gold 3 is present or not .
     *
     * @param x if x = 1 gold 3 is present else if x = 0 gold 3 is not present ( x should only ever equal 1 or 0 ).
     */
    protected void setGold3(int x) {
        this.gold3 = x;
    }

    /**
     * setter for attribute which shows if gold 4 is present or not .
     *
     * @param x if x = 1 gold 4 is present else if x = 0 gold 4 is not present ( x should only ever equal 1 or 0 ).
     */
    protected void setGold4(int x) {
        this.gold4 = x;
    }

    /**
     * setter for attribute which shows if gold 5 is present or not .
     *
     * @param x if x = 1 gold 5 is present else if x = 0 gold 5 is not present ( x should only ever equal 1 or 0 ).
     */
    protected void setGold5(int x) {
        this.gold5 = x;
    }

    
    /**
     * getter for attribute which shows if gold 1 is present or not.
     *
     * @return attribute which shows if gold 1 if present or not.
     */
    public int getGold1() {
        return gold1;
    }

    /**
     * getter for attribute which shows if gold 2 is present or not.
     *
     * @return attribute which shows if gold 2 if present or not.
     */
    public int getGold2() {
        return gold2;
    }

    /**
     * getter for attribute which shows if gold 3 is present or not.
     *
     * @return attribute which shows if gold 3 if present or not.
     */
    public int getGold3() {
        return gold3;
    }

    /**
     * getter for attribute which shows if gold 4 is present or not.
     *
     * @return attribute which shows if gold 4 if present or not.
     */
    public int getGold4() {
        return gold4;
    }

    /**
     * getter for attribute which shows if gold 5 is present or not.
     *
     * @return attribute which shows if gold 5 if present or not.
     */
    public int getGold5() {
        return gold5;
    }

    /**
     * getter for attribute which shows if exit 1 is present or not.
     *
     * @return attribute which shows if exit 1 if present or not.
     */
    public int getExit1() {
        return exit1;
    }

    /**
     * getter for attribute which shows if exit 1 is present or not.
     *
     * @return attribute which shows if exit 1 if present or not.
     */
    public int getExit2() {
        return exit2;
    }

    /**
     * Setter for x coordinate of exit 1.
     *
     * @param x coordinate of exit 1.
     */
    public void setExit1PositionX(int x) {
        this.exit1PositionX = x;
    }

    /**
     * Getter for x coordinate of exit 1.
     *
     * @return the x coordinate of exit 1.
     */
    public int getExit1PositionX() {
        return this.exit1PositionX;
    }

    /**
     * Setter for y coordinate of exit 1.
     *
     * @param y coordinate of exit 1.
     */
    public void setExit1PositionY(int y) {
       this.exit1PositionY = y;
    }

    /**
     * Getter for y coordinate of exit 1.
     *
     * @return the y coordinate of exit 1.
     */
    public int getExit1PositionY() {
        return this.exit1PositionY;
    }

    /**
     * Setter for x coordinate of exit 2.
     *
     * @param x coordinate of exit 2.
     */
    public void setExit2PositionX(int x) {
        this.exit2PositionX = x;
    }

    /**
     * Getter for x coordinate of exit 2.
     *
     * @return the x coordinate of exit 2.
     */
    public int getExit2PositionX() {
        return this.exit2PositionX;
    }

    /**
     * Setter for y coordinate of exit 2.
     *
     * @param y coordinate of exit 2.
     */
    public void setExit2PositionY(int y) {
        this.exit2PositionY = y;
    }

    /**
     * Getter for y coordinate of exit 2.
     *
     * @return the y coordinate of exit 2.
     */
    public int getExit2PositionY() {
        return this.exit2PositionY;
    }

    /**
     * Setter for y coordinate of gold 1.
     *
     * @param y coordinate of gold 1
     */
    public void setGold1PositionY(int y) {
        this.gold1PositionY = y;
    }

    /**
     * Getter for y coordinate of gold 1.
     *
     * @return the y coordinate of gold 1.
     */
    public int getGold1PositionY() {
        return this.gold1PositionY;
    }

    /**
     * Setter for x coordinate of gold 1.
     *
     * @param x coordinate of gold 1.
     */
    public void setGold1PositionX(int x) {
        this.gold1PositionX = x;
    }

    /**
     * Getter for x coordinate of gold 1.
     *
     * @return the x coordinate of gold 1.
     */
    public int getGold1PositionX() {
        return this.gold1PositionX;
    }

    /**
     * Setter for y coordinate of gold 2.
     *
     * @param y coordinate of gold 2.
     */
    public void setGold2PositionY(int y) {
         this.gold2PositionY = y;
    }

    /**
     * Getter for y coordinate of gold 2.
     *
     * @return the y coordinate of gold 2.
     */
    public int getGold2PositionY() {
        return this.gold2PositionY;
    }

    /**
     * Setter for x coordinate of gold2.
     *
     * @param x coordinate of gold2.
     */
    public void setGold2PositionX(int x) {
        this.gold2PositionX = x;
    }

    /**
     * Getter for x coordinate of gold2.
     *
     * @return the x coordinate of gold2.
     */
    public int getGold2PositionX() {
        return this.gold2PositionX;
    }

    /**
     * Setter for y coordinate of gold 3.
     *
     * @param y coordinate of gold 3
     */
    public void setGold3PositionY(int y) {
        this.gold3PositionY = y;
    }

    /**
     * Getter for y coordinate of gold 3.
     *
     * @return the y coordinate of gold 3.
     */
    public int getGold3PositionY() {
        return this.gold3PositionY;
    }

    /**
     * Setter for x coordinate of gold 3.
     *
     * @param x coordinate of gold 3.
     */
    public void setGold3PositionX(int x) {
        this.gold3PositionX = x;
    }

    /**
     * Getter for x coordinate of gold 3.
     *
     * @return the x coordinate of gold 3.
     */
    public int getGold3PositionX() {
        return this.gold3PositionX;
    }

    /**
     * Setter for y coordinate of gold 4.
     *
     * @param y coordinate of gold 4
     */
    public void setGold4PositionY(int y) {
        this.gold4PositionY = y;
    }

    /**
     * Getter for y coordinate of gold 4.
     *
     * @return the y coordinate of gold 4.
     */
    public int getGold4PositionY() {
        return this.gold4PositionY;
    }

    /**
     * Setter for x coordinate of gold 4.
     *
     * @param x coordinate of gold 4.
     */
    public void setGold4PositionX(int x) {
        this.gold4PositionX = x;
    }

    /**
     * Getter for x coordinate of gold 4.
     *
     * @return the x coordinate of gold 4.
     */
    public int getGold4PositionX() {
        return this.gold4PositionX;
    }

    /**
     * Setter for y coordinate of gold 5.
     *
     * @param y coordinate of gold 5
     */
    public void setGold5PositionY(int y) {
        this.gold5PositionY = y;
    }

    /**
     * Getter for y coordinate of gold 5.
     *
     * @return the y coordinate of gold 5.
     */
    public int getGold5PositionY() {
        return this.gold5PositionY;
    }

    /**
     * Setter for x coordinate of gold 5.
     *
     * @param x coordinate of gold 5.
     */
    public void setGold5PositionX(int x) {
        this.gold5PositionX = x;
    }

    /**
     * Getter for x coordinate of gold 5.
     *
     * @return the x coordinate of gold 5.
     */
    public int getGold5PositionX() {
        return this.gold5PositionX;
    }

    /**
     * Getter for y coordinate of bot.
     *
     * @return the y coordinate of bot.
     */
    public int getBotPositionY() {
        return this.botPositionY;
    }

    /**
     * Getter for x coordinate of bot.
     *
     * @return the x coordinate of bot.
     */
    public int getBotPositionX() {
        return this.botPositionX;
    }

    /**
     * Setter for y coordinate of bot.
     *
     * @param y coordinate.
     */
    public void setBotPositionY(int y) {
        this.botPositionY = y;
    }

    /**
     * Setter for x coordinate of bot.
     *
     * @param x coordinate.
     */
    public void setBotPositionX(int x) {
        this.botPositionX = x;
    }

    /**
     * Getter for y coordinate of player.
     *
     * @return the y coordinate of player.
     */
    public int getPlayerPositionY() {
        return this.playerPositionY;
    }

    /**
     * Getter for x coordinate of player.
     *
     * @return the x coordinate of player.
     */
    public int getPlayerPositionX() {
        return this.playerPositionX;
    }

    /**
     * Setter for y coordinate of player.
     *
     * @param y coordinate.
     */
    public void setPlayerPositionY(int y) {
        this.playerPositionY = y;
    }

    /**
     * Setter for x coordinate of player.
     *
     * @param x coordinate.
     */
    public void setPlayerPositionX(int x) {
        this.playerPositionX = x;
    }

    /**
     * gets random number which represents a direction 0 = North, 1 = East, 2 = South, 3 = West.
     */
    protected void randomBotMove() {
        Random rand = new Random();
        //generates random number between 0 and 3.
        int x = rand.nextInt(4);
        
        if (x == 0) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY - 1][botPositionX] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY - 1][botPositionX] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY - 1][botPositionX] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotN(botPositionY - 1,botPositionX);
                botPositionY = botPositionY -1;

            }
        }
        else if (x == 1) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY][botPositionX + 1] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY][botPositionX + 1] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY][botPositionX + 1] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotE(botPositionY,botPositionX + 1);
                botPositionX = botPositionX + 1;
            }
        }
        else if (x == 2) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY + 1][botPositionX] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY + 1][botPositionX] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY + 1][botPositionX] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotS(botPositionY + 1,botPositionX);
                botPositionY = botPositionY + 1;
            }
        }
        else if (x == 3) {
            // This makes sure bot does not move into a wall
            if(map[botPositionY][botPositionX - 1] == '#') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a gold tile
            else if (map[botPositionY][botPositionX - 1] == 'G') {
                randomBotMove();
            }
            // This makes sure the bot does not move into a exit tile
            else if (map[botPositionY][botPositionX - 1] == 'E') {
                randomBotMove();
            }
            else {
                adjustBotW(botPositionY,botPositionX - 1);
                botPositionX = botPositionX - 1;
            }
        }
    }

    /**
     * move bot tile 1 space in the east direction.
     *
     * @param y current bot y coordinate.
     * @param x current bot x coordinate.
     */
    protected void adjustBotE(int y, int x) {
        map[y][x] = 'B';
        map[y][x - 1] = '.';
    }

    /**
     * move bot tile 1 space in the south direction.
     *
     * @param y current bot y coordinate.
     * @param x current bot x coordinate.
     */
    protected void adjustBotS(int y, int x) {
        map[y][x] = 'B';
        map[y - 1][x] = '.';
    }

    /**
     * move bot tile 1 space in the North direction
     *
     * @param y current bot y coordinate
     * @param x current bot x coordinate
     */
    protected void adjustBotN(int y, int x) {
        map[y][x] = 'B';
        map[y + 1][x] = '.';
    }

    /**
     * move bot tile 1 space in the west direction
     *
     * @param y current bot y coordinate
     * @param x current bot x coordinate
     */
    protected void adjustBotW(int y, int x) {
        map[y][x] = 'B';
        map[y][x + 1] = '.';
    }

    /**
     * move player tile 1 space in the east direction.
     *
     * @param y current player y coordinate.
     * @param x current player x coordinate.
     */
    protected void adjustPlayerE(int y, int x) {
        map[y][x] = 'P';
        map[y][x - 1] = '.';
    }

    /**
     * move player tile 1 space in the south direction.
     *
     * @param y current player y coordinate.
     * @param x current player x coordinate.
     */
    protected void adjustPlayerS(int y, int x) {
        map[y][x] = 'P';
        map[y - 1][x] = '.';
    }

    /**
     * move player tile 1 space in the North direction
     *
     * @param y current player y coordinate
     * @param x current player x coordinate
     */
    protected void adjustPlayerN(int y, int x) {
        map[y][x] = 'P';
        map[y + 1][x] = '.';
    }

    /**
     * move player tile 1 space in the west direction
     *
     * @param y current player y coordinate
     * @param x current player x coordinate
     */
    protected void adjustPlayerW(int y, int x) {
        map[y][x] = 'P';
        map[y][x + 1] = '.';
    }

    /**
     * @return : Gold required to exit the current map.
     */
    protected int getGoldRequired() {
        return goldRequired;
    }

    /**
     * Random number generated between 0 and vertical length of map.
     *
     * @return : number.
     */
    protected int getRandomPositionY() {
        int y = mapSizeY();
        Random rand = new Random();
        //returns random number between 0 and vertical map size - 1 (too ensure it works for index intake)
        return rand.nextInt(y - 1);
    }

    /**
     * Random number generated between 0 and horizontal length of map.
     *
     * @return : number.
     */
    protected int getRandomPositionX() {
        int x = mapSizeX();
        Random rand = new Random();
        //returns random number between 0 and horizontal map size - 1 (too ensure it works for index intake)
        return rand.nextInt(x - 1);
    }

    /**
     * get random position for bot from method "getRandomPositionX" and "getRandomPositionY" and check if that
     * position meets. Conditions for a player to start there. Those condition are that it cannot be a Gold tile,
     * a Wall or a player.
     */
    protected void botStartPosition() {
        //gets random positions
        int y = getRandomPositionY();
        int x = getRandomPositionX();
        //this is so bot does not spawn on gold
        if (map[y][x] == 'G') {
            botStartPosition();
        }
        //this is so bot does not spawn on player
        else if (map[y][x] == 'P') {
            botStartPosition();
        }
        //this is so bot does not spawn on wall
        else if (map[y][x] == '#') {
            botStartPosition();
        }
        //this is so bot does not spawn on exit
        else if (map[y][x] == 'E') {
            botStartPosition();
        }
        else {
            map[y][x] = 'B';
            setBotPositionY(y);
            setBotPositionX(x);
        }
    }

    /**
     * get random position for player from method "getRandomPositionX" and "getRandomPositionY" and check if that
     * position meets. Conditions for a player to start there. Those condition are that it cannot be a Gold tile,
     * a Wall or a bot.
     */
    protected void playerStartPosition() {

        //gets random positions
        int y = getRandomPositionY();
        int x = getRandomPositionX();
        if (map[y][x] == 'G') {
            playerStartPosition();
        }
        else if (map[y][x] == '#') {
            playerStartPosition();
        }
        else if (map[y][x] == 'B') {
            playerStartPosition();
        }
        else {
            map[y][x] = 'P';
            setPlayerPositionY(y);
            setPlayerPositionX(x);
        }
    }

    /**
     * Method which returns vertical length of map.
     *
     * @return vertical length of map.
     */
    protected int mapSizeY() {
        return map.length;
    }

    /**
     * Method which returns horizontal length of map.
     *
     * @return horizontal length of map.
     */
    protected int mapSizeX() {
        return map[0].length;
    }

    /**
     * @return : The map as stored in memory.
     */
    protected char[][] getMap() {
        return map;
    }

    /**
     * @return : The name of the current map.
     */
    protected String getMapName() {
        return mapName;
    }

    protected char[][] mapFormat(String fileName) throws IOException {
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(fileName));
        BufferedReader counter = null;
        counter = new BufferedReader(new FileReader(fileName));

        reader.readLine();
        reader.readLine();

        int intCounter = 0;
        while(counter.readLine() != null) {
            intCounter++;
        }
        // to not include the lines for map name and gold required.
        intCounter = intCounter - 2 ;

        char[] tempMap;
        char[][] map = new char[intCounter][];
        for(int i = 0; i < intCounter ;i++) {
            tempMap = (reader.readLine()).toCharArray();
            map[i] = tempMap;
        }
        return map;
    }
    
    protected int goldRequiredFormat(String line) {
        int lineLen = line.length();
        String[] charGold = new String[lineLen - 5];
        for(int i = 5; i < lineLen; i++) {
            charGold[i - 5] = String.valueOf(line.charAt(i));
        }
        StringBuilder formattedGold = new StringBuilder();
        for (String s : charGold) {
            formattedGold.append(s);
        }
        String gold =formattedGold.toString();
        return Integer.parseInt(gold);
    }

    protected  String mapNameFormat(String name) {
        int nameLen = name.length();
        String[] charName = new String[nameLen - 6];
        for (int i = 6; i < nameLen; i++) {
            charName[i - 6] = String.valueOf(name.charAt(i));
        }
        StringBuilder formattedName = new StringBuilder();
        for (String s : charName) {
            formattedName.append(s);
        }
        return formattedName.toString();
    }

    /**
     * Reads the map from file. (Not complete)
     *
     * @param fileName: file name of map
     */
    protected void readMap(String fileName) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String name = reader.readLine();
            mapName = mapNameFormat(name);
            String stringGoldRequired = reader.readLine();
            goldRequired = goldRequiredFormat(stringGoldRequired);
            map = mapFormat(fileName);
        }
        catch (IOException e) {
        e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

