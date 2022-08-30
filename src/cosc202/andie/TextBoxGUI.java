package cosc202.andie;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JColorChooser;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.*;

/**
 * <p>
 *  Method to create the GUI to add text to the image.
 *  Takes input and sets variables to be returned by getters.
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
public class TextBoxGUI extends JPanel{
    
    TextBoxGUI(){
    }
    String text;
    String fontString;
    int fontSize;
    boolean bold;
    boolean italic;
    Color colour;
    Font font;

    /**
     * Creates a JPanel that has all of the labels and inputs neccessary to create text, does calculations and then sets all variables.
     */
    public void run(){
        JPanel panel = new JPanel();
        String[] fonts = {"Arial", "Calibri", "Cambria", "Franklin Gothic","Futura", "Garamond", "Helvetica", "Rockwell", "Times New Roman", "Verdana" };

        JLabel textLabel = new JLabel("Text");
        textLabel.setBounds(120,10,80,25);
        panel.add(textLabel);
        JTextArea userText = new JTextArea(5,20);
        userText.setBounds(10,30,270,100);
        panel.add(userText);
        JLabel fontLabel = new JLabel("Font");
        fontLabel.setBounds(10,125,80,25);
        panel.add(fontLabel);
        JComboBox<String> fontBox = new JComboBox<>(fonts);
        fontBox.setBounds(10, 150, 100, 20);
        panel.add(fontBox);

        fontBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                fontString = (String) fontBox.getSelectedItem();
                
            }
            
        });

        JCheckBox boldCheckBox = new JCheckBox("Bold");
        boldCheckBox.setBounds(165,145,50,30);
        JCheckBox italicCheckBox = new JCheckBox("Italics");
        italicCheckBox.setBounds(215,145,80,30);
        panel.add(boldCheckBox);
        panel.add(italicCheckBox);
       

        boldCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    bold = true;
                }else{
                    bold = false; 
                }
                
            }
        });
        italicCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    italic = true;
                }else{
                    italic = false;
                }
                
            }
        });

        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(8,1,100,1));
        sizeSpinner.setBounds(115,150,50,20);
        panel.add(sizeSpinner);

        
        int result = JOptionPane.showConfirmDialog(null,panel,null,JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            text = userText.getText();
                fontSize = (int) sizeSpinner.getValue();
                font = new Font(fontString,Font.PLAIN,fontSize);
                if(bold && italic){
                    font = new Font(fontString, Font.BOLD | Font.ITALIC, fontSize);
                 }
                 else if(bold){
                    font = new Font(fontString, Font.BOLD, fontSize);    
                 }
                 else if(italic){
                    font = new Font(fontString, Font.ITALIC, fontSize);    
                 }
                 else{
                    font = new Font(fontString, Font.PLAIN, fontSize);    
                 }
                try {
                    sizeSpinner.commitEdit();
                }
                catch (ParseException pe) {
                    JComponent editor = sizeSpinner.getEditor();
                    if (editor instanceof DefaultEditor) {
                        ((DefaultEditor)editor).getTextField().setValue(sizeSpinner.getValue());
                    }
                }
                
                colour = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                

        }
    
    }
    /**A range of getters to return various variables. */
    public String getText(){
        return text;
    }
    public Font getFont(){
        return font;
    }
    public Color getColour(){
        return colour;
    }
    }

        




    


    

