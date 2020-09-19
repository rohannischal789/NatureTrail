
/**
 * Game is the class which represents the entire Nature Trail Game .
 * It consists of a public method to start the Nature Trail Game and other private methods to help with the functionality internally
 * @author Rohan
 * @version 1.2
 */
public class Game
{
    private int trailLength; // the maximum length of the trail
    private Trail natureTrail; // representing the trail of naturefeatures
    private Player playerOne; // representing the first player
    private Player playerTwo; // representing the computer

    /**
     * Constructor for objects of class Game
     * The fields are initialized and set to their default values
     */
    public Game()
    {
        trailLength = 0;
        natureTrail = new Trail();
        playerOne = new Player();
        playerTwo = new Player();
    }

    /**
     * Game Constructor
     *
     * @param newTrailLength A new length for the trail
     * @param newNatureTrail A new trail object
     * @param newPlayer1 A new player object
     * @param newPlayer2 A new player object
     */
    public Game(int newTrailLength, Trail newNatureTrail, Player newPlayer1, Player newPlayer2)
    {
        trailLength = newTrailLength;
        natureTrail = newNatureTrail;
        playerOne = newPlayer1;
        playerTwo = newPlayer2;
    }

    /**
     * Method startGame
     * This method is used to start the Nature Trail Game.
     * It prompts the user for inputs and validates them.
     * It does the first dice roll and comparison to determine who goes first.
     * Next, the nature features are randomly setup across the trial.
     * Now the players take turn to roll the dice until a player reaches the final position.
     * Lastly the scores are compared to determine the winner and this is printed on the screen
     */
    public void startGame()
    {
        displayGameHeader();
        Input console = new Input();
        trailLength = console.acceptIntegerInput("Enter the length of trail");
        while(!console.isValidInput(10, 20, trailLength))
        {
            // prompt again if length is not between 10 and 20 (inclusive)
            trailLength = console.acceptIntegerInput("Please enter the length of trail between 10 to 20");
        }
        String playerOneName = console.acceptStringInput("Enter the name of Player One");
        while(!console.isValidInput(1, 6, playerOneName.length()))
        {
            // prompt again if player name's length is not between 1 and 6 (inclusive)
            playerOneName = console.acceptStringInput("Enter the name of Player One between 1 and 6 letters");
        }
        //initalize both the playes
        playerOne = initializePlayer(playerOneName);
        playerTwo = initializePlayer("Computer");
        System.out.println("Welcome " + playerOne.getName() + "!! You are playing against the Mighty " + playerTwo.getName() + " today");
        String gameRules = "Here's how we decide who goes first : Each player would roll the dice once, Higher number would determine who starts first. All the best!!";
        System.out.println(gameRules);
        Dice fourSidedDice = new Dice (1,4);
        // initial dice roll to decide who goes first
        int initialPlayerOneDice = rollDice(fourSidedDice, playerOne);
        int initialPlayerTwoDice = rollDice(fourSidedDice, playerTwo);
        // compare the initial dice rolls
        compareDiceValues(initialPlayerOneDice, initialPlayerTwoDice, fourSidedDice);
        // show the trail progress on the screen
        showTrailProgress(trailLength, playerOne.getPosition(), playerTwo.getPosition());
        // setting up the random nature features across the trail
        natureTrail.setupValidFeatures(trailLength);
        // continue until 1 of the players crosses the finish line
        while(playerOne.getPosition() < trailLength && playerTwo.getPosition() < trailLength)
        {
            if(playerOne.getCurrentTurn())
            {
                playTurn(fourSidedDice, playerOne, playerTwo);
            }
            else if(playerTwo.getCurrentTurn())
            {
                playTurn(fourSidedDice, playerTwo, playerOne);
            }
        }

        // Compare scores after completing the trail Length. Show winner
        compareScores(playerOne, playerTwo, trailLength);
    }

    /**
     * Method initializePlayer
     * This method is used to initialize a new player object with the given name and return it
     * @param name name for the player
     * @return A player object with default values and name set
     */
    private Player initializePlayer(String name)
    {
        Player player = new Player();
        player.setName(name);
        return player;
    }

