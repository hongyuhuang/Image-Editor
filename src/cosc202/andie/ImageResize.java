package cosc202.andie;

import java.awt.image.*;
import java.awt.*;


/**
 * ImageOperation to Resize an Image 
 * 
 * @author Paddy Borthwick
 * @version 1.0
 */

public class ImageResize implements ImageOperation, java.io.Serializable {

    private float x_scale;
    private float y_scale;

    /**
     * Create a new image resize operation
     * @param x_scale The factor we want to change the horizontal size by
     * @param y_scale The factor we want to change the vertical size by
     */
    ImageResize(float x_scale, float y_scale) {
        this.x_scale = x_scale;
        this.y_scale = y_scale;
    }
    /**
     * Create a new image resize operation with default scale values
     */
    ImageResize(){
        this.x_scale = 0;
        this.y_scale = 0;
    }

    /**
     * <p>
     * Apply the image resize
     * <p>
     * 
     * This method creates a new buffered image with the new size. It then creates a new graphics2d object that draw the new rescaled
     * image.
     * 
     * @param input The Buffered Image we want to change
     * @return The resized Buffered Image
     */
    public BufferedImage apply(BufferedImage input) {

        float temp1 = input.getWidth() * x_scale;
        int newW = (int) temp1;
        float temp2 = input.getHeight() * y_scale;
        int newH = (int) temp2;

        BufferedImage newImage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = newImage.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(input, 0, 0, newW, newH, null);
        g2D.dispose();
        return newImage;
    }
}


