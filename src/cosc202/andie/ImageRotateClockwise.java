package cosc202.andie;

import java.awt.image.*;

/**
 * ImageOperation to rotate an image 90 degrees anticlockwise
 * 
 * @author Michael Young
 * @version 1.0
 */

public class ImageRotateClockwise implements ImageOperation, java.io.Serializable {


    /**
     * <p>
     * Creates a new RotateClockwise operation.
     * </p>
     */
    ImageRotateClockwise() {

    }

    
    /** 
     * A method which creates and returns a version of the original image rotated 90 degrees clockwise
     * @param input
     * @return BufferedImage A copy of the input with a clockwise roation applied
     */
    public BufferedImage apply(BufferedImage input) {
       int newHeight = input.getWidth();
        int newWidth = input.getHeight();
        int type = input.getType();
        BufferedImage temp = new BufferedImage(newWidth, newHeight, type);
        for (int y = 0; y < newHeight; ++y) {
            for (int x = 0; x < newWidth; ++x) {
                int argb = input.getRGB(y, x);

                temp.setRGB(newWidth - 1 - x, y, argb);
            }
        }

        return temp;
    }
    
}