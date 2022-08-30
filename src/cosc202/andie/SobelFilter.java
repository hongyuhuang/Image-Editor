package cosc202.andie;
import java.awt.image.*;
import java.awt.*;


/**
 * <p>
 * ImageOperation to apply a sobel filter to an image
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
public class SobelFilter implements ImageOperation, java.io.Serializable {
    private String choice;

    /**
     * Create a new SobelFilter operation with the user chosen direction
     * @param choice The chosen direction
     */
    SobelFilter(String choice){
        this.choice = choice;
    }

    /**
     * Creats a new SobelFilter operation with a default direction
     */
    SobelFilter(){
        this.choice = "Right Sobel";
    }

    /** 
     * <p>
     * Apply the Sobel Filter to the image
     * <p>
     * 
     * This method iterates through each pixel in the image and gets its neighbours.
     * It then multiplies each of the pixels r, g, and b values by a given value in a 
     * kernel.
     * 
     * @param input The Buffered Image we want to edit
     * @return The edited image
     */
    public BufferedImage apply(BufferedImage input){

        float[] array;
        if(choice == "Right Sobel"){
            array = new float[]{-0.5f, 0, 0.5f,
                                -1,    0, 1,
                                -0.5f, 0, 0.5f};
        }else{
            array = new float[]{-0.5f, -1, -0.5f,
                                 0,     0,  0,
                                 0.5f,  1,  0.5f};
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
                float r = 0;
                float g = 0;
                float b = 0;

                //Add the rgb values of the neighbours multiplied by the value of the kernel
                for(int i = 0; i <=8; i++){
                    int argbNeighbour = neighbours[i];
                    r += ((argbNeighbour & 0x00FF0000) >> 16) * array[i];
                    g += ((argbNeighbour & 0x0000FF00) >> 8) * array[i];
                    b += (argbNeighbour & 0x000000FF) * array[i];
                }

               

                //Normalises them
                int r1 = (int)(r+255)/2;
                int g1 = (int)(g+255)/2;
                int b1 = (int)(b+255)/2;

                //Sets the output pixel to the desired value
                argb = (a << 24) | (r1 << 16) | (g1 << 8) | b1;
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
