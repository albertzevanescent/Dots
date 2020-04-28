/**
 * Author: Albert Zhou zhouj103
 * Revised: 29/03/2020
 * Description: Module to control the game Dots.
*/ 

package src;

/**
 * @brief A module to control the game Dots.
 */
public class ControllerT{

    private ModelT m;
    private ViewT v;

    /**
     * @brief Create a controller
     * @details Set the model and view.
     * @param model the model
     * @param view the view
     */
    public ControllerT(ModelT model, ViewT view){
        this.m = model;
        this.v = view;
        
    }

    /**
     * @brief Determine if the player has lost
     * @details The player loses if they run out of moves
     * @return true if there are moves remaining, false otherwise
     */
    public boolean winnable(){
        return this.m.hasMoves();
    }

    /**
     * @brief Evaluate a path
     * @details If a path is not valid, return -1. If it is not a valid on the board,
     *          return -2. Otherwise return 0.
     * @param p the path
     * @param val the evaluation of the path
     */
    public int eval(PathT p){
        if (!p.valid())
            return -1;
        else if (!this.m.validMove(p))
            return -2;
        return 0;
    }

    /**
     * @brief Make a move
     * @details Make a move on the board
     * @param p the path
     */
    public void play(PathT p){
        this.m.makeMove(p);
    }

    /**
     * @brief Complete the level
     * @details Complete the level
     */
    public void completeLv(){
        this.m.completeLv();
    }

    /**
     * @brief Update the view
     * @details Update v
     */
    public void updateView(){
        v.disp(this.m.board(), this.m.level(), this.m.lv(), this.m.moves());
    }


}