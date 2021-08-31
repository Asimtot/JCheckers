package guitests;

import gui.ClockPanel;
import piece.Alliance;
import timer.Clock;
import timer.TimeControl;

import javax.swing.*;

public class ClockPanelTest {

    public static void main (String [] args){
        JFrame frame = new JFrame();

        ClockPanel clockPanel = new ClockPanel(Alliance.BLACK, new TimeControl(0,5,4,1));
        frame.add(clockPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);

        clockPanel.getClock().start();

    }

}
