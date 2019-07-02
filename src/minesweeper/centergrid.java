
package minesweeper;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ahmet
 */
public class centergrid extends JPanel implements ActionListener {
    public static int row; 
    public static int col;
    
  
    public static JButton[][] button = new JButton[12][12]; //Borders of matrix are not used.
    public static int[][] field = new int[12][12]; // number for all buttons. if its bigger than 9, then its a bomb.
                                                  // else it shows how many bombs are there around that button
        
    public centergrid(){ // Constructor
        
        ImageIcon image = new ImageIcon("mine.jpg"); // add mine icon
        setLayout(new GridLayout(10,10,0,0)); // (row,column,hgap,vgap)
 
        
        for (row = 1 ; row <=10 ; row ++){ //add all buttons to grid
            for ( col = 1 ; col <=10 ; col ++){
                button[row][col] = new JButton();
                button[row][col].addActionListener(this); //action listener
                add(button[row][col]);
            }
        }
        
        
        int rand;
        for (row = 0 ; row <= 11 ; row++ ){ // add bombs to random fields(buttons)
            for (col = 0 ; col <=11 ; col ++){
                rand = (int ) (Math.random()*100+1);
                if (rand <= 15){ // %15 chance to have mine
                    field[row][col] = 10;
                }
            }
        }
        
        int r , c;
        for (row = 1 ; row <= 10 ; row++ ){ // find mines and increase all numbers around mines by 1
            for (col = 1 ; col <=10 ; col ++){
                if (field[row][col] > 9){ // if its a bomb , increase all buttons by 1 around that bomb
                    r = row -1; c = col -1;
                    
                    field[r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                    c = col -1;
                    field[++r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                    c = col -1;
                    field[++r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                }
            }
        }
    } // Constructor end
    
    
    public static void reset(){ // Reset all buttons
        
        for (row = 1 ; row <=10 ; row ++){  // reser all field, remove texts and icons from buttons.
            for ( col = 1 ; col <=10 ; col ++){
                field[row][col] = 0;
                button[row][col].setText("");
                button[row][col].setIcon(null);
            }
        }
        
        int rand;
        for (row = 0 ; row <= 11 ; row++ ){ // add mines to random fields(buttons)
            for (col = 0 ; col <=11 ; col ++){
                rand = (int ) (Math.random()*100+1);
                if (rand <= 15){
                    field[row][col] = 10;
                }
            }
        }
        
        int r , c;
        for (row = 1 ; row <= 10 ; row++ ){ // find mines and increase all numbers around mines by 1
            for (col = 1 ; col <=10 ; col ++){
                if (field[row][col] > 9){ // if its a mine , increase all buttons by 1 around that mine
                    r = row -1; c = col -1;
                    
                    field[r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                    c = col -1;
                    field[++r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                    c = col -1;
                    field[++r][c] += 1;
                    field[r][++c] += 1;
                    field[r][++c] += 1;
                }
            }
        }
    } // reset end
 
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int aer = 1 ; aer <=10 ; aer++){     // when a button is clicked , cheack all buttons with action event row,columns
            for (int aec = 1 ; aec <=10 ; aec++){ // find whick one is equals to clicked one
                
                if(ae.getSource() == button[aer][aec]) { // if the clicked one equals that button[aer][aec]
                
                    if(field[aer][aec] > 9){ // if its a mine
                        button[aer][aec].setIcon(new javax.swing.ImageIcon(getClass().getResource("redmine.jpg")));
                        // show mine icon
                        headline.game.setText("Game Over");
                        for(int x = 1; x <=10 ; x++){ // open all other buttons when its game over
                            for(int y = 1 ; y <=10 ; y++){
                                if(field[x][y] > 9){ //if its mine, set icon
                                    if(x!=aer || y!=aec)
                                    button[x][y].setIcon(new javax.swing.ImageIcon(getClass().getResource("mine.jpg")));
                                }
                                    else //if its not mine set text
                                    button[x][y].setText(field[x][y]+"");
                            }
                        }
                    }
                    else if (field[aer][aec]!=0) //if its not 0; show present number only
                    button[aer][aec].setText(field[aer][aec]+""); // show its field number.
                    else if(field[aer][aec]==0){ // if its zero; show number and call method to open related zeros.
                    button[aer][aec].setText(field[aer][aec]+"");
                    openNearbyButtons(aer,aec);
                    }
                }
            }
        }
    }
    public void openNearbyButtons(int mr, int mc){ // method to open all related zeros 
        int tr = mr-1; int tc=mc-1;
        
        for(int x = tr ; x <=tr+2 ; x++){     // scan all buttonds surrounding clicked button
            for(int y = tc ; y <=tc+2 ; y++){
                if(x>=1 && x<=10 && y>=1 && y<=10 ){ // if its not out of borders
                    if(button[x][y].getText().equals("")){ // if button is not opened
                        if(field[x][y]!=0 && field[x][y]<9)  // if field doesnt include a bomb and not a zero
                            button[x][y].setText(field[x][y]+""); // set text of button 
                        else if (field[x][y]==0){            //if zero; set text of button and recall method
                            button[x][y].setText(field[x][y]+"");
                            openNearbyButtons(x,y);
                        }
                    }
                }
            }
        }
    }
}
