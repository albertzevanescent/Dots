/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module to present the view of the game Dots.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module to present the view of the game Dots.
 */
public class ViewT{

    /**
     * @brief Create a view
     * @details Create a view.
     */
    public ViewT(){
        ;
    }

    /**
     * @brief Display the components of the game Dots
     * @details Modify window so that "Level: " followed by the level number at the top of the
     *          screen. Below, the objectives are written from left to right, 1 by 1, as the
     *          goal followed by the colour if they have not been completed. If all of the
     *          objectives are complete print "You win" and nothing else. Below, the board
     *          should by displayed starting at the top row. The names of the colours should be
     *          printed in place of the colour they represent. At the bottom, the number of moves
     *          remaining should be printed.
     * @param b the board of the model
     * @param level the level of the model
     * @param lv the level number
     * @param moves the number of moves remaining
     */
    public void disp(BoardT b, LevelT l, int lv, int moves){
        System.out.println("Level: " + (lv + 1));

        ArrayList<ObjectiveT> objs = l.objectives();

        boolean win = true;

        for(ObjectiveT o : objs){
            if (!o.complete()){
                win = false;
                System.out.print(o.col() + " - " + o.goal() + "   ");
            }
        }

        if (win){
            System.out.print("You Win!");
            return;
        }

        System.out.println();

        ArrayList<ArrayList<ColourT>> board = b.board();

        for(int i = board.get(0).size() - 1; i >= 0; i--){
            for(int j = 0; j < board.size(); j++)
                System.out.print(board.get(i).get(j) + " ");
            System.out.println();
        }

        System.out.println(moves + " moves remaining!");
    }


}