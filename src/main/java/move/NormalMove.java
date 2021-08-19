package move;

import piece.Alliance;
import piece.CheckerPiece;

public class NormalMove extends Move{

    private Alliance alliance;

    public NormalMove(int currentCoordinate, int destinationCoordinate, CheckerPiece checkerPiece, Alliance alliance) {
        super(currentCoordinate, destinationCoordinate, checkerPiece);
        this.alliance = alliance;
    }

    @Override
    public String toString(){

        return  super.toString() + " NormalMove " + " Piece is " + checkerPiece + "\n Alliance is: " + alliance.getAlliance() + "\n"
                + "\n ----------------------------\n" ;
    }
}