    /**
     * Method playTurn
     * This method is used to play each Player's turn. It would roll the dice and update the position of the user.
     * It would then check for animal sightings and nature features, if the player is not at the start or end of the trail.
     * It would also print the player's details and show both the player's trail progress.
     * Lastly, the turns for each user are updated
     * @param dice the dice object
     * @param currentPlayer the current player
     * @param nextPlayer the next player who's waiting for the turn to roll the dice
     */
    private void playTurn(Dice dice, Player currentPlayer, Player nextPlayer)
    {
        int initialPosition = 1;        
        int rolledValue = rollDice(dice, currentPlayer);
        // if rolled value + current position is greater than trail length, set new position to trail length
        currentPlayer.setPosition((currentPlayer.getPosition() + rolledValue) > trailLength ? trailLength : currentPlayer.getPosition() + rolledValue);
        if(currentPlayer.getPosition() != initialPosition && currentPlayer.getPosition() != trailLength)
        {
            // if current player position is not the start or finish position, check for animal sightings and nature features   
            checkForAnimalSighting(currentPlayer);
            checkForNatureFeatures(currentPlayer, trailLength);
        }
        displayPlayerDetails(currentPlayer);
        showTrailProgress(trailLength, playerOne.getPosition(), playerTwo.getPosition());
        // update current and next player's turns
        nextPlayer.setCurrentTurn(true);
        currentPlayer.setCurrentTurn(false);
    }

    /**
     * Method rollDice
     * This method would prompt the user to roll the dice. It would then print the rolled number on the screen and return the value
     * @param dice the dice object
     * @param player the current player
     * @return the value after rolling the dice
     */
    private int rollDice(Dice dice, Player player)
    {
        Input console = new Input();
        if(!player.getName().equalsIgnoreCase("Computer"))
        {
            // prompt user to enter only the enter key to roll the dice
            boolean validEnter = console.readEnterKey(player.getName() + " : Press enter to roll the 4 sided dice");
            while(!validEnter)
            {
                validEnter = console.readEnterKey(player.getName() + " : Press only the enter key to roll the 4 sided dice");
            }

        }
        showRollingGraphic(player.getName());
        int rolledValue = dice.generateRandomNumber();
        System.out.println(player.getName() + " has rolled a dice with number " + rolledValue);
        return rolledValue;
    }

    /**
     * Method showRollingGraphic
     * Displays the dice rolling graphic with the player's name on the screen
     * @param playerName the current player's name
     */
    private void showRollingGraphic(String playerName)
    {
        System.out.println("############" + playerName + " Rolling Dice#################");        
    }

    /**
     * Method compareDiceValues
     * This method compares the values of each player's dice and prints an appropriate message on the screen
     * If the two values are equal, the die are rolled again
     * @param playerOneDiceValue dice value rolled by the user
     * @param playerTwoDiceValue dice value rolled by the computer
     * @param dice the dice object
     */
    private void compareDiceValues(int playerOneDiceValue, int playerTwoDiceValue, Dice dice)
    {
        if(playerOneDiceValue == playerTwoDiceValue) // if both the dice values are equal
        {
            System.out.println("Tie Alert!! " + playerOne.getName() +" and " + playerTwo.getName() + " have rolled the same dice values!! We need to roll again");           
            // roll again in case of a tie
            int initialPlayerOneDice = rollDice(dice, playerOne);
            int initialPlayerTwoDice = rollDice(dice, playerTwo);
            compareDiceValues(initialPlayerOneDice, initialPlayerTwoDice, dice);
        }
        else if(playerOneDiceValue > playerTwoDiceValue) // if user has rolled the higher number
        {
            System.out.println("Congratulations!! " + playerOne.getName() + ",you have rolled the highest value and would go first");
            playerOne.setCurrentTurn(true);
            playerTwo.setCurrentTurn(false);
        }
        else // if computer has rolled the higher number
        {
            System.out.println("Unlucky!! " + playerTwo.getName() + " has rolled the highest value and would go first");
            playerTwo.setCurrentTurn(true);
            playerOne.setCurrentTurn(false);
        }
    }

