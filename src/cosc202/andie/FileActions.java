package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {
    
    /** A list of actions for the File menu and toolbar. */
    protected ArrayList<Action> actions;
    protected ArrayList<Action> tools;
    protected boolean recordingMacro = false;
    

    /**
     * <p>
     * Create a set of File menu and toolbar actions.
     * </p>
     */
    public FileActions() {
        ImageIcon saveIcon = new ImageIcon("./src/Save.png");
        ImageIcon exportIcon = new ImageIcon("./src/Export.png");
        actions = new ArrayList<Action>();
        tools = new ArrayList<Action>();
        actions.add(new FileOpenAction("Open", null, "Open a file", Integer.valueOf(KeyEvent.VK_O),KeyStroke.getKeyStroke(KeyEvent.VK_O,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileSaveAction("Save", null, "Save the file", Integer.valueOf(KeyEvent.VK_S),KeyStroke.getKeyStroke(KeyEvent.VK_S,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileSaveAsAction("Save As", null, "Save a copy", Integer.valueOf(KeyEvent.VK_A),KeyStroke.getKeyStroke(KeyEvent.VK_A,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileRecordMacroAction("Record Macro", null, "Record a set of actions", Integer.valueOf(KeyEvent.VK_Q),KeyStroke.getKeyStroke(KeyEvent.VK_Q,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileSaveMacroAction("Save Macro", null, "Save recorded actions to a new .ops file", Integer.valueOf(KeyEvent.VK_Q),KeyStroke.getKeyStroke(KeyEvent.VK_Q,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileLoadMacroAction("Load macro", null, "Load and apply a .ops file", Integer.valueOf(KeyEvent.VK_K),KeyStroke.getKeyStroke(KeyEvent.VK_K,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileExportAction("Export", null, "Export image", Integer.valueOf(KeyEvent.VK_E),KeyStroke.getKeyStroke(KeyEvent.VK_E,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileResetAction("Reset", null, "Clear unsaved progress and reset Andie", Integer.valueOf(KeyEvent.VK_P),KeyStroke.getKeyStroke(KeyEvent.VK_P,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new FileExitAction("Exit", null, "Exit the program", Integer.valueOf(0)));
        tools.add(new FileSaveAction("", saveIcon, "Save the file", Integer.valueOf(KeyEvent.VK_S),KeyStroke.getKeyStroke(KeyEvent.VK_S,
        InputEvent.CTRL_DOWN_MASK)));
        tools.add(new FileExportAction("", exportIcon, "Export image", Integer.valueOf(KeyEvent.VK_E),KeyStroke.getKeyStroke(KeyEvent.VK_E,
        InputEvent.CTRL_DOWN_MASK)));

    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("File");

        for(Action action: actions) {
            
            fileMenu.add(new JMenuItem(action));
            
            
        }

        return fileMenu;
    }

    /**
     * <p>
     * Create a toolbar containing the list of File actions.
     * </p>
     * 
     * @return The File toolbar UI element.
     */

    public JToolBar createToolbar() {
        JToolBar fileTool = new JToolBar();
        for(Action tool: tools) {
            fileTool.add(new JMenuItem(tool));
        }


        

        return fileTool;
    }
    

    

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */


        
   
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    System.err.println("Invalid Action.");
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();           
            } catch (Exception ex) {
                System.err.println("There was no file to save");
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

         /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    System.err.println("There was no file to save");
                }
            }
        }

    }

    /**
     * <p>
     * Action to reset the variables in the application.
     * </p>
     * 
     */
    public class FileResetAction extends ImageAction {

        /**
         * <p>
         * Create a new file-reset action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileResetAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the file-reset action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileResetAction is triggered.
         * It resets the values of Andie
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            int input = JOptionPane.showConfirmDialog(null, "All unsaved progress will be lost.", "Reset Andie?", JOptionPane.OK_CANCEL_OPTION);
            if( input == JOptionPane.OK_OPTION) {
                ImageAction.getTarget().reset();
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends AbstractAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

         /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    /**
     * <p>
     * Action to export an image to a chosen file location.
     * </p>
     * 
     * @see EditableImage#export()
     */
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

        /**
         * <p>
         * Callback for when the file-export action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExportAction is triggered.
         * It exports the image to a specified filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().export(imageFilepath);
                } catch (Exception ex) {
                    System.err.println("There was no file to export");
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the begin macro recording.
     * </p>
     */
    public class FileRecordMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new RecordMacro action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileRecordMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

         /**
         * <p>
         * Callback for when the RecordMacro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RecordMacro Action is triggered.
         * It begins the macro recording.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if (ImageAction.getTarget().getImage().hasImage() == false) {
                System.err.println("There was no file to save");
                JOptionPane.showMessageDialog(null, "Please first load an image and try again.", "No image loaded", JOptionPane.ERROR_MESSAGE);
            } else if (recordingMacro == true) {
                int input = JOptionPane.showConfirmDialog(null, "Overwrite current recording?", "Recording already in progress", JOptionPane.YES_NO_OPTION);
                if( input == JOptionPane.YES_OPTION) {
                    recordingMacro = true;
                    EditableImage image = ImageAction.getTarget().getImage();
                    image.startMacroRec();
                }
            } else {
                recordingMacro = true;
                EditableImage image = ImageAction.getTarget().getImage();
                image.startMacroRec();
            }

        }

    }

    /**
     * <p>
     * Action to save a recorded '.ops' file.
     * </p>
     */
    public class FileSaveMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new SaveMacro action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileSaveMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

         /**
         * <p>
         * Callback for when the SaveMacro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SaveMacro Action is triggered.
         * Saves the recorded '.ops' file.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(recordingMacro == false) {
                JOptionPane.showMessageDialog(null, "Please first record a macro and try again.", "No macro recorded", JOptionPane.ERROR_MESSAGE);
            } else {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(target);
                if (result == JFileChooser.APPROVE_OPTION) {
                    recordingMacro = false;
                    EditableImage image = ImageAction.getTarget().getImage();
                    try {
                        String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                        image.endMacroRec(imageFilepath);
                    } catch (Exception ex) {
                        System.err.println("There was no file to save");
                    }
                }
            }
        }
    }

    /**
     * <p>
     * Action to load a '.ops' file to an image.
     * </p>
     */
    public class FileLoadMacroAction extends ImageAction {

        /**
         * <p>
         * Create a new LoadMacro action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param accelerator An accelerator key to use as a shortcut  (ignored if null).
         */
        FileLoadMacroAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            putValue(ACCELERATOR_KEY, accelerator);
        }

         /**
         * <p>
         * Callback for when the LoadMacro action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LoadMacro Action is triggered.
         * It allows the user to select a '.ops' file which is then applied to the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().loadMacro(imageFilepath);
                    target.repaint();
                    target.getParent().revalidate();
                } catch (Exception ex) {
                    System.err.println("Invalid Action.");
                }
            }
        }
    }
}
