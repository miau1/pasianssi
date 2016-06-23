/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasianssi.pasianssi.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Frame näytetään, kun peliä ladataan.
 * 
 * @author mikko
 */
public class LatausFrame extends JFrame{
    
    private JLabel latausLabel;
    
    public LatausFrame(){
        this.setTitle("Pasianssi");
        this.setSize(1000, 700);
        latausLabel = new JLabel("Ladataan...", SwingConstants.CENTER);
        this.add(latausLabel);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(false);
    }
    
}
