package piece;

import move.Move;

import java.util.List;

public abstract class CheckerPiece {

    protected CheckerPieceType checkerPieceType;
    protected int currentCoordinate;
    protected Alliance alliance;

    protected CheckerPiece(CheckerPieceType checkerPieceType, int currentCoordinate, Alliance alliance){
        this.checkerPieceType = checkerPieceType;
        this.currentCoordinate = currentCoordinate;
        this.alliance = alliance;
    }

    public CheckerPieceType getType(){
        return this.checkerPieceType;
    }

    public abstract List<Move> calculateLegalMoves();
    public abstract List<Move> calculateTakeMoves();

    @Override
    public String toString(){
        return "The piece type is: " + checkerPieceType;
    }

}

