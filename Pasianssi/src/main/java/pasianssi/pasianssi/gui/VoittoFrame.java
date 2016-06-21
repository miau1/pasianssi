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
 *
 * @author mikko
 */
public class VoittoFrame extends JFrame{
    
    private JLabel voittoLabel;
    
    public VoittoFrame(){
        this.setTitle("VOITIT PELIN");
        this.setSize(500, 250);
        voittoLabel = new JLabel("ONNEKSI OLKOON!!1", SwingConstants.CENTER);
        this.add(voittoLabel);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setVisible(false);
    }
    
    public void vaihdaTeksti(String teksti){
        voittoLabel.setText(teksti);
    }
    
}
