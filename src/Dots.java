/**
 * Author: Albert Zhou zhouj103
 * Revised: 30/03/2020
 * Description: Play Dots.
*/ 

import src.PointT;
import src.LineT;
import src.PathT;
import src.ObjectiveT;
import src.LevelT;
import src.BoardT;
import src.ModelT;
import src.ViewT;
import src.ControllerT;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

/**
 * @brief A module to play Dots.
 */
public class Dots{

    private static ControllerT c;

    public static void init(){

        c = new ControllerT(new ModelT(6, 5), new ViewT());

        // ArrayList<ColourT> r1;
        // ArrayList<ColourT> r2;
        // ArrayList<ColourT> r3;
        // ArrayList<ArrayList<ColourT>> cols;
        // BoardT b;
        // LineT l1;
        // LineT l2;
        // PathT p;
        // LevelT lv1;
        // LevelT lv2;
        // ModelT m;

        // r1 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.B, ColourT.B));
		// r2 = new ArrayList<ColourT>(Arrays.asList(ColourT.G, ColourT.G, ColourT.B));
		// r3 = new ArrayList<ColourT>(Arrays.asList(ColourT.R, ColourT.R, ColourT.B));
		// cols = new ArrayList<ArrayList<ColourT>>();
		// cols.add(r3);
		// cols.add(r2);
		// cols.add(r1);
		// b = new BoardT(cols);
		// l1 = new LineT(new PointT(0, 2), new PointT(2, 2));
		// l2 = new LineT(new PointT(2, 2), new PointT(2, 1));
        // p = new PathT();
        // p.add(l1);
        // p.add(l2);
        // lv1 = new LevelT(new ArrayList<ObjectiveT>(Arrays.asList(new ObjectiveT(ColourT.B, 3), new ObjectiveT(ColourT.R, 2))), 5);
        // lv2 = new LevelT(new ArrayList<ObjectiveT>(Arrays.asList(new ObjectiveT(ColourT.R, 2))), 10);
        // //m = new ModelT(b, new ArrayList<LevelT>(Arrays.asList(lv1, lv2)));
        // m = new ModelT(10, 1);
        // ViewT v = new ViewT();
    }

    public static PathT parsePath(String s){
        PathT p = new PathT();

        String[] ss = s.split(" ");
        ArrayList<PointT> points = new ArrayList<PointT>();
        int row, col;
        for(int i = 0; i < ss.length; i++){
            row = Integer.parseInt(ss[i].substring(0, ss[i].indexOf(",")));
            col = Integer.parseInt(ss[i].substring(ss[i].indexOf(",") + 1));
            points.add(new PointT(row, col));
        }
        for(int i = 0; i < points.size()-1; i++)
            p.add(new LineT(points.get(i), points.get(i+1)));

        return p;

    }

    public static void main(String[] args){
        init();
        Scanner in = new Scanner(System.in);
        PathT p = new PathT();
        String s;

        System.out.println("Enter the path in the form \"a,b c,d ...\" where each pair is a point " +
                            "in row,col form. Ensure that every pair of points construct a valid " +
                            "line. Enter anything to continue.");
        in.nextLine();

        c.updateView();
        while(c.winnable()){
            s = in.nextLine();
            p = parsePath(s);

            switch(c.eval(p)){
                case 0:
                c.play(p);
                c.completeLv();
                c.updateView();
                break;

                case -1:
                System.out.println("That path is invalid");
                break;

                case -2:
                System.out.println("That path is invalid on the board");
                break;
            }

        }
    }
}