import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

    /**
     * Runs the game with a human player and contains code needed to read inputs.
     *
     */
    public class HumanPlayer {



        /**
         * Reads player's input from the console.
         * <p>
         * return : A string containing the input the player entered.
         * @return null
         */
        protected String getInputFromConsole() {
            //input reader
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(input);
            try {
                String userIn = br.readLine();
                return getNextAction(userIn) ;
            }
            catch (IOException ignored) {

            }
            return null;
        }

        /**
         * Processes the command. It should return a reply in form of a String, as the protocol dictates.
         * Otherwise it should return the string "Invalid".
         * @param action get next action.
         * @return : Processed output or Invalid if the @param command is wrong.
         */
        protected String getNextAction(String action) {
            // put input into lower case, making player input not case sensitive.
            action = action.toLowerCase();
            // makes sure player enters valid word
            if(action.equals("move w") || action.equals("move n") || action.equals("move s")
                    || action.equals("move e") || action.equals("look") || action.equals("hello")
                    || action.equals("gold") || action.equals("pickup") || action.equals("quit") || action.equals("help"))
            return action;

            else {
                return "Invalid";
            }
        }
    }