    /**
     * Method updatePosition
     * This method prints the nature feature spotted at the current position. Also, updates the player's position according to the feature's space penalty
     * If the new Position is less than the trail start point, it would update the new position to the starting point.
     * If the new Position is greater than the trail length, it would update the new position to the finishing point.
     * @param player the current player
     * @param currentFeature the feature spotted at this position
     * @param trailLength the length of the trail
     */
    private void updatePosition(Player player,NatureFeature currentFeature, int trailLength)
    {
        int currentPenalty = currentFeature.getSpacePenalty();
        int initialPosition = 1;
        // string formation for printing according to whether penalty is positive or negative
        String stringStart = currentPenalty > 0 ? "WOW!! " :"OOPS!! ";
        String spaceMovement = currentPenalty > 0 ? "forwards" : "backwards";
        System.out.println(stringStart + player.getName() + " has spotted a " + currentFeature.getFeatureType() + 
                            " and moves " + spaceMovement + " by " + Math.abs(currentPenalty));
        int newPosition = 0;
        if(currentPenalty < initialPosition)
        {
            // if its a negative penalty
            newPosition = (player.getPosition() +  currentPenalty) >= initialPosition ? (player.getPosition() + currentPenalty) : initialPosition;
        }
        else
        {
            // if its a positive penalty
            newPosition = (player.getPosition() +  currentPenalty) <= trailLength ? (player.getPosition() + currentPenalty) : trailLength;
        }
        player.setPosition(newPosition);
    }

    /**
     * Method checkForNatureFeatures
     * checks if there are any nature features at the player's current position.
     * If yes, would update the position and print it on the screen
     * If not, would show the default message to indicate staying on the current position
     * @param player the current player
     * @param trailLength the length of the trail
     */
    private void checkForNatureFeatures(Player player, int trailLength)
    {
        // retrieve feature at current position, if any
        NatureFeature currentFeature = natureTrail.getFeature(player.getPosition());
        if(currentFeature != null)
        {
            updatePosition(player, currentFeature, trailLength);    
        }
        else
        {
            System.out.println(player.getName() + " stays at the current position as there are no nature features at this position");
        }        
    }

    /**
     * Method checkForAnimalSighting
     * Checks if an animal is spotted at the current position. 
     * If yes, would print the animal and points earned on the screen and would update the player's score.
     * If not, would print no animals spotted
     * @param player the current player
     */
    private void checkForAnimalSighting(Player player)
    {
        // check if an animal has been sighted at the current position
        String animalSpotted = checkIfAnimalSighted();
        if(!animalSpotted.isEmpty())
        {
            // if spotted
            String result = "Wow!! ";
            int pointsForAnimal = Integer.parseInt(animalSpotted.split(",")[1]);
            // split the values by a comma. 1st value is the animal name, 2nd is the points for spotting the animal
            if(pointsForAnimal < 0)
                result = "Oops!! ";
            System.out.println(result + player.getName() + ", You've spotted a " + animalSpotted.split(",")[0] + 
                                " at this position and get " + pointsForAnimal + " points");
            updateScore(player, pointsForAnimal);
        }
        else
        {
            System.out.println(player.getName() + ", No animals spotted at this position");
        }
    }

    /**
     * Method displayPlayerDetails
     * Prints the player's name, score and position on the screen
     * @param player the current player
     */
    private void displayPlayerDetails(Player player)
    {
        System.out.println("Updated Player Details - " + player.displayPlayer());
    }

    /**
     * Method updateScore
     * Used to update the score of the player upon sighting an animal
     * @param player represents the current player
     * @param newScore the new score of the current player
     */
    private void updateScore(Player player, int newScore)
    {
        player.setScore(player.getScore() + newScore);
        System.out.println(player.getName() + ", Your score has been updated to " + player.getScore());
    }

