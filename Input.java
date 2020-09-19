import java.util.Scanner;

/**
 * Input is the class which uses the scanner class internally to read the user's typed input
 * It has methods to return String & Integer inputs typed by the user, to read the enter key and validate the user's input as well.
 * @author Rohan
 * @version 1.2
 */
public class Input
{
    /**
     * Constructor for objects of class Input
     */
    public Input()
    {
    }

    /**
     * Method acceptStringInput
     *
     * @param displayMessage This is the message that would be displayed to the user before prompting for an input
     * @return The String input typed by the user. Spaces are trimmed on each side of the input.
     */
    public String acceptStringInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        return console.nextLine().trim();
    }
    
    /**
     * Method acceptIntegerInput
     *
     * @param displayMessage This is the message that would be displayed to the user before prompting for an input
     * @return The integer input typed by the user. 
     * If a String input is provided instead, then it'll display 'Incorrect Input' message to the user and prompt again
     */
    public int acceptIntegerInput(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        // checks if the user has input a value of type
        while(!console.hasNextInt())
        {
            System.out.println("Incorrect Input!! " + displayMessage);
            //waits for the next input
            console.next();
        }
        return console.nextInt();
    }
    
    /**
     * Method readEnterKey
     *
     * @param displayMessage This is the message that would be displayed to the user before prompting for enter key
     * @return returns true if the only enter key has been pressed by the user, else false
     */
    public boolean readEnterKey(String displayMessage)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(displayMessage);
        // returns if the user has pressed only the enter key or not
        return console.nextLine().isEmpty();
    }
    
    /**
     * Method isValidInput
     *
     * @param minValue The minimum value to compare with
     * @param maxValue The maximum value to compare with
     * @param enteredLength The length to be compared
     * @return returns true if the length is between minimum and maximum value (inclusive)
     */
    public boolean isValidInput(int minValue, int maxValue, int enteredLength)
    {
        return (minValue <= enteredLength && enteredLength <= maxValue) ? true : false;
    }
}
