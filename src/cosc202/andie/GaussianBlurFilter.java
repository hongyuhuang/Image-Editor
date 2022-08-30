package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian Blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian filter blurs an image by putting each pixel through a 2-dimensional Gaussian
 * filter to obtain a kernel from pixels in a surrounding neighborhood and then summing these 
 * entries to divide the solutions.  This can be implemented by convolution.
 * </p>
 * 
 * <p>
 * The convolution is first applied to an scaled up image relative to the radius.
 * The orignal image is drawn in the centre. The convolution is then applied to this 
 * bordered image. This will account for the edge cases. A subimage of the bordered image
 * is returned which is the size of the original image.
 * <p>
 * 
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Hongyu Huang
 * @version 1.0
 */

public class GaussianBlurFilter implements ImageOperation, java.io.Serializable {
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

     /**
     * <p>
     * Construct a Gaussian Blur filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlurFilter
     */
    GaussianBlurFilter(int radius){
        this.radius = radius;
    }

     /**
     * <p>
     * Construct a Gaussian Blur filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see GaussianBlurFilter(int)
     */
    GaussianBlurFilter(){
        this(1);
    }

     /**
     * <p>
     * Apply a Gaussian Blur filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian Blur filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian Blur filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        /**
         * Return the image unaltered if the radius is 0.
         */
        if(radius == 0){
            return input;
        }

        /**
         * Set the size of the kernel to (2 * radius + 1)^2. 
         */
        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];
        
        /**
         * Set sigma to radius / 3.
         */
        double sigma = radius / 3; 
        
        /**
         * Loop through the kernel and getting the corresponding coordinates of each index to apply the 2-dimensional Gaussian
         * function to as well as keeping a total of the entries into the kernel. 
         */
        float total = 0;
        for(int index = 0; index < size; index++){
            int [] currentCoordinates = coords(index);
            
            int x = currentCoordinates[0];
            int y = currentCoordinates[1];
            
            double xSquared = Math.pow(x, 2);
            double ySquared = Math.pow(y, 2);
            double sigmaSquared =  Math.pow(sigma, 2);
            
            array[index] = (float)((1 /(2 * Math.PI * sigmaSquared)) * (Math.exp((-1) * (xSquared + ySquared)/(2 * sigmaSquared))));
            total += array[index]; 
        }

        /**
         * Loop through the kernel again but this time dividing each entry but the total entries. 
         */
        for(int index = 0; index < size; index++){
            array[index] = array[index] / total;
        }   

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        
        BufferedImage borderedImage = new BufferedImage( input.getWidth() + (2 * radius), input.getHeight() + (2 * radius), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = borderedImage.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(input, 0, 0, borderedImage.getWidth(), borderedImage.getHeight(), null);
        g2D.drawImage(input, radius, radius, null);
        g2D.dispose();

        BufferedImage offsetImage = new BufferedImage( input.getWidth() + (2 * radius), input.getHeight() + (2 * radius), BufferedImage.TYPE_INT_ARGB);
        
        convOp.filter(borderedImage, offsetImage); 
        
        BufferedImage output = offsetImage.getSubimage(radius, radius, input.getWidth() , input.getHeight());
        return output;
    }

    /**
    * Generates a row-column coordinate pair for an index with the index in the centre being (0,0).
    * 
    * @param index The index
    * @return A two-element array representing the coordinates of that index.
    */
    public int[] coords(int index) {
        int x = (index/(2*radius+1)) - radius;
        int y = (index % (2*radius+1))  - radius;
        return new int[] {x, y};
      }
}

