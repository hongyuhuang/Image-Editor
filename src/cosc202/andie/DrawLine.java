package cosc202.andie;
import javax.swing.JColorChooser;


import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * <p>
 *  Method to draw a line from the top left of a rectangle to the bottom right .
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

class DrawLine extends ImagePanel implements ImageOperation{
    /**
     * The array that holds the individual points used for drawing a line
     */
    int[] points;
   
    public DrawLine(){
       
    }
    

   /**
    * Calls GetPoints to set the points array.
    * Asks the user to select the colour that they want their line to be.
    * Draws a line onto the image and then returns it.
    */
    @Override
    public BufferedImage apply(BufferedImage input) {
        points = GetPoints.getPoints();
        Color newColour = JColorChooser.showDialog(null, "Choose a color", Color.RED);

        final Graphics2D g = (Graphics2D) input.getGraphics();
        g.setColor(newColour);
        g.drawLine(points[0],points[1],points[2],points[3]);
        g.dispose();
        ImagePanel.rect = null;
        return input;
    }
  
}