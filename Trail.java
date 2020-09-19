/**
 * Trail is the class which represents the Trail in the Game. Trail consists of Nature Features on which a player might land.
 * It has accessor and mutator methods to return or update a feature or many features.
 * @author Rohan
 * @version 1.2
 */
public class Trail
{
    private NatureFeature[] features; // Array to represent the Nature features on the trail

    /**
     * Constructor for objects of class Trail
     * It initializes the Nature features array to length of 4
     */
    public Trail()
    {
        features = new NatureFeature[4];
    }

    /**
     * Method getFeatures
     *
     * @return The array consisting of all the Nature Features
     */
    public NatureFeature[] getFeatures()
    {
        return features;
    }

    /**
     * Method getFeature
     *
     * @param featurePosition A position on the trail
     * @return The nature feature, if any, at this position on the trail
     */
    public NatureFeature getFeature(int featurePosition)
    {
        for(int i = 0; i < features.length; i++)
        {
            if(features[i].getFeaturePosition() == featurePosition)
                return features[i];
        }        
        return null;
    }

    /**
     * Method setupValidFeatures
     * This method is used to setup the various nature features randomly across the trail.
     * No 2 features can be placed on the same position.
     * @param trailLength the length of the trail
     */
    public void setupValidFeatures(int trailLength)
    {
        // new dice object start from 1 to trail length - 2
        Dice fourSidedDice = new Dice (1, trailLength - 2);
        // Adding 1 to the random number generated as nature features cannot be found on start and finish positions
        features[0] = new NatureFeature(fourSidedDice.generateRandomNumber() + 1, "Creek", -2);
        features[1] = new NatureFeature(fourSidedDice.generateRandomNumber() + 1, "Bridge", 4);
        features[2] = new NatureFeature(fourSidedDice.generateRandomNumber() + 1, "Fallen Tree", -3);
        features[3] = new NatureFeature(fourSidedDice.generateRandomNumber() + 1, "Landslide", -5);

        for(int i = 0; i < features.length; i++)
        {
            NatureFeature currentFeature = features[i];
            for(int j = 0; j < features.length; j++)
            {
                if(i != j) // do not compare the same features
                {
                    NatureFeature comparableFeature = features[j];
                    while(currentFeature.getFeaturePosition() == comparableFeature.getFeaturePosition())
                    {
                        // update feature position if a nature feature already exists on the same position
                        currentFeature.setFeaturePosition(fourSidedDice.generateRandomNumber() + 1);
                    }
                }
            }
        }
    }

    /**
     * Method setFeatures
     *
     * @param newFeatures A new array of Nature Features
     */
    public void setFeatures(NatureFeature[] newFeatures)
    {
        features = newFeatures;
    }

    /**
     * Method setFeature
     *
     * @param index The index of the array
     * @param newFeaturePosition The position of the feature on the trail
     * @param newFeatureType The type of the feature on the trail
     * @param newSpacePenalty The space penalty for landing on this feature on the trail
     */
    public void setFeature(int index, int newFeaturePosition, String newFeatureType, int newSpacePenalty)
    {
        int arrayLength = features.length;
        // check if index is within the array or not
        NatureFeature changeableFeature = index < arrayLength ? features[index] : new NatureFeature();
        changeableFeature.setFeaturePosition(newFeaturePosition);
        changeableFeature.setFeatureType(newFeatureType);
        changeableFeature.setSpacePenalty(newSpacePenalty);
    }
}
