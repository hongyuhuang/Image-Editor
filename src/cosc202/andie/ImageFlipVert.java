package cosc202.andie;

import java.awt.image.*;

/**
 * ImageOperation to flip an image in the x axis
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */

public class ImageFlipVert implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new ImageFlip operation.
     * </p>
     */
    ImageFlipVert() {

    }

    /**
     * <p>
     * Apply the Vertical image flip
     * <p>
     * 
     * This method takes every pixel and sets it to the opposing pixel on a new Buffered Image
     * 
     * @param input The Buffered Image we want to change
     * @return The flipped Buffered Image
     */
    public BufferedImage apply(BufferedImage input) {
        int height = input.getHeight();
        int width = input.getWidth();
        int type = input.getType();
        BufferedImage temp = new BufferedImage(width, height, type);
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                int argb = input.getRGB(x, y);

                temp.setRGB(x, height - 1 - y, argb);
            }
        }

        return temp;
    }
}
