package move;

import piece.CheckerPiece;
import piece.NormalCheckerPiece;

public abstract class Move {

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

    @Override
    public String toString(){
        return "Current coordinate is " + currentCoordinate
                + "\nDestination coordinate is " + destinationCoordinate + "\n";
    }

    public CheckerPiece getCheckerPiece(){
        return checkerPiece;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof Move) ){
            return false;
        }

        if(other == this ){
            return true;
        }

        Move move = (Move) other;

        return this.destinationCoordinate == move.getDestinationCoordinate()
                && this.currentCoordinate == move.getCurrentCoordinate();
    }

    @Override
    public int hashCode(){
        return 31 * (destinationCoordinate + currentCoordinate);
    }

}
