import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

    /**
     * Contains the main logic part of the game, as it processes.
     *
     */
    public class GameLogic {

        private String mapFolderPath;
        private Map map;
        private HumanPlayer humanPlayer;
        private boolean gameRunning;
        private int goldAmount;


        /**
         * Default constructor
         */
        public GameLogic() {

            //REALLY IMPORTANT - PUT FILE PATH OF MAP FOLDER HERE
            mapFolderPath = "C:\\Users\\Public\\Example maps";
            // amount of gold player owns, starts with 0.
            goldAmount = 0;
            map = new Map();
            humanPlayer = new HumanPlayer();
            // if false, game stops running.
            gameRunning = true;
        }

        /**
         * method which prints data/text with delay between each character
         *
         * @param data  of which your want to rpint with delay
         * @param unit  the time unit of the delay
         * @param delay delay amount
         * @throws InterruptedException
         */
        private static void printWithDelays(String data, TimeUnit unit, long delay)
                throws InterruptedException {
            for (char ch : data.toCharArray()) {
                System.out.print(ch);
                unit.sleep(delay);
            }
        }

        /**
         * Introduction to game, asks player to pick a map
         *
         * @throws InterruptedException
         */
        private void introduction() throws InterruptedException {

            System.out.println("");
            System.out.println("");
            System.out.println("");
            printWithDelays("Welcome to Dungeons of Doom!, To enhance your gaming session please read the README file!", TimeUnit.MILLISECONDS, 50);
            System.out.println("");
            TimeUnit.MILLISECONDS.sleep(500);
            printWithDelays("To pick a map please input a number for which map you want. E.G. 1 for the first map shown. To play the default map enter input 0", TimeUnit.MILLISECONDS, 50);
            System.out.println("");

            TimeUnit.MILLISECONDS.sleep(500);
            printWithDelays("Here are your options!", TimeUnit.MILLISECONDS, 50);
            System.out.println("");
            TimeUnit.MILLISECONDS.sleep(500);
            printWithDelays("Maps:", TimeUnit.MILLISECONDS, 50);
            System.out.println("");
            System.out.println("");
            printWithDelays("Map 0 - Default Map", TimeUnit.MILLISECONDS, 50);
            System.out.println("");
        }

        /**
         * method, which based on player input chooses the map the player wants.
         *
         * @throws InterruptedException
         * @throws IOException
         */
        private void pickMaps() throws InterruptedException, IOException {
            InputStreamReader input = new InputStreamReader(System.in);

            //obtains folder of maps
            File folder = new File(mapFolderPath);
            File[] listOfFiles = folder.listFiles();

            //if no maps are in the folder, returns "No maps found"
            if (listOfFiles == null) {
                System.out.println("No maps found!");
            }
            // this prints out all the maps in the folder, so the player knows their options
            else {
                int number = 1;
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        // prints with delay
                        printWithDelays("Map " + number + " - " + file.getName(), TimeUnit.MILLISECONDS, 50);
                        System.out.println("");
                    }
                    number++;

                }
            }
            System.out.println("");
            printWithDelays("If you want to quit, type 'QUIT' ", TimeUnit.MILLISECONDS, 50);
            System.out.println("");
            System.out.println("");
            try {
                //takes in player input
                BufferedReader br = new BufferedReader(input);
                String userIn = br.readLine();
                userIn = userIn.toLowerCase();
                if (userIn.equals("quit")) {
                    System.out.println("I guess you're too scared to challenge this dungeon. Farewell!");
                    //if player quits, code ends with goodbye message.
                    System.exit(0);
                } else {
                    // converts player input into integer
                    int mapNumber = Integer.parseInt(userIn);
                    // checks folder is not empty
                    assert listOfFiles != null;
                    // if player input for map is a number that does not represent a file, this message is printed
                    if (mapNumber > listOfFiles.length) {
                        System.out.println("You only have " + listOfFiles.length + " map files in your folder!");
                        pickMaps();
                    }
                    // if player input for map is 0, default map is picked
                    else if (mapNumber == 0) {
                        startGame();
                    }
                    //checks for which map the player wants
                    else {
                        for (int i = 1; i < 101; i++) {
                            if (mapNumber == i) {
                                // do something with this to pick map listOfFiles[i-1]
                                Path filePath = listOfFiles[i - 1].toPath();
                                String filePathString = Paths.get(String.valueOf(filePath)).toString();
                                //this block of code constructs the map within the file the player has chosen
                                map = new Map(filePathString);
                                //gets name
                                String mapName = map.getMapName();
                                //gets gold required
                                int goldRequired = map.getGoldRequired();
                                System.out.println("You picked " + mapName + " which requires " + goldRequired + " gold. Good luck 0.0");
                                startGame();
                            }
                        }
                    }
                }
            }
            // catch exception to make sure player inputs a number
            catch (NumberFormatException ignored) {
                System.out.println("Please use a number to pick your map!");
                System.out.println(" ");
                pickMaps();
            }
        }

        /**
         * Checks if the game is running
         *
         * @return if the game is running.
         */
        protected boolean isGameRunning() {
            return gameRunning;
        }

        /**
         * Returns the gold required to win.
         *
         * @return : Gold required to win.
         */
        protected String hello() {
            return "" + map.getGoldRequired();
        }

        /**
         * Returns the gold currently owned by the player.
         */
        protected void gold() {
            System.out.println(goldAmount);

        }

        /**
         * Checks if movement is legal and updates player's location on the map
         * and prints if successful or not.
         *
         * @param direction : The direction of the movement.
         */
        protected void move(char direction) {

            //player x coordinate
            int playerX = map.getPlayerPositionX();
            //player y coordinate
            int playerY = map.getPlayerPositionY();
            //map
            char[][] charMap = map.getMap();
            //gold1 x coordinate
            int gold1X = map.getGold1PositionX();
            //gold1 y coordinate
            int gold1Y = map.getGold1PositionY();
            //gold2 x coordinate
            int gold2X = map.getGold2PositionX();
            //gold2 y coordinate
            int gold2Y = map.getGold2PositionY();
            //gold3 x coordinate
            int gold3X = map.getGold3PositionX();
            //gold3 y coordinate
            int gold3Y = map.getGold3PositionY();
            //gold4 x coordinate
            int gold4X = map.getGold4PositionX();
            //gold4 y coordinate
            int gold4Y = map.getGold4PositionY();
            //gold5 x coordinate
            int gold5X = map.getGold5PositionX();
            //gold5 y coordinate
            int gold5Y = map.getGold5PositionY();
            //object which shows if gold1 tile is present on the map or not
            int gold1 = map.getGold1();
            //object which shows if gold2 tile is present on the map or not
            int gold2 = map.getGold2();
            //object which shows if gold3 tile is present on the map or not
            int gold3 = map.getGold3();
            //object which shows if gold4 tile is present on the map or not
            int gold4 = map.getGold4();
            //object which shows if gold5 tile is present on the map or not
            int gold5 = map.getGold5();
            //object which shows if exit1 tile is present on the map or not
            int exit1 = map.getExit1();
            //object which shows if exit2 tile is present on the map or not
            int exit2 = map.getExit2();
            //exit1 x coordinate
            int exit1X = map.getExit1PositionX();
            //exit1 y coordinate
            int exit1Y = map.getExit1PositionY();
            //exit2 x coordinate
            int exit2X = map.getExit2PositionX();
            //exit2 y coordinate
            int exit2Y = map.getExit2PositionY();

            //checks if player wants to move west direction.
            if (direction == 'w') {
                //checks if this move will place player in a wall.
                if (charMap[playerY][playerX - 1] == '#') {
                    System.out.println("FAIL");
                }
                //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
                //still visible on the map.
                else if (playerX == exit1X && playerY == exit1Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (exit1 == 1) {
                        map.resetExit1Tile();
                    }
                }
                //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
                //still visible on the map.
                else if (playerX == exit2X && playerY == exit2Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (exit2 == 1) {
                        map.resetExit2Tile();
                    }
                }
                //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold1X && playerY == gold1Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (gold1 == 1) {
                        map.resetGold1Tile();
                    }
                }
                //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerY == gold2X && playerY == gold2Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (gold2 == 1) {
                        map.resetGold2Tile();
                    }
                }
                //checks if player is currently on a gold3 tile. Ensures that if player moves off the gold3 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerY == gold3X && playerY == gold3Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (gold3 == 1) {
                        map.resetGold3Tile();
                    }
                }
                //checks if player is currently on a gold4 tile. Ensures that if player moves off the gold4 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerY == gold4X && playerY == gold4Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (gold4 == 1) {
                        map.resetGold4Tile();
                    }
                }
                //checks if player is currently on a gold5 tile. Ensures that if player moves off the gold5 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerY == gold5X && playerY == gold5Y) {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                    if (gold5 == 1) {
                        map.resetGold5Tile();
                    }
                } else {
                    map.adjustPlayerW(playerY, playerX - 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX - 1);
                    map.setPlayerPositionY(playerY);
                }

            }

            //checks if player wants to move north direction.
            else if (direction == 'n') {
                //checks if it will place player on wall
                if (charMap[playerY - 1][playerX] == '#') {
                    System.out.println("FAIL");
                }
                //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
                //still visible on the map.
                else if (playerX == exit1X && playerY == exit1Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (exit1 == 1) {
                        map.resetExit1Tile();
                    }
                }
                //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
                //still visible on the map.
                else if (playerX == exit2X && playerY == exit2Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (exit2 == 1) {
                        map.resetExit2Tile();
                    }
                }
                //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold1X && playerY == gold1Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (gold1 == 1) {
                        map.resetGold1Tile();
                    }
                }
                //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold2X && playerY == gold2Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (gold2 == 1) {
                        map.resetGold2Tile();
                    }
                }
                //checks if player is currently on a gold3 tile. Ensures that if player moves off the gold3 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold3X && playerY == gold3Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (gold3 == 1) {
                        map.resetGold3Tile();
                    }
                }
                //checks if player is currently on a gold4 tile. Ensures that if player moves off the gold4 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold4X && playerY == gold4Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (gold4 == 1) {
                        map.resetGold4Tile();
                    }
                }
                //checks if player is currently on a gold5 tile. Ensures that if player moves off the gold5 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold5X && playerY == gold5Y) {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                    if (gold5 == 1) {
                        map.resetGold5Tile();
                    }
                } else {
                    map.adjustPlayerN(playerY - 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY - 1);
                }
            } else if (direction == 'e') {
                if (charMap[playerY][playerX + 1] == '#') {
                    System.out.println("FAIL");
                }
                //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
                //still visible on the map.
                else if (playerX == exit1X && playerY == exit1Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (exit1 == 1) {
                        map.resetExit1Tile();
                    }
                }
                //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
                //still visible on the map.
                else if (playerX == exit2X && playerY == exit2Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (exit2 == 1) {
                        map.resetExit2Tile();
                    }
                }
                //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold1X && playerY == gold1Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (gold1 == 1) {
                        map.resetGold1Tile();
                    }
                }
                //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold2X && playerY == gold2Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (gold2 == 1) {
                        map.resetGold2Tile();
                    }
                }
                //checks if player is currently on a gold3 tile. Ensures that if player moves off the gold3 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold3X && playerY == gold3Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (gold3 == 1) {
                        map.resetGold3Tile();
                    }
                }
                //checks if player is currently on a gold4 tile. Ensures that if player moves off the gold4 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold4X && playerY == gold4Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (gold4 == 1) {
                        map.resetGold4Tile();
                    }
                }
                //checks if player is currently on a gold5 tile. Ensures that if player moves off the gold5 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold5X && playerY == gold5Y) {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                    if (gold5 == 1) {
                        map.resetGold5Tile();
                    }
                } else {
                    map.adjustPlayerE(playerY, playerX + 1);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX + 1);
                    map.setPlayerPositionY(playerY);
                }

            } else if (direction == 's') {
                if (charMap[playerY + 1][playerX] == '#') {
                    System.out.println("FAIL");
                }
                //checks if player is currently on a exit1 tile. Ensures that if player moves off the exit1 tile it is
                //still visible on the map.
                else if (playerX == exit1X && playerY == exit1Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (exit1 == 1) {
                        map.resetExit1Tile();
                    }
                }
                //checks if player is currently on a exit2 tile. Ensures that if player moves off the exit2 tile it is
                //still visible on the map.
                else if (playerX == exit2X && playerY == gold2Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (exit2 == 1) {
                        map.resetExit2Tile();
                    }
                }
                //checks if player is currently on a gold1 tile. Ensures that if player moves off the gold1 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold1X && playerY == gold1Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (gold1 == 1) {
                        map.resetGold1Tile();
                    }
                }
                //checks if player is currently on a gold2 tile. Ensures that if player moves off the gold2 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold2X && playerY == gold2Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (gold2 == 1) {
                        map.resetGold2Tile();
                    }
                }
                //checks if player is currently on a gold3 tile. Ensures that if player moves off the gold3 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold3X && playerY == gold3Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (gold3 == 1) {
                        map.resetGold3Tile();
                    }
                }
                //checks if player is currently on a gold4 tile. Ensures that if player moves off the gold4 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold4X && playerY == gold4Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (gold4 == 1) {
                        map.resetGold4Tile();
                    }
                }
                //checks if player is currently on a gold5 tile. Ensures that if player moves off the gold5 tile it is
                //still visible on the map assuming player hasn't used "pickup".
                else if (playerX == gold5X && playerY == gold5Y) {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                    if (gold5 == 1) {
                        map.resetGold5Tile();
                    }
                } else {
                    map.adjustPlayerS(playerY + 1, playerX);
                    System.out.println("SUCCESS");
                    map.setPlayerPositionX(playerX);
                    map.setPlayerPositionY(playerY + 1);
                }
            }
        }

        /**
         * Converts the map from a 2D char array to a single string.
         */
        protected void look() {
            //gets 5x5 map
            char[][] charMap = map.mapTo5x5Grid();
            //gets player position
            int playerX = map.getPlayerPositionX();
            int playerY = map.getPlayerPositionY();


            StringJoiner sj = new StringJoiner((System.lineSeparator()));
            //converts map to string
            for (char[] row : charMap) {
                sj.add(Arrays.toString(row));
            }
            String result = sj.toString();
            //prints string version
            System.out.println(result);
            System.out.println("");
        }

        /**
         * Processes the player's pickup command, updating the map and the player's gold amount.
         * prints out if the player successfully picked-up gold or not and how much gold they now have.
         */
        protected void pickup() {
            //gets current player positions
            int x = map.getPlayerPositionX();
            ;
            int y = map.getPlayerPositionY();

            //checks if player is on gold1 tile
            if (x == map.getGold1PositionX() && y == map.getGold1PositionY()) {
                //increases gold amount by 1
                goldAmount += 1;
                System.out.println("SUCCESS. Gold owned: " + goldAmount);
                //lets system know that gold1 shouldn't be represented on the map anymore
                map.setGold1(0);
            }
            //checks if player is on gold2 tile
            else if (x == map.getGold2PositionX() && y == map.getGold2PositionY()) {
                //increases gold amount by 1
                goldAmount += 1;
                System.out.println("SUCCESS. Gold owned: " + goldAmount);
                //lets system know that gold2 shouldn't be represented on the map anymore
                map.setGold2(0);
            }
            //checks if player is on gold3 tile
            else if (x == map.getGold3PositionX() && y == map.getGold3PositionY()) {
                //increases gold amount by 1
                goldAmount += 1;
                System.out.println("SUCCESS. Gold owned: " + goldAmount);
                //lets system know that gold3 shouldn't be represented on the map anymore
                map.setGold3(0);
            }
            //checks if player is on gold4 tile
            else if (x == map.getGold4PositionX() && y == map.getGold4PositionY()) {
                //increases gold amount by 1
                goldAmount += 1;
                System.out.println("SUCCESS. Gold owned: " + goldAmount);
                //lets system know that gold4 shouldn't be represented on the map anymore
                map.setGold4(0);
            }
            //checks if player is on gold5 tile
            else if (x == map.getGold5PositionX() && y == map.getGold5PositionY()) {
                //increases gold amount by 1
                goldAmount += 1;
                System.out.println("SUCCESS. Gold owned: " + goldAmount);
                //lets system know that gold5 shouldn't be represented on the map anymore
                map.setGold5(0);
            }
            //if none of the condition are meet code returns "FAIL" and amount of gold player has.
            else {
                System.out.println("FAIL. Gold owned: " + goldAmount);
            }
        }

        private void winningMessage() {
            System.out.println("WIN. Well done you beat " + map.getMapName() + " If you found this map easy try, another!!");
            System.out.println("");
            System.out.println("");
            System.out.println("Do you want to play again? type yes to play again, no to quit");
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(input);
            String userIn = null;
            try {
                userIn = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert userIn != null;
            userIn = userIn.toLowerCase();
            if (userIn.equals("yes")) {
                try {
                    playerMenu();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else if (userIn.equals("no")) {
                System.out.println("see you next time");
                gameRunning = false;
            }
        }


        /**
         * Quits the game, shutting down the application. to successfully quit player must have required gold and
         * be on either exit tile 1 or exit tile 2. If condition is not meet, code shut downs and player progress is
         * deleted.
         */
        protected void quitGame() {
            int playerX = map.getPlayerPositionX();
            int playerY = map.getPlayerPositionY();
            int exit1X = map.getExit1PositionX();
            int exit1Y = map.getExit1PositionY();
            int exit2X = map.getExit2PositionX();
            int exit2Y = map.getExit2PositionY();

            //checks if player has the right amount of gold to win
            if (goldAmount == map.getGoldRequired()) {
                //checks if player is on exit1 tile
                if (playerX == exit1X && playerY == exit1Y) {
                    winningMessage();
                }
                //checks if player is on exit2 tile
                else if (playerX == exit2X && playerY == exit2Y) {
                    winningMessage();
                }
                //prints "LOSE"
                else {
                    System.out.println("LOSE");
                }
            }
            //prints "LOSE"
            else {
                System.out.println("LOSE");
            }
            //sets game running to false, which stop code running in the main method.
            gameRunning = false;
        }

        protected void playerMenu() throws InterruptedException, IOException {
            introduction();
            pickMaps();
        }

        /**
         * Starts the game and is the only method directly called from the main.
         */
        protected void startGame() throws IOException, InterruptedException {

            // method initializes player start position
            map.playerStartPosition();
            //method initializes bot start position
            map.botStartPosition();
            // cancel game if too many or too little gold tiles are in map.
            if (map.howManyGold() > 5 || map.howManyGold() < 1) {
                System.out.println("Invalid amount of Gold in Map, Map must have 1-5 gold pieces!");
                quitGame();
            }
            if (map.howManyExit() > 5 || map.howManyExit() < 1) {
                System.out.println("Invalid amount of Exit in Map, Map must have 1-5 exit pieces!");
                quitGame();
            }
            else {
                while (isGameRunning()) {
                    //checks if bot has caught player.
                    if (map.getBotPositionX() == map.getPlayerPositionX() && map.getBotPositionY() == map.getPlayerPositionY()) {
                        System.out.println("The Bot caught you, unlucky :(");
                        //sets game running to false, which stop code running in the main method.
                        gameRunning = false;
                    } else {
                        //checks user input to see which command it is.
                        String in = humanPlayer.getInputFromConsole();
                        if (in.equals("Invalid")) {
                            System.out.println("Invalid, Use \"help\" for a list of available commands!");
                        } else if (in.equals("hello")) {
                            System.out.println(hello());
                        } else if (in.equals("look")) {
                            look();
                        } else if (in.equals("gold")) {
                            gold();
                        } else if (in.equals("pickup")) {
                            pickup();
                        } else if (in.equals("move n")) {
                            move('n');
                        } else if (in.equals("move s")) {
                            move('s');
                        } else if (in.equals("move e")) {
                            move('e');
                        } else if (in.equals("move w")) {
                            move('w');
                        }
                        else if (in.equals("help")) {
                            System.out.println("Hello - This will display the total amount of gold required to win.\n" +
                                    "Gold - This will display your current gold owned.\nMove <direction> - This will" +
                                    " move your tile in a direction E = East, W = West, N = North, S = South.\nLook -" +
                                    " This will display a 5x5 grid shwoing the map around your player.\nQuit - Leaves the game.");
                        } else if (in.equals("quit")) {
                            quitGame();
                        }
                        map.randomBotMove();
                    }
                }
            }
        }

        public static void main(String[] args) throws InterruptedException, IOException {

            GameLogic player1 = new GameLogic();
            // put player in the menu
            player1.playerMenu();
        }
    }

