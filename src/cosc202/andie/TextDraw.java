package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * <p>
 *  Method to draw text on an image.
 * </p>
 * 
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Andrew Clarkson
 * @version 1.0
 */
public class TextDraw implements ImageOperation{

    @Override
    public BufferedImage apply(BufferedImage input) {
        TextBoxGUI textGui = new TextBoxGUI();
        textGui.run();
        

        final Graphics2D g = (Graphics2D) input.getGraphics();
        g.setColor(textGui.getColour());
        g.setFont(textGui.getFont());
        g.drawString(textGui.getText(),GetPoints.getX(),GetPoints.getY());
        g.dispose();
        ImagePanel.rect = null;
        return input;
    }
    
}
