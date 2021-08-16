package move;

import piece.CheckerPiece;
import piece.NormalCheckerPiece;

public class Move {

    protected int currentCoordinate;
    protected int destinationCoordinate;
    protected CheckerPiece checkerPiece;

    public Move(int currentCoordinate, int destinationCoordinate, CheckerPiece checkerPiece) {
        this.currentCoordinate = currentCoordinate;
        this.destinationCoordinate = destinationCoordinate;
        this.checkerPiece = checkerPiece;
    }

    public int getCurrentCoordinate(){
        return this.currentCoordinate;
    }

    public int getDestinationCoordinate(){
        return this.destinationCoordinate;
    }


}
