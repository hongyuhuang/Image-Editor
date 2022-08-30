package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * ImageOperation to crop an image depending on the mouse selection
 * 
 * @author Hongyu Huang
 * @version 1.0
 */

public class ImageCrop implements ImageOperation{ 

    Rectangle rect;
    
    /**
     * <p>
     * Create a new ImageCrop operation.
     * </p>
     * 
     * @param rect The recatangle that has been created by the mouse selection
     */
    public ImageCrop(Rectangle rect){
        this.rect = rect;
    }

    /**
     * <p>
     * Apply the ImageCrop action
     * <p>
     * 
     * This method takes the orginial image and creates a subimage taking the different
     * dimensions of the rectangle as parameters.
     * 
     * @param input The Buffered Image we want to change
     * @return The cropped Buffered Image
     */
    public BufferedImage apply(BufferedImage input) {    
        BufferedImage output = input.getSubimage(rect.x, rect.y, rect.width , rect.height);
        return output;
    }
}
