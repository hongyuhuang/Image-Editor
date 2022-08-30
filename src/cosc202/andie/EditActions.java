package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

 /**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EditActions {
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;

    /**
     * <p>
     * Create a set of Edit menu and toolbar actions.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<Action>();
        tools = new ArrayList<Action>();
        ImageIcon UndoIcon = new ImageIcon("./src/Undo.png");
        ImageIcon RedoIcon = new ImageIcon("./src/Redo.png");
        actions.add(new UndoAction("Undo", null, "Undo", Integer.valueOf(KeyEvent.VK_Z),KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new RedoAction("Redo", null, "Redo", Integer.valueOf(KeyEvent.VK_Y),KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        InputEvent.CTRL_DOWN_MASK)));
        tools.add(new UndoAction("", UndoIcon, "Undo", Integer.valueOf(KeyEvent.VK_Z),KeyStroke.getKeyStroke(KeyEvent.VK_Z,
        InputEvent.CTRL_DOWN_MASK)));
        tools.add(new RedoAction("", RedoIcon, "Redo", Integer.valueOf(KeyEvent.VK_Y),KeyStroke.getKeyStroke(KeyEvent.VK_Y,
        InputEvent.CTRL_DOWN_MASK)));
        
    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Edit");

        for (Action action: actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Create a toolbar containing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */

    public JToolBar createToolbar() {
        JToolBar editTool = new JToolBar();
        for(Action tool: tools) {
            editTool.add(new JMenuItem(tool));
        }


        

        return editTool;
    }



    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().undo();
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

     /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */   
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        
        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().redo();
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
        }
    }

}
