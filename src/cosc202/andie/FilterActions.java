package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu and toolbar. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;

    /**
     * <p>
     * Create a set of Filter menu and toolbar actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        tools = new ArrayList<Action>();
        ImageIcon sharpenIcon = new ImageIcon("./src/Sharpen.png");
        actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M),KeyStroke.getKeyStroke(KeyEvent.VK_M,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new SharpenFilterAction("Sharpen filter", null, "Apply a sharpen filter", Integer.valueOf(KeyEvent.VK_V),KeyStroke.getKeyStroke(KeyEvent.VK_V,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new GaussianBlurFilterAction("Gaussian blur filter", null, "Apply a Gaussian blur filter", Integer.valueOf(KeyEvent.VK_C),KeyStroke.getKeyStroke(KeyEvent.VK_C,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new MedianFilterAction("Median filter", null, "Apply a median filter", Integer.valueOf(KeyEvent.VK_B),KeyStroke.getKeyStroke(KeyEvent.VK_B,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new EmbossFilterAction("Emboss filter", null, "Apply an emboss filter", Integer.valueOf(KeyEvent.VK_M),KeyStroke.getKeyStroke(KeyEvent.VK_C,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new SobelFilterAction("Sobel filter", null, "Apply an sobel filter", Integer.valueOf(KeyEvent.VK_P),KeyStroke.getKeyStroke(KeyEvent.VK_C,
        InputEvent.CTRL_DOWN_MASK)));
        tools.add(new SharpenFilterAction("", sharpenIcon, "Apply a sharpen filter", Integer.valueOf(KeyEvent.VK_S),KeyStroke.getKeyStroke(KeyEvent.VK_V,
        InputEvent.CTRL_DOWN_MASK)));
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu filterMenu = new JMenu("Filter");

        for(Action action: actions) {
            filterMenu.add(new JMenuItem(action));
        }

        return filterMenu;
    }

    /**
     * <p>
     * Create a toolbar containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */

    public JToolBar createToolbar() {
        JToolBar filterTool = new JToolBar();
        for(Action tool: tools) {
            filterTool.add(new JMenuItem(tool));
        }


        

        return filterTool;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            

            JSlider radiusSlider = new JSlider(0,10,1);
            radiusSlider.setMajorTickSpacing(5);
            radiusSlider.setMinorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            radiusSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, "Enter filter radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusSlider.getValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new MeanFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }

    }

    public class SharpenFilterAction extends ImageAction{
        /**
         * <p>
         * Create a new sharpen-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }    

        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new SharpenFilter());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class GaussianBlurFilterAction extends ImageAction{

        /**
         * <p>
         * Create a new gaussian-blur-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        GaussianBlurFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }    

        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            

            JSlider radiusSlider = new JSlider(0,10,1);
            radiusSlider.setMajorTickSpacing(5);
            radiusSlider.setMinorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            radiusSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, "Enter filter radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusSlider.getValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new GaussianBlurFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the median filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up slider to ask for the radius value.

            JSlider radiusSlider = new JSlider(0,10,1);
            radiusSlider.setMajorTickSpacing(5);
            radiusSlider.setMinorTickSpacing(1);
            radiusSlider.setPaintTicks(true);
            radiusSlider.setPaintLabels(true);
            radiusSlider.setSnapToTicks(true);
            int option = JOptionPane.showOptionDialog(null, radiusSlider, "Enter filter radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusSlider.getValue();
            }

            // Create and apply the filter
            try{
                target.getImage().apply(new MedianFilter(radius));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

    public class EmbossFilterAction extends ImageAction{

        /**
         * <p>
         * Create a new Emboss-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {

            String choice = "West";
            String [] options = {"West", "North-West", "North", "North-East", "East", "South-East", "South", "South-West"};
            Object selected = JOptionPane.showInputDialog(null, "Choose emboss filter", "Selection", JOptionPane.DEFAULT_OPTION, null, options, "Option 1");
            if(selected != null){
                choice = selected.toString();
            }else{
                System.out.println("User Canceled");
                return;
            }
            
            try{
                target.getImage().apply(new EmbossFilter(choice));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }

        }


    }
    
    public class SobelFilterAction extends ImageAction{
        /**
         * <p>
         * Create a new Sobel-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        public void actionPerformed(ActionEvent e) {

            String choice = "Right Sobel";
            String [] options = {"Right Sobel", "Bottom Sobel"};
            Object selected = JOptionPane.showInputDialog(null, "Choose emboss direction", "Selection", JOptionPane.DEFAULT_OPTION, null, options, "Option 1");
            if(selected != null){
                choice = selected.toString();
            }else{
                System.out.println("User Canceled");
                return;
            }

            
            try{
                target.getImage().apply(new SobelFilter(choice));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }

        }        

    }
}
