/*
 *  Name:       Sakaria, Hetal
 *  Project:    #1  
 *  Due:        January 22, 2015
 *  Course:     cs-245-01-w15
 *  Description:
                A simple stopwatch.
 */

package hsakaria.stopwatch;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class HsakariaStopWatch implements ActionListener
{
    JLabel displayMsg;
    long startTime;
    Border border;
    
   HsakariaStopWatch()
   {        
        JFrame jfrm = new JFrame("H.Sakaria's Stopwatch");
        jfrm.setLayout(new FlowLayout());
        
        jfrm.setSize(250,100);
        
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        JButton startWatch = new JButton("Start");
        JButton stopWatch = new JButton("Stop");
     
        // Add Action Listener
        startWatch.addActionListener(this);
        stopWatch.addActionListener(this);
        
        // Add Buttons to the frame.
        jfrm.add(startWatch);
        jfrm.add(stopWatch);
        
        displayMsg = new JLabel("<html><body><font color = blue>Press Start to begin timing.</font></body></html>");      
        jfrm.add(displayMsg);
        
        // Set the border to darkGray color
        border =  BorderFactory.createLineBorder(Color.darkGray);

        jfrm.setVisible(true);
        
    } 
    /*****************************************
     * Handle Action Event from Stop and Start
     * button 
     *****************************************/
    public void actionPerformed(ActionEvent ae)
    {             
       if(ae.getActionCommand().equals("Start"))
       {
           startTime = ae.getWhen();
           displayMsg.setText( "<html><<font color = green> <i>Stopwatch is running... </font></html>");
       }
       else
       {
           displayMsg.setBorder(border);
           displayMsg.setText("<html> <font color = blue> Time is:  " + (double)(ae.getWhen()- startTime)/1000 + "sec</font></html> ");
       }            
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new HsakariaStopWatch();                
            }
        });
    }
}
    

