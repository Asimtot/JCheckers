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


}
