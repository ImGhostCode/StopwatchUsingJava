import javax.swing.*;
import javax.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class App implements Action {

    JFrame frame = new JFrame("StopWatch");
    JLabel label = new JLabel();
    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    int elapsedTime = 0;
    boolean started = false;
    String secondsString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    String hoursString = String.format("%02d", hours);

    Timer timer = new Timer(1000, new Action(){
        public void actionPerformed(ActionEvent e){
            elapsedTime = elapsedTime + 1000;
            seconds = (elapsedTime / 1000) % 60;
            minutes = (elapsedTime / 60000) % 60;
            hours = (elapsedTime / 3600000);
            secondsString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hoursString = String.format("%02d", hours);
            label.setText(hoursString + ":" + minutesString + ":" + secondsString);
        }
    });

    App (){

        label.setText(hoursString + ":" + minutesString + ":" + secondsString);
        label.setBounds(0, 0, 250, 100);
        label.setFont(new Font("Verdana",Font.PLAIN,35));
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setOpaque(true);
        label.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(0, 100, 125, 50);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setFont(new Font("Verdana",Font.PLAIN,25));

        resetButton.setBounds(100, 100, 125, 50);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setFont(new Font("Verdana",Font.PLAIN,25));

        frame.add(label);
        frame.add(startButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(220, 190);
        frame.setResizable(false);
        //frame.pack();
        frame.setVisible(true);
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        int seconds = 0;
        minutes = 0;
        hours = 0;
        elapsedTime = 0;
        started = false;
        secondsString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hoursString = String.format("%02d", hours);
        label.setText(hoursString + ":" + minutesString + ":" + secondsString);
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton){
                if (started == false){
                    start();
                    startButton.setText("Stop");
                    started = true;
                } else {
                    started = false;
                    startButton.setText("Start");
                    stop();
                }
            }

            if (e.getSource() == resetButton){
                started = false;
                startButton.setText("Start");
                reset();
            }
            
        }

    public static void main(String[] args) throws Exception {
        new App();
    }

    

}
