package cosc202.andie;
import javax.swing.JColorChooser;
import java.awt.*;
import java.awt.image.BufferedImage;

class RectangleFillShape implements ImageOperation{


    Rectangle rect;
    public RectangleFillShape(Rectangle rect){
        this.rect = rect;
    }

   
    Color newColour = JColorChooser.showDialog(null, "Choose a color", Color.RED);

    @Override
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g = (Graphics2D) input.getGraphics();
        g.setColor(newColour);
        g.fill(new Rectangle(rect.x, rect.y, rect.width, rect.height));
        g.dispose();
        ImagePanel.rect = null;
        return input;
    }

  
}//rectangle