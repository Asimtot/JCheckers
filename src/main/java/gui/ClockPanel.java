package gui;

import piece.Alliance;
import timer.Clock;
import timer.TimeControl;

import javax.swing.*;
import java.awt.*;

public class ClockPanel extends JPanel {

    private JLabel timeLabel;
    private Clock clock;

    public ClockPanel(Alliance alliance, TimeControl timeControl){
        clock = new Clock(alliance, timeControl, this);
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Serif",Font.PLAIN, 50));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));

        System.out.println("Clock Panel constructor");
        add(timeLabel);
        updateTimeLabel();
    }

    public void updateTimeLabel(){
        String secondsString = String.format("%02d", clock.getSeconds());
        String minutesString = String.format("%02d", clock.getMinutes());
        String hoursString = String.format("%02d", clock.getHours());

        timeLabel.setText(hoursString + ":" + minutesString + ":" + secondsString );
    }

    public Clock getClock(){
        return clock;
    }
}
