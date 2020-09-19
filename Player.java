/**
 * Player is the class which represents a Player in the Game.
 * It has accessor and mutator methods to return or update fields such as name, score, position and currentTurn.
 * It also has a method which returns the Player's current details on the trail.
 * @author Rohan
 * @version 1.2
 */
public class Player
{
    private String name; // Represents the name of the player
    private int position; // Represents the Player's position on the trail
    private int score; // Represents the score of the player
    private boolean hasCurrentTurn; // Boolean flag used to determine who rolls the dice next
    
    /**
     * Player Constructor
     * Sets the field values to the default values of a player in the game
     */
    public Player()
    {
        name = "";
        position = 1;
        score = 0;
        hasCurrentTurn = false;
    }
    
    /**
     * Player Constructor
     *
     * @param newName This can be used to set the player's name
     * @param newPosition This can be used to set the player's position
     * @param newScore This can be used to set the player's score
     * @param newHasCurrentTurn This can be used to update if a player plays next or not
     */
    public Player(String newName, int newPosition, int newScore, boolean newHasCurrentTurn)
    {
        name = newName;
        position = newPosition;
        score = newScore;
        hasCurrentTurn = newHasCurrentTurn;
    }

    /**
     * Method setName
     *
     * @param newName A player's new name for the game
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Method setPosition
     *
     * @param newPosition A player's new position on the trail
     */
    public void setPosition(int newPosition)
    {
        position = newPosition;
    }
    
    /**
     * Method setScore
     *
     * @param newScore A player's new score on the trail
     */
    public void setScore(int newScore)
    {
        score = newScore;
    }
    
    /**
     * Method getName
     *
     * @return The player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Method getScore
     *
     * @return The player's score
     */
    public int getScore()
    {
        return score;
    }
    
    /**
     * Method getPosition
     *
     * @return The player's position on the trail
     */
    public int getPosition()
    {
        return position;
    }
    
    /**
     * Method getCurrentTurn
     *
     * @return returns true if the player rolls the dice next, else false
     */
    public boolean getCurrentTurn()
    {
        return hasCurrentTurn;
    }
    
    /**
     * Method setCurrentTurn
     *
     * @param newHasCurrentTurn set if the player rolls the dice next or not
     */
    public void setCurrentTurn(boolean newHasCurrentTurn)
    {
         hasCurrentTurn = newHasCurrentTurn;
    }
    
    /**
     * Method displayPlayer
     *
     * @return The Player details currently on the trail
     */
    public String displayPlayer()
    {
        return "| Name : " + getName() + " | Score : " + Integer.toString(getScore()) + " | Position : " + Integer.toString(getPosition()) + " |";
    }
}
