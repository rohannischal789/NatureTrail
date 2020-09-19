/**
 * Dice is the class which represents the Dice in the Game. Rolling the dice would lead to player movements across the trail.
 * It has accessor and mutator methods to return or update the minimum and maximum number for the dice.
 * It also has a method to generate a random number between 2 values (inclusive)
 * @author Rohan
 * @version 1.2
 */
public class Dice
{
    private int maximumValue; // the maximum value can be rolled on the dice
    private int minimumValue; // the minimum value can be rolled on the dice

    /**
     * Dice Constructor
     * Sets the maximum and minimum Values to that of a four sided dice
     */
    public Dice()
    {
        minimumValue = 1;
        maximumValue = 4;
    }

    /**
     * Dice Constructor
     *
     * @param newMinimumValue the minimum value can be generated
     * @param newMaximumValue the maximum value can be generated
     */
    public Dice(int newMinimumValue, int newMaximumValue)
    {
        minimumValue = newMinimumValue;
        maximumValue = newMaximumValue;
    }

    /**
     * Method generateRandomNumber
     *
     * @return a random number between the minimum and maximum values (inclusive)
     */
    public int generateRandomNumber()
    {
        return minimumValue + (int)(Math.random() * maximumValue);
    }

    /**
     * Method setMinimumValue
     *
     * @param newMinimumValue the minimum value that can be generated
     */
    public void setMinimumValue(int newMinimumValue)
    {
        minimumValue = newMinimumValue;
    }

    /**
     * Method setMaximumValue
     *
     * @param newMaximumValue the maximum value that can be generated
     */
    public void setMaximumValue(int newMaximumValue)
    {
        maximumValue = newMaximumValue;
    }

    /**
     * Method getMinimumValue
     *
     * @return the minimum value of the dice
     */
    public int getMinimumValue()
    {
        return minimumValue;
    }

    /**
     * Method getMaximumValue
     *
     * @return the maximum value of the dice
     */
    public int getMaximumValue()
    {
        return maximumValue;
    }
}