    /**
     * Method checkIfAnimalSighted
     * Checks if an animal is sighted with a 50% chance
     * @return The spotted Animal's name and points separated by a comma, if any, or else would return empty
     */
    private String checkIfAnimalSighted()
    {
        Dice dice = new Dice (1,10);
        int randomNo = dice.generateRandomNumber();
        String animalSpottedWithPoints = "";
        // 50% chance of sighting an animal
        switch(randomNo)
        {
            case 1: animalSpottedWithPoints = "Koala,5"; break;
            case 2: animalSpottedWithPoints = "Emu,7"; break;
            case 3: animalSpottedWithPoints = "Wombat,5"; break;
            case 4: animalSpottedWithPoints = "Kangaroo,2"; break;
            case 5: animalSpottedWithPoints = "Redback Spider,-5"; break;
            case 6: break;
            case 7: break;
            case 8: break;
            case 9: break;
            case 10: break;
            default : break;
        }
        return animalSpottedWithPoints;
    }

    /**
     * Method showTrailProgress
     * This method would print out the trail along with start, finish and the user and computer's current positions.
     * @param trailLength the length of the trail set by the user
     * @param playerOnePosition the user's position on the trail
     * @param playerTwoPosition the computer's position on the trail
     */
    private void showTrailProgress(int trailLength, int playerOnePosition, int playerTwoPosition)
    {
        System.out.println("\n##########Current Trail Progress##########");
        for(int i = 1; i <= trailLength; i++)
        {
            if(i == 1)
            {
                // print the start position of the trail
                System.out.print(" S");
            }
            if(i == playerOnePosition)
            {
                // print the user's position on the trail
                System.out.print(" H");
            }
            if(i == playerTwoPosition)
            {
                // print the computer's position on the trail
                System.out.print(" C");
            }
            else if(i != playerOnePosition && i != 1 && i != trailLength)
            {
                // if the position is empty and not the start and finish, print a trail character
                System.out.print(" _");
            }
            if(i == trailLength)
            {
                // print the finish position of the trail
                System.out.print(" F");
            }
        }
        displayBlankLine();
        displayLine();
        displayBlankLine();
    }

    /**
     * Method displayBlankLine
     * Displays a blank line on the screen
     */
    private void displayBlankLine()
    {
        System.out.println("");
    }

    /**
     * Method compareScores
     * This method is used to compare the scores of both the players once any of the player has finished crossing the trail.
     * It would add a score of 10 points for finishing first and would compare the scores of both the players and display the result.
     * @param player1 Corresponds to the user
     * @param player2 Corresponds to the computer
     * @param trailLength the length of the trail set by the user
     */
    private void compareScores(Player player1, Player player2, int trailLength)
    {
        // retrieve who finished first
        Player finishedFirst = player1.getPosition() >= trailLength ? player1 : player2;
        int finishingFirstPoints = 10;
        System.out.println(finishedFirst.getName() + 
                           " is the first player to cross the trail and gets 10 points for it. We'll now compare the scores to determine the winner!!");
        finishedFirst.setScore(finishedFirst.getScore() + finishingFirstPoints);
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();
        String matchGreeting = "#################YOU WIN##################";
        String highestScorer = "";
        String gameResult = " to win this game";
        int winningScore = 0;
        if(player1Score > player2Score) // if the user has won
        {
            highestScorer = player1.getName();
            winningScore = player1.getScore();
        }
        else if(player1Score == player2Score) // in case of a tie
        {
            highestScorer = player1.getName() + " and " + player2.getName() ;
            winningScore = player1.getScore();
            matchGreeting = "ITS A TIE!! ";
            matchGreeting = "################ITS A TIE#################";
            gameResult= " to Tie this game";
        }
        else // if the computer has won
        {
            highestScorer = player2.getName();
            winningScore = player2.getScore();
            matchGreeting = "#################YOU LOSE#################";
        }
        displayLine();
        System.out.println(matchGreeting);
        displayLine();
        System.out.println(highestScorer + " scored " + winningScore + " Points" + gameResult);
    }

    /**
     * Method displayLine
     * Display a line with Hash characters on the screen
     */
    private void displayLine()
    {
        System.out.println("##########################################");
    }

    /**
     * Method displayGameHeader
     * Displays the Game Header on the screen
     */
    private void displayGameHeader()
    {
        displayLine();
        System.out.println("#####Welcome to the Nature Trail Game#####");
        displayLine();
    }
}
