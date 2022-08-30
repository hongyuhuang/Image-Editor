package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Text menu.
 * </p>
 * 
 * <p>
 * The Text menu is very common across applications, 
 * The text box is what the user would expect to find here.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Hongyu Huang, Michael Young
 * @version 1.0
 */

public class TextActions {
    /** A list of actions for the Image menu and toolbar. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;

    /**
     * <p>
     * Create a set of Text menu actions.
     * </p>
     */
    public TextActions() {
        actions = new ArrayList<Action>();
        actions.add(new TextBoxAction("Text Box", null, "Draws a text box", Integer.valueOf(KeyEvent.VK_T),KeyStroke.getKeyStroke(KeyEvent.VK_T,
        InputEvent.CTRL_DOWN_MASK)));
    }

    /**
     * <p>
     * Create a menu containing the list of Text actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu imageMenu = new JMenu("Text");
        for (Action action : actions) {
            imageMenu.add(new JMenuItem(action));
        }
        return imageMenu;
    }

     /**
         * <p>
         * Create a new TextBox clockwise action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
    public class TextBoxAction extends ImageAction {
        TextBoxAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new DrawText(target));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }    
    }
}

