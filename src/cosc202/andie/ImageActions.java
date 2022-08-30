package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Image menu.
 * </p>
 * 
 * <p>
 * The Image menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * All of these methods change the location of all the pixels without changing there values.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Andrew Clarkson, Hongyu Huang, Michael Young, Paddy Borthwick
 * @version 1.0
 */

public class ImageActions {
/** A list of actions for the Image menu and toolbar. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;


    /**
     * <p>
     * Create a set of Image menu and toolbar actions.
     * </p>
     */
    public ImageActions() {
        actions = new ArrayList<Action>();
        tools = new ArrayList<Action>();
        
        actions.add(new ImageFlipVertAction("Flip image vertical", null, "Apply a vertical flip",
                Integer.valueOf(KeyEvent.VK_8),KeyStroke.getKeyStroke(KeyEvent.VK_8,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ImageFlipHoriAction("Flip image horizontal", null, "Apply a horizontal flip",
                Integer.valueOf(KeyEvent.VK_5),KeyStroke.getKeyStroke(KeyEvent.VK_5,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ImageRotateClockwiseAction("Rotate image clockwise", null, "Apply a 90 degree rotation",
                Integer.valueOf(KeyEvent.VK_6),KeyStroke.getKeyStroke(KeyEvent.VK_6,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ImageRotateAntiClockwiseAction("Rotate image anticlockwise", null, "Apply a -90 degree rotation",
                Integer.valueOf(KeyEvent.VK_4),KeyStroke.getKeyStroke(KeyEvent.VK_4,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ImageResizeAction("Resize Image", null, "Apply a contrast", Integer.valueOf(KeyEvent.VK_R),KeyStroke.getKeyStroke(KeyEvent.VK_R,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ImageCropAction("Crop Image", null, "Apply a crop", Integer.valueOf(KeyEvent.VK_R),KeyStroke.getKeyStroke(KeyEvent.VK_R,
                InputEvent.CTRL_DOWN_MASK)));
       
    }

    /**
     * <p>
     * Create a menu containing the list of Image actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu imageMenu = new JMenu("Image");

        for (Action action : actions) {
            imageMenu.add(new JMenuItem(action));
        }

        return imageMenu;
    }

    /**
     * <p>
     * Create a toolbar containing the list of Image actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JToolBar createToolbar() {
        JToolBar imageTool = new JToolBar();
        for(Action tool: tools) {
            imageTool.add(new JMenuItem(tool));
        }


        

        return imageTool;
    }

    public class ImageFlipVertAction extends ImageAction {

        /**
         * <p>
         * Create a new Image flip vert action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageFlipVertAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ImageFlipVert());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class ImageFlipHoriAction extends ImageAction {

        /**
         * <p>
         * Create a new Image flip Hori action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageFlipHoriAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ImageFlipHori());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class ImageRotateClockwiseAction extends ImageAction {

        /**
         * <p>
         * Create a new Image rotate clockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageRotateClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ImageRotateClockwise());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class ImageRotateAntiClockwiseAction extends ImageAction {

        /**
         * <p>
         * Create a new Image rotate anticlockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageRotateAntiClockwiseAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ImageRotateAntiClockwise());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    
    public class ImageResizeAction extends ImageAction {

        /**
         * <p>
         * Create a new Image Resize action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            //Default Scales
            float x_scale = 1f;
            float y_scale = 1f;

            // Pop-up dialog box to ask for the x_scale value.
            SpinnerNumberModel xModel = new SpinnerNumberModel(1, 0, 10, 0.01);
            JSpinner xSpinner = new JSpinner(xModel);
            float option = JOptionPane.showOptionDialog(null, xSpinner, "Enter width factor (0.01 to 10)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                x_scale = xModel.getNumber().floatValue();
            }
            // Pop-up dialog box to ask for the x_scale value.
            SpinnerNumberModel yModel = new SpinnerNumberModel(1, 0, 10, 0.01);
            JSpinner ySpinner = new JSpinner(yModel);
            float option2 = JOptionPane.showOptionDialog(null, ySpinner, "Enter height factor (0.01 to 10)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option2 == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option2 == JOptionPane.OK_OPTION) {
                y_scale = yModel.getNumber().floatValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new ImageResize(x_scale,y_scale));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    
    
    }

    public class ImageCropAction extends ImageAction {

        /**
         * <p>
         * Create a new Image crop action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ImageCropAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ImageCrop(ImagePanel.rect));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }
}
