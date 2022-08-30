package cosc202.andie;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JSpinner;

import java.awt.*;

/**
 * GUI to implement a text box
 * 
 * Creates a GUI that has a JTextArea, JComboBox, JSlider, JCheckbox and JButton
 * 
 * @author Hongyu Huang
 * @version 1.0
 */
public class TextBoxPanel extends javax.swing.JDialog {

    // Variables declaration                   
    private String text = "";
    private String family = "";
    private int fontSize = 8;
    private Graphics g;
    private javax.swing.JCheckBox boldCheckBox;
    private javax.swing.JToggleButton cancelBtn;
    private javax.swing.JComboBox<String> fontCmbBox;
    private javax.swing.JLabel fontLabel;
    private javax.swing.JCheckBox itlaicsCheckBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton okayBtn;
    private javax.swing.JSpinner sizeSpinner;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel textLabel;
    // End of variables declaration      

    /**
     * Creates new form TextBoxPanel
     */
    public TextBoxPanel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public TextBoxPanel(Graphics g) {
        initComponents();
        setModal(true);
        this.g = g;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */                     
    private void initComponents() {

        textLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        fontCmbBox = new javax.swing.JComboBox<>();
        SpinnerNumberModel m_numberSpinnerModel;
        m_numberSpinnerModel = new SpinnerNumberModel(8, 8, 72, 1);
        sizeSpinner = new JSpinner(m_numberSpinnerModel);
        boldCheckBox = new javax.swing.JCheckBox();
        itlaicsCheckBox = new javax.swing.JCheckBox();
        okayBtn = new javax.swing.JToggleButton();
        cancelBtn = new javax.swing.JToggleButton();
        fontLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        textLabel.setText("Text");

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        fontCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Calibri", "Cambria", "Franklin Gothic","Futura", "Garamond", "Helvetica", "Rockwell", "Times New Roman", "Verdana" }));

        boldCheckBox.setText("Bold");
        boldCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boldCheckBoxActionPerformed(evt);
            }
        });

        itlaicsCheckBox.setText("Italics");
        itlaicsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itlaicsCheckBoxActionPerformed(evt);
            }
        });

        okayBtn.setText("Okay");
        okayBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okayBtnActionPerformed(evt);
            }
        });

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        fontLabel.setText("Font");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(249, 249, 249))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(okayBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(fontCmbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sizeSpinner)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(boldCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(itlaicsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fontLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(249, 249, 249)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(11, 11, 11)
                .addComponent(fontLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fontCmbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boldCheckBox)
                    .addComponent(itlaicsCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okayBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        pack();
    }                 

    /**
     * Method to handle the event when the bold checkbox is clicked
     * 
     * @param evt When the bold checkbox is clicked
     */
    private void boldCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {                                             
    }                                            

    /**
     * Method to handle the event when the italics checkbox is clicked
     * 
     * @param evt When the italics checkbox button is clicked
     */
    private void itlaicsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {                                                
    }                                               

    /**
     * Method to handle the event when the okay button is clicked
     * 
     * Set the text from the JTextArea as the text to be drawn
     * Set the font family from the JComboBox for the text to be drawn.
     * Set the font size from the JSpinner for the text to be drawn.
     * Set the font style depending on wheather the bold and italics checkbox is clicked.
     * Set the colour of the font depending on what the user picks in the JColourChooser.
     * Draw the text at the position the mouse selection rectangle in the top left and bottom right cornor.
     * 
     * @param evt When the okay button is clicked
     */
    private void okayBtnActionPerformed(java.awt.event.ActionEvent evt) {                                        
         this.text = textArea.getText();
         this.family = (String) fontCmbBox.getSelectedItem();
         try {
             sizeSpinner.commitEdit();
         }
         catch (ParseException pe) {
             JComponent editor = sizeSpinner.getEditor();
             if (editor instanceof DefaultEditor) {
                 ((DefaultEditor)editor).getTextField().setValue(sizeSpinner.getValue());
             }
         }
         this.fontSize = (int) sizeSpinner.getValue();
         Font myFont = new Font(family, Font.PLAIN, fontSize);
         if(boldCheckBox.isSelected() && itlaicsCheckBox.isSelected()){
            myFont = new Font(family, Font.BOLD | Font.ITALIC, fontSize);
         }
         else if(boldCheckBox.isSelected()){
            myFont = new Font(family, Font.BOLD, fontSize);    
         }
         else if(itlaicsCheckBox.isSelected()){
            myFont = new Font(family, Font.ITALIC, fontSize);    
         }
         else{
            myFont = new Font(family, Font.PLAIN, fontSize);    
         }
         g.setFont(myFont);
         Color colour = JColorChooser.showDialog(null, "Choose a color", Color.RED);
         g.setColor(colour);
         g.drawString(text, GetPoints.getX(), GetPoints.getY());        
         dispose();
    }                                       

    /**
     * Method to handle the event when the cancel button is clicked.
     * 
     * Closes the TextBoxPanel dialog window.
     * 
     * @param evt When the cancel button is clicked
     */
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        dispose();
    }                                                      
}