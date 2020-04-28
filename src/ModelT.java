/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Module to model the game Dots.
*/ 

package src;

import java.util.ArrayList;

/**
 * @brief A module to model the game Dots.
 */
public class ModelT{
    
    private BoardT b;
    private ArrayList<LevelT> levels;
    private int lv;
    private int moves;

    /**
     * @brief Create a model
     * @details Create a random board, random levels, and initialize the level and move counter.
     * @param d the dimension of the board
     * @param l the number of levels
     * @throws IllegalArgumentException if d <= 0
     */
    public ModelT(int d, int l){
        if (d <= 0)
            throw new IllegalArgumentException("Cannot create a board with dimension <= 0");
        else if (l <= 0)
            throw new IllegalArgumentException("The number of levels must be positve");

        this.b = this.makeBoard(d);
        this.levels = this.makeLevels(l);
        this.lv = 0;
        this.moves = 0;
    }

    /**
     * @brief Create a model
     * @details Set the board and levels and initialize the level and move counter.
     * @param board the board of the model
     * @param l the levels of the model
     */
    public ModelT(BoardT board, ArrayList<LevelT> l){
        this.b = board;
        this.levels = l;
        this.lv = 0;
        this.moves = 0;
    }

    /**
     * @brief Get b
     * @details Returns the board.
     * @return b the board
     */
    public BoardT board(){
        return this.b;
    }

    /**
     * @brief Get levels[lv]
     * @details Returns the current level.
     * @return level[lv] the current level
     */
    public LevelT level(){
        return this.levels.get(this.lv);
    }

    /**
     * @brief Get lv
     * @details Returns the level counter
     * @return lv the level counter
     */
    public int lv(){
        return this.lv;
    }

    /**
     * @brief Get the remaining number of moves
     * @details Returns the difference between the maximum moves of the level and the
     *          current number of moves.
     * @return the remaining number of moves
     */
    public int moves(){
        return this.levels.get(this.lv).moves() - this.moves;
    }

    /**
     * @brief Determine if there are moves remaining
     * @details Check if the number of moves remaining is positive.
     * @return true if there are moves remaining, false otherwise
     */
    public boolean hasMoves(){
        return this.moves() > 0;
    }

    /**
     * @brief Determine if a move is valid
     * @details Check if the path is valid in the board
     * @return true if the move is valid, false otherwise
     * @throws IllegalArgumentException if p is an invalid path
     */
    public boolean validMove(PathT p){
        if (!p.valid())
            throw new IllegalArgumentException("The given path is invalid");

        return this.b.validPath(p);
    }

    /**
     * @brief Perform a move
     * @details Remove the path from the board, attempt the objectives of the level,
     *          increment the move counter.
     * @throws IllegalArgumentException if p is an invalid path or an invalid move
     */
    public void makeMove(PathT p){
        if (!this.validMove(p))
            throw new IllegalArgumentException("The given path is invalid on the board");

        this.levels.get(this.lv).attempt(this.b.pathCol(p), p.mag());
        this.b.rmPath(p);
        this.moves++;
    }

    /**
     * @brief Complete the level
     * @details If the level is complete, move to the next and reset the moves counter.
     *          If this is the last level do nothing.
     */
    public void completeLv(){
        if (this.levels.get(this.lv).complete(this.moves) && this.lv < this.levels.size() - 1){
            this.lv++;
            this.moves = 0;
        }
    }

    /**
     * @brief Generate a random number between 0 and i
     * @details Generate a random number between 0 and i.
     * @param i the maximum value
     * @return a random number between 0 and i
     */
    private int randNum(int i){
        double n = Math.random();
        return (int) (n * i);
    }

    /**
     * @brief Create a sequence of random colours
     * @details Create an ArrayList of d random colours
     * @param d the number of colours
     * @return a sequence of random colours
     */
    private ArrayList<ColourT> makeRow(int d){
        ArrayList<ColourT> row = new ArrayList<ColourT>();
        for(int i = 0; i < d; i++)
            row.add(ColourT.randCol());
        return row;
    }

    /**
     * @brief Create a random board
     * @details Create a board from random rows
     * @param d the dimension of the board
     * @return a random board
     */
    private BoardT makeBoard(int d){
        ArrayList<ArrayList<ColourT>> board = new ArrayList<ArrayList<ColourT>>();
        for(int i = 0; i < d; i++)
            board.add(this.makeRow(d));
        return new BoardT(board);
    }

    /**
     * @brief Create a random objective
     * @details Create an objective with a random colour and a given goal.
     * @param i the goal
     * @return a random objective
     */
    private ObjectiveT makeObj(int i){
        return new ObjectiveT(ColourT.randCol(), i);
    }

    /**
     * @brief Create a random level
     * @details Create an level with a random objective and a given maximum number of moves.
     * @param i the maximum number of mvoes
     * @return a random level
     */
    private LevelT makeLv(int i){
        ArrayList<ObjectiveT> objs = new ArrayList<ObjectiveT>();
        objs.add(this.makeObj(i));
        return new LevelT(objs, 10);
    }

    /**
     * @brief Create a random sequence of levels
     * @details Create i levels with random goals.
     * @param i the number of levels
     * @return a random sequence of levels
     */
    private ArrayList<LevelT> makeLevels(int l){
        ArrayList<LevelT> levels = new ArrayList<LevelT>();
        for(int i = 0; i < l; i++)
            levels.add(this.makeLv(3 + this.randNum(i)));
        return levels;
    }

}