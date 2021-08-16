import board.Board;

public class BoardTest {

    public static void main (String [] args){


        Board board = new Board();

        for(int a = 0; a < 64; a++){
            System.out.println(board.getTile(a));
        }
    }

}
