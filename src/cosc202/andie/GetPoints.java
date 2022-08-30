package cosc202.andie;

import java.awt.*;
/**
 * <p>
 *  Method to get the point at the top left of a rectangle and the bottom right .
 * </p>
 * 
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Andrew Clarkson
 * @version 1.0
 */

public class GetPoints {
    Point point1;
    Point point2;
    static int[] points = new int[4];
    static int x;
    static int y;

  public GetPoints(){
       
    }

    public static int[] getPoints(){
        return points;
    }

    public static void setPoints(Point point){
        if(points[0] == 0 && points[1]==0){
            x = point.x;
            y = point.y;
            points[0] = point.x;
            points[1] = point.y;
        }else{
            points[2] = point.x;
            points[3] = point.y;
        }
    }
    public static void clear(){
        for(int i =0;i<points.length;i++){
            points[i] = 0;
        }
    }

    public static int getX(){
        return x;
    }

    public static int getY(){
        y += 10;
        return y;
    }
}


  