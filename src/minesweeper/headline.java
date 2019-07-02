
package minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Ahmet
 */
public class headline extends JPanel implements ActionListener{
    
    public static JLabel game;
    JLabel time;
    JButton restart;
    
    public headline(){// Constructor
        setLayout(new GridLayout(1,3));
               
        time = new JLabel("    Time : ");
        add(time);      
        
        game = new JLabel("New Game",SwingConstants.CENTER);
        add(game);
        
        restart = new JButton ("Restart");
        restart.addActionListener(this);
        add(restart);
        
        new Timer(1000,taskPerformer ).start(); // start timer. call taskPerformer for every 1000 miliseconds
    }
    
    
    int t= 0; // t variable for time
    
    ActionListener taskPerformer = new ActionListener() { //Timer
        @Override
        public void actionPerformed(ActionEvent evt) {
            String text = game.getText();
            if (text != "Game Over"){ // stop caunting if game is over
                t++;
                time.setText("    Time : "+t);
            }
        }
    };

    @Override
    public void actionPerformed(ActionEvent ae) { //Restart button action listener
        if (ae.getSource() == restart){
            centergrid.reset();
            game.setText("New Game");
            t = 0;
        }       
    }
}