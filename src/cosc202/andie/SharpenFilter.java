package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 * 
 * <p>
 * A Sharpen filter is the opposite of a blur and makes each pixel more like its neighbors it 
 * enhances the differences between neighboring values, and can be implemented by a convolution.
 * </p>
 * 
 * <p>
 * The convolution is first applied to an scaled up image relative to the size of the filter.
 * The orignal image is drawn in the centre. The convolution is then applied to this 
 * bordered image. This will account for the edge cases. A subimage of the bordered image
 * is returned which is the size of the original image.
 * <p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Hongyu Huang
 * @version 1.0
 */

public class SharpenFilter implements ImageOperation, java.io.Serializable {
    
    /** 
     * @param input
     * @return BufferedImage
     */
    public BufferedImage apply(BufferedImage input) {
        float [] array = new float[]{
            0.0f, -0.5f, 0.0f,
            -0.5f, 3.0f, -0.5f,
            0.0f, -0.5f, 0.0f
        };
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage borderedImage = new BufferedImage( input.getWidth() + 2, input.getHeight() + 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = borderedImage.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2D.drawImage(input, 0, 0, borderedImage.getWidth(), borderedImage.getHeight(), null);
        g2D.drawImage(input, 1, 1, null);
        g2D.dispose();

        BufferedImage offsetImage = new BufferedImage( input.getWidth() + 2, input.getHeight() + 2, BufferedImage.TYPE_INT_ARGB);
        
        convOp.filter(borderedImage, offsetImage); 
        
        BufferedImage output = offsetImage.getSubimage(1, 1, input.getWidth() , input.getHeight());
        return output;
    }
}