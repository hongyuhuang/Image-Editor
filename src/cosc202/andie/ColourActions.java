package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * 
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu and toolbar. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;

    /**
     * <p>
     * Create a set of Colour menu and toolbar actions.
     * </p>
     */
    public ColourActions() {
        ImageIcon brightIcon = new ImageIcon("./src/Bright.png");
        ImageIcon greyIcon = new ImageIcon("./src/GreyScale.png");
        actions = new ArrayList<Action>();
        tools = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G),KeyStroke.getKeyStroke(KeyEvent.VK_G,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new BrightnessAdjusterAction("Adjust brightness", null, "Apply a brightness",
                Integer.valueOf(KeyEvent.VK_L),KeyStroke.getKeyStroke(KeyEvent.VK_L,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new ContrastAdjustmentAction("Adjust contrast", null, "Apply a contrast",
                Integer.valueOf(KeyEvent.VK_K),KeyStroke.getKeyStroke(KeyEvent.VK_K,
                InputEvent.CTRL_DOWN_MASK)));
        actions.add(new PosterisationAction("Posterisation", null, "Apply a posterisation",
                Integer.valueOf(KeyEvent.VK_K),KeyStroke.getKeyStroke(KeyEvent.VK_K,
                InputEvent.CTRL_DOWN_MASK)));
        tools.add(new ConvertToGreyAction("", greyIcon, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G),KeyStroke.getKeyStroke(KeyEvent.VK_G,
        InputEvent.CTRL_DOWN_MASK)));
        tools.add(new BrightnessAdjusterAction("", brightIcon, "Apply a brightness",
                Integer.valueOf(KeyEvent.VK_L),KeyStroke.getKeyStroke(KeyEvent.VK_L,
                InputEvent.CTRL_DOWN_MASK)));
    
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu colourMenu = new JMenu("Colour");

        for(Action action: actions) {
            colourMenu.add(new JMenuItem(action));
        }

        return colourMenu;
    }

    /**
     * <p>
     * Create a toolbar containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JToolBar createToolbar() {
        JToolBar colourTool = new JToolBar();
        for(Action tool: tools) {
            colourTool.add(new JMenuItem(tool));
        }


        

        return colourTool;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }

    }

    public class BrightnessAdjusterAction extends ImageAction {

        /**
         * <p>
         * Create a new Brightness Adjuster action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        BrightnessAdjusterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            //Default Brightness
            int brightness = 1;

            // Pop-up dialog box to ask for the brightness value.
           
            JSlider brightnessSlider = new JSlider(-100,100,0);
            brightnessSlider.setMajorTickSpacing(50);
            brightnessSlider.setMinorTickSpacing(10);
            brightnessSlider.setPaintTicks(true);
            brightnessSlider.setPaintLabels(true);
            brightnessSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, brightnessSlider, "Enter brightness factor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                brightness = brightnessSlider.getValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new BrightnessAdjuster(brightness));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    
    
    }

    public class ContrastAdjustmentAction extends ImageAction {

        /**
         * <p>
         * Create a new Contrast Adjuster action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        ContrastAdjustmentAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            //Default Contrast
            int contrast = 1;

            // Pop-up dialog box to ask for the contrast value.
            JSlider contrastSlider = new JSlider(-100,100,0);
            contrastSlider.setMajorTickSpacing(50);
            contrastSlider.setMinorTickSpacing(10);
            contrastSlider.setPaintTicks(true);
            contrastSlider.setPaintLabels(true);
            contrastSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, contrastSlider, "Enter contrast factor", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                contrast = contrastSlider.getValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new ContrastAdjustment(contrast));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }

        }
    
    
    }

    public class PosterisationAction extends ImageAction {

        /**
         * <p>
         * Create a new Contrast Adjuster action.
         * </p>
         *
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        PosterisationAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {
            //Setting default values
            boolean red = true;
            boolean green = true;
            boolean blue = true;

            //Creating Checkboxes and adding them to a JPanel
            JPanel panel = new JPanel();
            JCheckBox rBox = new JCheckBox("red");
            rBox.setMnemonic(KeyEvent.VK_C);
            panel.add(rBox);
            JCheckBox gBox = new JCheckBox("green");
            gBox.setMnemonic(KeyEvent.VK_G);
            panel.add(gBox);
            JCheckBox bBox = new JCheckBox("blue");
            bBox.setMnemonic(KeyEvent.VK_H);
            panel.add(bBox);

            //Showing them to the user
            int i = JOptionPane.showOptionDialog(null, panel, "Select colour bands", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(!rBox.isSelected()) red = false;
            if(!gBox.isSelected()) green = false;
            if(!bBox.isSelected()) blue = false;

            //Sending info to Posterisation action if its not canceled
            if(i != 2){
                try{
                    target.getImage().apply(new Posterisation(red,green,blue));
                    target.repaint();
                    target.getParent().revalidate();
                }catch(Exception ex){
                    System.err.println("Invalid Action.");
                }
            }
        }
    }



}
