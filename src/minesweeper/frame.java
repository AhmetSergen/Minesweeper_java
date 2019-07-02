/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.BorderLayout;
import javax.swing.JFrame;
/**
 *
 * @author Ahmet
 */
public class frame extends JFrame {
   
    public frame(){
        setTitle("Minesweeper"); 
        setSize(500,520);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        
        setLayout(new BorderLayout());
        
        headline HL = new headline();
        centergrid CG = new centergrid();
        
        add(HL,BorderLayout.NORTH);
        add(CG,BorderLayout.CENTER);
        
    }
}
