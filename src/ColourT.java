/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module for representing a colour.
*/ 

package src;

/**
 * @brief A module for representing a colour.
 */
public enum ColourT{
    R,
    G,
    B,
    P,
    O,
    W;
    
    /**
     * @brief Generate a random colour (except white)
     * @details Generate a random number and return the colour it represents.
     * @return a random ColourT (except W)
     */
    public static ColourT randCol(){
        double n = Math.random();
        int c = (int) (n * 5);
        
        switch(c){
            case 0:
                return ColourT.R;
            case 1:
                return ColourT.G;
            case 2:
                return ColourT.B;
            case 3:
                return ColourT.P;
        }
        return ColourT.O;
    }
}