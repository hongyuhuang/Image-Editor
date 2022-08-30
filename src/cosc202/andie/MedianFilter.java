package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

/**
 * <p>
 * Median Filter Function
 * </p>
 * 
 * <p>
 * The median filter is another blurring filter, but it is not implemented as a convolution. The median filter takes all of the pixel values
 * in a local neighbourhood and sorts them. The new pixel value is then the middle value (the median) from the sorted list.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Michael Young
 * @version 1.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int radius;
    private int numNeighbours;

    MedianFilter( int radius ) {
        this.radius = radius;
    }

    MedianFilter() {
        this(1);
    }

    
    /** 
     * A method which creates and returns a version of the original image with a median blur of data field radius applied.
     * Accounts for edge cases by scaling the image up by the radius and then returning it at the original size.
     * @param input
     * @return BufferedImage
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage borderedImage = new BufferedImage( input.getWidth() + (radius * 2), input.getHeight() + (radius * 2), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = borderedImage.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(input, 0, 0, borderedImage.getWidth(), borderedImage.getHeight(), null);
        g2D.drawImage(input, radius, radius, null);
        g2D.dispose();

        numNeighbours = (int)Math.pow((2.0*((double)this.radius)) + 1.0, 2.0);
        for (int y = radius; y < borderedImage.getHeight() - radius; ++y) {
            for (int x = radius; x < borderedImage.getWidth() - radius; ++x) {
                input.setRGB(x - radius, y - radius, getMedianPixel(getNeighbours( x, y, borderedImage)));
            }
        }
        
        return input;
    }

    
    /** 
     * A method which takes the location of a pixel and returns an int[] of the RGB values of all the neighbours within the radius
     * @param x x-axis location of target pixel
     * @param y y-axis location of target pixel
     * @param input Original BufferedImage
     * @return int[]
     */
    private int[] getNeighbours( int x, int y, BufferedImage input) {
        int[] neighbours = new int[numNeighbours];
        int counter = 0;
        for( int row = y - radius; row < (y - radius)+((radius*2)+1); row++ ) {
            for( int col = x - radius; col < (x - radius)+((radius*2)+1); col++ ) {
                    neighbours[counter] = input.getRGB(col, row);
                    counter++;
            }
        }

        return neighbours;
    }
    
    /** 
     * A method which finds the median RGB value of the RGB values stored in int[] neighbours
     * @param neighbours A int[] of RGB values of all the neighbours within the radius of a pixel
     * @return int The median RGB value of the pixels within int[] neighbours
     */
    private int getMedianPixel( int[] neighbours ) {
        int median = (neighbours.length)/2;
        ArrayList<Integer> aVals = new ArrayList<Integer>();
        ArrayList<Integer> rVals = new ArrayList<Integer>();
        ArrayList<Integer> gVals = new ArrayList<Integer>();
        ArrayList<Integer> bVals = new ArrayList<Integer>();
        for( int index = 0; index < neighbours.length; index++) {
            int argb = neighbours[index];
            aVals.add((argb & 0xFF000000) >> 24);
            rVals.add((argb & 0x00FF0000) >> 16);
            gVals.add((argb & 0x0000FF00) >> 8);
            bVals.add((argb & 0x000000FF));
        }
        Collections.sort( aVals );
        Collections.sort( rVals );
        Collections.sort( gVals );
        Collections.sort( bVals );
        int medianA = aVals.get(median);
        int medianR = rVals.get(median);
        int medianG = gVals.get(median);
        int medianB = bVals.get(median);
        int medianPixel = (medianA << 24) | (medianR << 16) | (medianG << 8) | medianB;
        return medianPixel;

    }
    
}
