package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to apply a emboss filter to an image
 * </p>
 * 
 * <p>
 * The images chages the RGB values of each individual pixel by applying the selcted kernel.
 * </p>
 * 
 *
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */

public class EmbossFilter implements ImageOperation, java.io.Serializable {
    private String choice;

    /**
     * Create a new EmbossFilter operation with the user chosen direction
     * @param choice The chosen direction
     */
    EmbossFilter(){
        this.choice = "West";
    }

    /**
     * Creats a new EmbossFilter operation with a default direction
     */
    EmbossFilter(String choice){
        this.choice = choice;
    }

    /** 
     * <p>
     * Apply the Emboss Filter to the image
     * <p>
     * 
     * This method iterates through each pixel in the image and gets its neighbours.
     * It then multiplies each of the pixels r, g, and b values by a given value in a 
     * kernel.
     * 
     * @param input The Buffered Image we want to edit
     * @return The edited image
     */
    public BufferedImage apply(BufferedImage input) {
    
        //Get the right kernel for the desired emboss direction
        float [] array;
        if(choice == "West"){
            array = new float[]{0, 0, 0,
                1, 0, -1,
                0, 0, 0};
        }else if(choice == "North-West"){
            array = new float[]{1, 0, 0,
                0, 0, 0,
                0, 0, -1};
        }else if(choice == "North"){
            array = new float[]{0, 1, 0,
                0, 0, 0,
                0, -1, 0};
        }else if(choice == "North-East"){
            array = new float[]{0, 0, 1,
                0, 0, 0,
                -1, 0, 0};
        }else if(choice == "East"){
            array = new float[]{0, 0, 0,
                -1, 0, 1,
                0, 0, 0};
        }else if(choice == "South-East"){
            array = new float[]{-1, 0, 0,
                0, 0, 0,
                0, 0, 1};
        }else if(choice == "South"){
            array = new float[]{0, -1, 0,
                0, 0, 0,
                0, 1, 0};
        }else if(choice == "South-West"){
            array = new float[]{0, 0, -1,
                0, 0, 0,
                1, 0, 0};
        }else{
            return input;
        }

        //Make the output image to return
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        //Add a border around the input image
        BufferedImage borderedImage = new BufferedImage( input.getWidth() + 2, input.getHeight() + 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = borderedImage.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(input, 0, 0, borderedImage.getWidth(), borderedImage.getHeight(), null);
        g2D.drawImage(input, 1, 1, null);
        g2D.dispose();

        

        //The loops that applies the transformation
        for(int y = 1; y < output.getHeight()+1; y++){
            for(int x = 1; x < output.getWidth()+1; x++){
                
                //Creating the variables
                int [] neighbours = getNeighbours(x, y, borderedImage);
                int argb = borderedImage.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = 0;
                int g = 0;
                int b = 0;

                //Add the rgb values of the neighbours multiplied by the value of the kernel
                for(int i = 0; i <=8; i++){
                    int argbNeighbour = neighbours[i];
                    r += ((argbNeighbour & 0x00FF0000) >> 16) * array[i];
                    g += ((argbNeighbour & 0x0000FF00) >> 8) * array[i];
                    b += (argbNeighbour & 0x000000FF) * array[i];
                }

               

                //Normalises them
                r = (r+255)/2;
                g = (g+255)/2;
                b = (b+255)/2;

                //Sets the output pixel to the desired value
                argb = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x-1, y-1, argb);
            }
        }

       return output;
    }

    
    private int[] getNeighbours( int x, int y, BufferedImage input) {
        int[] neighbours = new int[9];
        int counter = 0;
        for( int row = y - 1; row <= y+1; row++ ) {
            for( int col = x - 1; col <= x+1; col++ ) {
                    neighbours[counter] = input.getRGB(col, row);
                    counter++;
            }
        }

        return neighbours;
    }
    
}
