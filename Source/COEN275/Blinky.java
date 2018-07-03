package COEN275;

import javax.swing.*;
import java.awt.*;

public class Blinky implements Runnable{
    private JButton blinkButton;

    public Blinky(JButton blinkButton){
        this.blinkButton = blinkButton;
    }

    @Override
    public void run() {
        Color bg = blinkButton.getBackground();
        Color fg = blinkButton.getForeground();
        while(true) {
            //if(blinkButton.isEnabled()){
                blinkButton.setBackground(Color.BLUE);
                blinkButton.setForeground(Color.WHITE);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                blinkButton.setBackground(bg);
                blinkButton.setForeground(fg);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //}
        }
    }
}
