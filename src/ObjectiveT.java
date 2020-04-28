/**
 * Author: Albert Zhou zhouj103
 * Revised: 28/03/2020
 * Description: Module for representing an objective.
*/ 

package src;

/**
 * @brief A module for representing an objective.
 */
public class ObjectiveT{
    
    private final ColourT col;
    private final int goal;
    private boolean complete;

    /**
     * @brief Create an objective
     * @details Set the colour, goal, and completion.
     * @param c the target colour
     * @param g the target count
     * @throws IllegalArgumentException if g is less than 2
     */
    public ObjectiveT(ColourT c, int g){
        if (g < 2)
            throw new IllegalArgumentException("Cannot have goal less than 2");

        this.col = c;
        this.goal = g;
        this.complete = false;
    }

    /**
     * @brief Get col
     * @details Returns the col value.
     * @return col the colour
     */
    public ColourT col(){
        return this.col;
    }

    /**
     * @brief Get goal
     * @details Returns the goal value.
     * @return goal the target count
     */
    public int goal(){
        return this.goal;
    }

    /**
     * @brief Get complete
     * @details Returns the complete value.
     * @return complte the completion
     */
    public boolean complete(){
        return this.complete;
    }

    /**
     * @brief Attempt to complete a goal
     * @details If the colours match and the goal is reached the objective is complete
     * @param c the colour of a path
     * @param x the count of a path
     */
    public void attempt(ColourT c, int x){
        if (!this.complete)
            this.complete = c == this.col && x >= this.goal;
    }

}