package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to change the level of contrast of an image
 * </p>
 * 
 * <p>
 * The images chages the RGB values of each individual pixel based on their relation to the average of every possibel RBG value.
 * </p>
 * 
 *
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */

public class ContrastAdjustment implements ImageOperation, java.io.Serializable {

    private double contrastFactor;

     /**
     * <p>
     * Create a new ContrastAdjustment operation with the user inputed contrast.
     * </p>
     */
    ContrastAdjustment(double contrastFactor) {
        this.contrastFactor = contrastFactor;
    }
    /**
     * <p>
     * Create a new ContrastAdjustment operation with default contrast.
     * </p>
     */

    ContrastAdjustment() {
        this(1);
    }

    /** 
     * <p>
     * Apply the contrast change to the image
     * <p>
     * 
     * This method iterates through each pixel in the image and gets the rgb values for it.
     * It then applys the operation to change the contrast and sets that to the new rgb value aslong as they are between 0 ans 255.
     * 
     * @param input The Buffered Image we want to edit
     * @return The edited image
     */
    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                double r = (argb & 0x00FF0000) >> 16;
                double g = (argb & 0x0000FF00) >> 8;
                double b = (argb & 0x000000FF);

                int r2 = (int) r;
                int g2 = (int) g;
                int b2 = (int) b;
                
                r = (1 + contrastFactor/100)*(r-127.5)+127.5;
                g = (1 + contrastFactor/100)*(g-127.5)+127.5;
                b = (1 + contrastFactor/100)*(b-127.5)+127.5;
                
                

                r2 = (int)Math.max(r, 0);
                r2 = Math.min(r2, 255);
                g2 = (int)Math.max(g, 0);
                g2 = Math.min(g2, 255);
                b2 = (int)Math.max(b, 0);
                b2 = Math.min(b2, 255);

                int argc =  (a << 24) | (r2 << 16) | (g2 << 8) | b2;
                
                input.setRGB(x, y, argc);

            }
        }
        return input;

    }

}
 