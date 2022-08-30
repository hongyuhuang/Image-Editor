package cosc202.andie;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;




/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener{
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;
    /**
     * <p>
     * The rectangle that is used to show the mouse selection screen as well as draw all of the shapes.
     * </p>
     */
    static Rectangle rect = null;
    /**
     * <p>
     * The variables that represent the starting x,y coordinate of the rectangle as well as the height and width.
     * </p>
     */
    int x,y,w,h;
    /**
     * <p>
     * The point that the mouse is pressed when selecting a rectangle.
     * </p>
     */
    private Point mousePress = null;
    /**
     * <p>
     * Set to true when the mouse is dragged to create a rectangle.
     * </p>
     */
    private boolean drawing = false;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    protected void reset() {
        image = new EditableImage();
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }


    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    

    
    /**
     * Removes a mouse selection rectangle from the screeen when the mouse is clicked.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        rect = null;
        
        repaint();
    }
    /**
     * Clears the points used for DrawLine and then sets the mousePress variable as well as setting the first round of points for the GetPoints class
     */
    @Override
    public void mousePressed(MouseEvent e) {
        GetPoints.clear();
        mousePress = e.getPoint();
        GetPoints.setPoints(e.getPoint());
        
        
    }
    /**
     * Sets drawing to false signalling that the mouse selection has stopped
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        drawing = false;
        repaint();
        
        
        
    }

    
    /**
     * Signals that the drawing of a selection rectangle has begun.
     * Caclulates the x,y and width and height variables and creates a rectangle using those variables.
     * Sets the second point that is used for drawing a line.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        drawing = true;
        x = Math.min(mousePress.x, e.getPoint().x);
        y = Math.min(mousePress.y, e.getPoint().y);
        w = Math.abs(mousePress.x - e.getPoint().x);
        h = Math.abs(mousePress.y - e.getPoint().y);
        GetPoints.setPoints(e.getPoint());

        rect = new Rectangle(x, y, w, h);
        repaint();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {    
    }
    @Override
    public void mouseEntered(MouseEvent e) {  
    }

    @Override
    public void mouseExited(MouseEvent e) {  
    }

    
    


    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * <p>
     * Draw the mouse selection rectangle on the GUI
     * <p>
     * 
     * @param g The Graphics component to draw the image on.
     */

    private static final Color DRAWING_COLOUR = new Color(200, 200, 255);
    private static final Color DRAWN_COLOUR = Color.blue;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }
        Graphics2D g3  = (Graphics2D) g.create();
        if (rect == null) {
            return;
         } else if (drawing) {
            g3.setColor(DRAWING_COLOUR);
            g3.draw(rect);
         } else {
            g3.setColor(DRAWN_COLOUR);
            g3.draw(rect);
         }
    }

    
    

    
}
