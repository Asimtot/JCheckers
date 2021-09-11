package move;

import piece.CheckerPiece;

public class AttackMove extends Move {

    private int takenPieceCoordinate;

    public AttackMove(int currentCoordinate, int destinationCoordinate, CheckerPiece checkerPiece,
                      int takenPieceCoordinate) {
        super(currentCoordinate, destinationCoordinate, checkerPiece);
        this.takenPieceCoordinate = takenPieceCoordinate;
    }

    public int getTakenPieceCoordinate(){
        return this.takenPieceCoordinate;
    }

    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object other){

        if(other == null){
            return false;
        }

        else if(other == this){
            return true;
        }

        else {
            AttackMove attackMove = (AttackMove) other;
            return super.equals(attackMove) && takenPieceCoordinate == attackMove.getTakenPieceCoordinate();

        }
    }

    @Override
    public int hashCode(){
         return 31 * (currentCoordinate + destinationCoordinate + takenPieceCoordinate);
    }
}
