package timer;

/**
 *  @author Efe Can Tepe
 *
 *  This class is created for player's time control
 */

public class TimeControl  {

    private int hour;
    private int minute;
    private int second;
    private int bonusTimePerMove;

    public TimeControl(int hour, int minute, int second, int bonusTimePerMove){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.bonusTimePerMove = bonusTimePerMove;
        makeTimeControlsValid();
    }



    public int getHours(){
        return hour;
    }

    public int getMinutes(){
        return minute;
    }

    public int getSeconds(){
        return second;
    }

    public int getBonusTimePerMove(){
        return bonusTimePerMove;
    }

    @Override
    public String toString(){
        return "Hours: " + hour
                + " Minutes: " + minute
                + " Seconds: " + second
                + " Bonus Time Per Move: " + bonusTimePerMove;
    }

    public void makeTimeControlsValid(){

        if(second > 60){
            second = second % 60;
            minute = second / 60;
        }

        if(minute > 60){
            minute = minute % 60;
            hour = minute / 60;
        }

        // Hour is 99 at most
        if(hour > 99){
            hour = 99;
        }
    }

}
