/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hallarrangement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Ashad Nadeem
 */
class hallArrangement extends JFrame implements ActionListener{
    private static final int width =600;
    private static final int hieght =600;
    private static final int seatRows = 7;
    private static final int seatColumns = 18;
    private JLabel someL,keyL,key1L,key2L,key3L,screenL;
    private boolean value[][] = new boolean[seatRows][seatColumns];
    private boolean tempvalue[][] = new boolean[seatRows][seatColumns];
    private JButton exitJB,submitJB;
    private JButton seat [][];
    private ButtonHandler ebHandler,sbHandler;
    private ImageIcon icong,iconr,icony;
    
    
    public hallArrangement(){
        setTitle("Hall Seating Arrangement");
        someL = new JLabel("Enter the length: ",SwingConstants.RIGHT);
        iconr = new ImageIcon(getClass().getResource("/hallarrangement/seatR.png"));
        icong = new ImageIcon(getClass().getResource("/hallarrangement/seatG.png"));
        icony = new ImageIcon(getClass().getResource("/hallarrangement/seatY.png"));
        
        // EXit button
        exitJB = new JButton("Exit");
        exitJB.setBackground(Color.RED);
        ebHandler = new ButtonHandler();
        exitJB.addActionListener(ebHandler);
        submitJB = new JButton("Submit");
        submitJB.setBackground(Color.CYAN);
        sbHandler = new ButtonHandler();
        submitJB.addActionListener(sbHandler);
        
        // Seat buttons
        seat = new JButton[seatRows][seatColumns];
        for(int i=0;i<seatRows;i++){
            for(int j=0;j<seatColumns;j++){
                //seat[i][j] = new JButton(""+((j+1)));
                seat[i][j] = new JButton();
                seat[i][j].setIcon(iconr); // NOI18N
                seat[i][j].setBorder(null);
                seat[i][j].setPreferredSize(new Dimension(32, 32));
                seat[i][j].setMaximumSize(new Dimension(32, 32));
                seat[i][j].setMinimumSize(new Dimension(32, 32));
                seat[i][j].addActionListener(this);
            }
        }
        
        JPanel panel1 = new JPanel(new GridLayout(seatRows+1,seatColumns+1));
        for(int i=0;i<seatRows+1;i++){
            if(i<seatRows)  
                panel1.add(new JLabel(""+(char)((int)('A')+i),SwingConstants.CENTER));
            else 
                panel1.add(new JLabel(""));
            for(int j=0;j<seatColumns;j++){
                if(i<seatRows)
                    panel1.add(seat[i][j]);
                else 
                    panel1.add(new JLabel(""+(seatColumns-j),SwingConstants.CENTER));
                
            }
            
        }
        JPanel key = new JPanel(new GridLayout(3,3));
        key.add(new JLabel("Please Select your Seats:"));
        key.add(new JLabel());
        key.add(new JLabel());
        keyL = new JLabel("Key:");
        keyL.setForeground(Color.RED);
        keyL.setFont(new Font("Arial", Font.PLAIN, 26));
        key.add(keyL);
        key.add(new JLabel());
        key.add(new JLabel());
        key1L = new JLabel();
        key1L.setIcon(icong);
        key1L.setText("= Availabile");
        key.add(key1L);
        key2L = new JLabel();
        key2L.setIcon(icony);
        key2L.setText("= Selected");
        key.add(key2L);
        key3L = new JLabel();
        key3L.setIcon(iconr);
        key3L.setText("= Booked");
        key.add(key3L);
        
        JPanel button = new JPanel(new GridLayout(2,2));
        button.add(new JLabel());
        button.add(new JLabel());
        button.add(exitJB);
        button.add(submitJB);
        JPanel panel2 = new JPanel(new GridLayout(2,1));
        screenL = new JLabel("Screen This Way", SwingConstants.CENTER);
        screenL.setBackground(Color.ORANGE);
        screenL.setOpaque(true);
        panel2.add(screenL);
        panel2.add(button);
        
        
        
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(key, BorderLayout.NORTH);
        pane.add(panel1, BorderLayout.CENTER);
        pane.add(panel2, BorderLayout.SOUTH);
        
        randomPopulation();
        setValues();
        setSize(width,hieght);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        for(int i=0;i<seatRows;i++){
            for(int j=0;j<seatColumns;j++){
                if(ae.getSource()== seat[i][j])
                    if(! value[i][j]){
                        tempvalue[i][j] = !tempvalue[i][j];
                        paint(i,j);
                    }else{}
            }
        }
    }
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent press){
            if(press.getSource()== exitJB)  System.exit(0);
            if(press.getSource()== submitJB){
                try {
                    //Submit
                    FileWriter file = new FileWriter("file.txt");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(hallArrangement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(hallArrangement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void setValues(){
        for(int i=0;i<seatRows;i++){
            for(int j=0;j<seatColumns;j++){
                if (value[i][j]) seat[i][j].setIcon(iconr);
                else seat[i][j].setIcon(icong);
            }
        }
    }
    public void paint(int i, int j){
        if (tempvalue[i][j]) seat[i][j].setIcon(icony);
        else seat[i][j].setIcon(icong);
            
    }
    public void randomPopulation(){
        for(int i=0;i<seatRows;i++){
            for(int j=0;j<seatColumns;j++){
                if (Math.random()>0.5) value[i][j]=true;
                else value[i][j]=false;
            }
        }
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        hallArrangement h = new hallArrangement();
    }
    
}
