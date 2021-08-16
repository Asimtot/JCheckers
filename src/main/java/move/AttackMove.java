package move;

import piece.CheckerPiece;

public class AttackMove extends Move {

    public AttackMove(int currentCoordinate, int destinationCoordinate, CheckerPiece checkerPiece,
                      CheckerPiece takenPiece) {
        super(currentCoordinate, destinationCoordinate, checkerPiece);
    }
}
