package move;

import piece.CheckerPiece;

public class NormalMove extends Move{
    public NormalMove(int currentCoordinate, int destinationCoordinate, CheckerPiece checkerPiece) {
        super(currentCoordinate, destinationCoordinate, checkerPiece);
    }
}
