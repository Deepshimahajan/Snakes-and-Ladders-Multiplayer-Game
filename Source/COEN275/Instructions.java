package COEN275;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.GenericArrayType;

public class Instructions extends JPanel implements ActionListener {
    private JButton backButton;
    Game game;

    Instructions(Game gameObject){
        JPanel textArea;
        JLabel title;
        JLabel instructionText;
        String instructions;

        game = gameObject;
        instructions = "<html>" +
                "The objective of the game is to be the first player to reach the end by moving across the <br>" +
                "board from square one to the final square. You move left to right across first row, then move<br> " +
                "up to the second and move right to left, and so on.<br><br>" +
                "To take your turn, roll the dice and read the number that you rolled. Your game piece moves <br>" +
                "forward that number of spaces. For example, if you roll a two, move your piece to square two.<br> " +
                "On your next turn, if you roll a five, move your piece forward five squares, ending up on square<br>" +
                " seven.<br><br>" +
                "The ladders on the game board allow you to move upwards and get ahead faster. If you land exactly<br>" +
                " on a square that shows an image of the bottom of a ladder, then you may move your game piece all<br>" +
                " the way up to the square at the top of the ladder.<br><br>" +
                "Some versions have snakes on the board. Snakes move you back on the board because you have to <br>" +
                "slide down them. If you land exactly at the top of a snake, slide your game piece all the way to<br>" +
                " the square at the bottom of the snake or chute.<br><br>" +
                "If you roll a six, then you get an extra turn. First, move your piece forward six squares and <br>" +
                "then roll the die again. If you land on any snakes or ladders, follow the instructions above to<br>" +
                " move up or down and then roll again to take your extra turn. As long as you keep rolling sixes,<br>" +
                " you can keep moving!<br><br>" +
                "The first person to reach the highest square on the board wins, usually square 100.<br><br>" +
                "</html>";
        setLayout(null);
        textArea = new JPanel(new FlowLayout());
        title = new JLabel("How To Play?");
        textArea.setBackground(Color.white);
        instructionText = new JLabel(instructions);
        backButton = new JButton("Back");
        setBackground(Color.white);

        title.setBounds(250,-15,250,100);
        title.setFont(new Font("Serif", Font.CENTER_BASELINE, 31));
        instructionText.setFont(new Font("Serif", Font.CENTER_BASELINE, 13));
      
        textArea.setBounds(70,100,600,350);
        textArea.add(instructionText);
        
        backButton.setBounds(300,500,100,50);
        backButton.setBackground(new Color(242, 221, 169));
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setOpaque(true);
     
        add(title);
        add(textArea);
        add(backButton);
        

        backButton.addActionListener(this);
        game.setContentPane(this);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("images/play.jpeg"));
        lblNewLabel.setBounds(500,423, 297, 171);
        add(lblNewLabel);
        

        backButton.addActionListener(this);
        game.setContentPane(this);
        game.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(backButton)){
            try {
                Welcome welcome = new Welcome(game);
                game.setContentPane(welcome);
                game.setVisible(true);
            } catch (Exception exp) {
                exp.getStackTrace();
            }
        }
    }
}
