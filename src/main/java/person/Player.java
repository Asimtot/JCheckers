package person;

import board.Board;
import piece.Alliance;

public class Player extends Person {

    private Board gameBoard;

    public Player(String name, Alliance alliance, Board gameBoard) {
        super(name, alliance);
        this.gameBoard = gameBoard;
    }

    public void resign(){
        // TODO
    }

    public void offerDraw(){
        // TODO
    }

}
