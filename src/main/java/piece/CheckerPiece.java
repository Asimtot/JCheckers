package piece;

import board.Board;
import move.Move;

import java.util.List;

/**
 *   Checker Piece class is created for representing the common attributes of the piece types
 */
public abstract class CheckerPiece {

    protected CheckerPieceType checkerPieceType;
    protected int currentCoordinate;
    protected final Alliance alliance;
    protected final Board board;
    protected boolean isThereAnyTakeMove;

    protected CheckerPiece(CheckerPieceType checkerPieceType, int currentCoordinate, Alliance alliance, Board board){
        this.checkerPieceType = checkerPieceType;
        this.currentCoordinate = currentCoordinate;
        this.alliance = alliance;
        this.board = board;
        this.isThereAnyTakeMove = false;
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

    protected void setIsThereAnyTakeMove(boolean a){
        isThereAnyTakeMove = a;
    }

    public int getCurrentCoordinate(){
        return this.currentCoordinate;
    }

}

