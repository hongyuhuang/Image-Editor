package cosc202.andie;

import java.awt.image.*;
import java.awt.*;

/**
 * Text-Operation to draw text on an image.
 * 
 * @author Michael Young
 * @version 1.0
 */

public class DrawText implements ImageOperation, java.io.Serializable {

    ImagePanel target;

    /**
     * <p>
     * Creates a new DrawText operation.
     * </p>
     */
    DrawText(ImagePanel target) {
        this.target = target;
    }

    
    /** 
     * A method which applies text to the image.
     * @param input
     * @return BufferedImage A copy of the input with the user's message applied.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage image = input;
        Graphics g = image.getGraphics();
        TextBoxPanel frame = new TextBoxPanel(g);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);                        
        return image;
    }
}