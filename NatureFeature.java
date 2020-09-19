/**
 * NatureFeature is the class which represents a nature feature on the trail.
 * It has accessor and mutator methods to return and update fields such as the feature's position, feature type and the space penalty for coming across 
 * the feature.
 * @author Rohan
 * @version 1.2
 */
public class NatureFeature
{
    private int featurePosition; // represents a feature's position on the trail
    private String featureType; // represents a feature's type on the trail
    private int spacePenalty; // represents the number of spaces a player has to move forwards or backwards after landing on this nature feature

    /**
     * Constructor for objects of class NatureFeature
     * Sets the field values to the default values of the nature features needed in the game
     */
    public NatureFeature()
    {
        featurePosition = 0;
        featureType = "";
        spacePenalty = 0;
    }
    
    /**
     * NatureFeature Constructor
     *
     * @param newFeaturePosition A feature's new position on the trail
     * @param newFeatureType A feature's type on the trail
     * @param newSpacePenalty The space penalty for landing on this feature
     */
    public NatureFeature(int newFeaturePosition, String newFeatureType, int newSpacePenalty)
    {
        featurePosition = newFeaturePosition;
        featureType = newFeatureType;
        spacePenalty = newSpacePenalty;
    }

    /**
     * Method getFeaturePosition
     *
     * @return The nature feature's position
     */
    public int getFeaturePosition()
    {
        return featurePosition;
    }
    
    /**
     * Method getFeatureType
     *
     * @return The nature feature's type
     */
    public String getFeatureType()
    {
        return featureType;
    }
    
    /**
     * Method getSpacePenalty
     *
     * @return The penalty for landing on the nature feature
     */
    public int getSpacePenalty()
    {
        return spacePenalty;
    }
    
    /**
     * Method setFeaturePosition
     *
     * @param newFeaturePosition  A feature's new position on the trail
     */
    public void setFeaturePosition(int newFeaturePosition)
    {
        featurePosition = newFeaturePosition;
    }
    
    /**
     * Method setFeatureType
     *
     * @param newFeatureType  A feature's new type on the trail
     */
    public void setFeatureType(String newFeatureType)
    {
        featureType = newFeatureType;
    }
    
    /**
     * Method setSpacePenalty
     *
     * @param newSpacePenalty  A feature's new space penalty on the trail
     */
    public void setSpacePenalty(int newSpacePenalty)
    {
        spacePenalty = newSpacePenalty;
    }
}
