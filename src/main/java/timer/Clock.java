package timer;

import gui.ClockPanel;
import piece.Alliance;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class Clock {

    public static final int HOURS_IN_MILLISECONDS = 3600000;
    public static final int MINUTES_IN_MILLISECONDS = 60000;
    public static final int SECONDS_IN_MILLISECONDS = 1000;

    private ClockPanel clockPanel;

    private long totalTimeInMilliSeconds;

    private Timer clock;
    private TimerTask ads;
    private Alliance alliance;

    private int hours;
    private int minutes;
    private int seconds;
    private int bonusTimePerMove;

    public Clock(final Alliance alliance, TimeControl timeControl, ClockPanel clockPanel) {

        this.clockPanel = clockPanel;

        this.hours = timeControl.getHours();
        this.minutes = timeControl.getMinutes();
        this.seconds = timeControl.getSeconds();
        this.bonusTimePerMove = timeControl.getBonusTimePerMove();
        this.alliance = alliance;

        totalTimeInMilliSeconds = (hours * HOURS_IN_MILLISECONDS)
                                    + (minutes * MINUTES_IN_MILLISECONDS)
                                    + (seconds * SECONDS_IN_MILLISECONDS);

        clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalTimeInMilliSeconds -= 1000;
                hours = (int) (totalTimeInMilliSeconds / HOURS_IN_MILLISECONDS);
                minutes = (int) ((totalTimeInMilliSeconds / MINUTES_IN_MILLISECONDS)) % 60;
                seconds = (int) ((totalTimeInMilliSeconds / SECONDS_IN_MILLISECONDS)) % 60;
                clockPanel.updateTimeLabel();
            }
        });

    }

    public Alliance getAlliance() {
        return alliance;
    }

    public int getBonusTimePerMove() {
        return bonusTimePerMove;
    }

    public int getHours(){
        return hours;
    }

    public int getSeconds(){
        return seconds;
    }

    public int getMinutes(){
        return minutes;
    }


    public void start(){
        clock.start();
    }

    public void stop(){
        totalTimeInMilliSeconds += bonusTimePerMove * SECONDS_IN_MILLISECONDS;
        clock.stop();
    }

    public boolean isFlagDown(){
        return seconds == 0 && hours == 0 && minutes == 0;
    }
}
