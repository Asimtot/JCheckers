import board.BoardUtils;

public class BoardUtilsTest {

    public static void main (String [] args){

        for(int a = 0; a <  BoardUtils.TILES_NUMBER_IN_BOARD; a++){
            if(BoardUtils.FIRST_COLUMN[a]){
                System.out.println( a + " is: " + BoardUtils.FIRST_COLUMN[a]);
            }
        }

        System.out.println("-------------------------------");

        for(int a = 0; a <  BoardUtils.TILES_NUMBER_IN_BOARD; a++){
            if(BoardUtils.EIGHTH_COLUMN[a]){
                System.out.println( a + " is: " + BoardUtils.EIGHTH_COLUMN[a]);
            }
        }
    }
}
