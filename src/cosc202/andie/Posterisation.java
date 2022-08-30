package cosc202.andie;

import java.awt.image.*;


/**
 * <p>
 * ImageOperation to apply a posterizastion to an image
 * </p>
 * 
 * <p>
 * The images chages the RGB values of each individual pixel by rounding them to set values or 
 * setting them to zero if they were unwanted
 * </p>
 * 
 *
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */
public class Posterisation implements ImageOperation, java.io.Serializable {
    
    Boolean red;
    Boolean green;
    Boolean blue;
    
    
    /**
     * Creates a new Posterisation operation with the user chosen r, g, and b channels
     * @param red
     * @param green
     * @param blue
     */
    Posterisation(Boolean red, Boolean green, Boolean blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    /**
     * Creates a new Posterisation action with default options
     */
    Posterisation(){
        this.red = true;
        this.green = true;
        this.blue = true;
    }

    /** 
     * <p>
     * Apply the Posterisation to the image
     * <p>
     * 
     * This method iterates through each pixel in the image and gets its rgb values.
     * It rounds them to either 0, 128, or 255 or just sets them to zero if that channel
     * is chosen to be off.
     * 
     * @param input The Buffered Image we want to edit
     * @return The edited image
     */
    public BufferedImage apply(BufferedImage input){
        for(int h = 0; h < input.getHeight(); h++){
            for(int w = 0; w < input.getWidth(); w++){
                int argb = input.getRGB(w, h);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                if(r < 64 || !red){
                    r = 0;
                }else if(r < 192){
                    r = 128;
                }else{
                    r = 255;
                }

                if(g < 64 || !green){
                    g = 0;
                }else if(g < 192){
                    g = 128;
                }else{
                    g = 255;
                }

                if(b < 64 || !blue){
                    b = 0;
                }else if(b < 192){
                    b = 128;
                }else{
                    b = 255;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(w, h, argb);
            }
        }

        return input;
    }
}