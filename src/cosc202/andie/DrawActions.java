package cosc202.andie;

import java.util.*;
import java.awt.event.*;


import javax.swing.*;
/**
 * <p>
 * Actions provided by the Draw menu.
 * </p>
 * 
 * <p>
 * The Draw menu is very common across a wide range of applications.
 * The operations that are found here are used to draw shapes and lines on an image
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Andrew Clarkson
 * @version 1.0
 */

public class DrawActions {
    /** A list of actions for the Draw menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Draw menu actions.
     * </p>
     */
    public DrawActions(){
        actions = new ArrayList<Action>();
        actions.add(new RectangleShapeAction("Rectangle", null, "Rectangle", Integer.valueOf(KeyEvent.VK_R),KeyStroke.getKeyStroke(KeyEvent.VK_R,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new OvalShapeAction("Oval", null, "Oval", Integer.valueOf(KeyEvent.VK_O),KeyStroke.getKeyStroke(KeyEvent.VK_O,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new RectangleFillShapeAction("Rectangle Fill", null, "Rectangle Fill", Integer.valueOf(KeyEvent.VK_R),KeyStroke.getKeyStroke(KeyEvent.VK_R,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new OvalFillShapeAction("Oval Fill", null, "Oval Fill", Integer.valueOf(KeyEvent.VK_O),KeyStroke.getKeyStroke(KeyEvent.VK_O,
        InputEvent.CTRL_DOWN_MASK)));
        actions.add(new LineShapeAction("Line", null, "Line", Integer.valueOf(KeyEvent.VK_L),KeyStroke.getKeyStroke(KeyEvent.VK_L,
        InputEvent.CTRL_DOWN_MASK)));
        
    }

    /**
     * <p>
     * Create a menu containing the list of Draw actions.
     * </p>
     * 
     * @return The draw menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawMenu = new JMenu("Draw");

        for (Action action: actions) {
            drawMenu.add(new JMenuItem(action));
        }

        return drawMenu;
    }


    /**
     * <p>
     * Action to draw a rectangle outline.
     * </p>
     * 
     * @see RectangleShape#apply()
     */
    public class RectangleShapeAction extends ImageAction{

        RectangleShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new RectangleShape(ImagePanel.rect));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
            
        }

    }
    /**
     * <p>
     * Action to draw a filled rectangle.
     * </p>
     * 
     * @see RectangleFillShape#apply()
     */

    public class RectangleFillShapeAction extends ImageAction{

        RectangleFillShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
    
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new RectangleFillShape(ImagePanel.rect));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
            
        }

    }

    /**
     * <p>
     * Action to draw an Oval outline.
     * </p>
     * 
     * @see OvalShape#apply()
     */

    public class OvalShapeAction extends ImageAction{

        OvalShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new OvalShape(ImagePanel.rect));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
            
        }

    }

    /**
     * <p>
     * Action to draw a filled Oval.
     * </p>
     * 
     * @see OvalFillShape#apply()
     */

    public class OvalFillShapeAction extends ImageAction{

        OvalFillShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
           
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new OvalFillShape(ImagePanel.rect));
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
            
        }
    }

    /**
     * <p>
     * Action to draw a line.
     * </p>
     * 
     * @see DrawLine#apply()
     */

    public class LineShapeAction extends ImageAction{

        LineShapeAction(String name, ImageIcon icon, String desc, Integer mnemonic, KeyStroke accelerator) {
            super(name, icon, desc, mnemonic, accelerator);
           
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                target.getImage().apply(new DrawLine());
                target.repaint();
                target.getParent().revalidate();
            }catch(Exception ex){
                System.err.println("Invalid Action.");
            }
            
        }
    }
    }

