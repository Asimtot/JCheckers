package piece;

import board.Board;
import move.Move;

import java.util.ArrayList;
import java.util.List;

public class KingCheckerPiece extends CheckerPiece{

    private static final CheckerPieceType KING_CHECKER_PIECE = CheckerPieceType.KING_CHECKER_PIECE_TYPE;

    public KingCheckerPiece(int tileNumber, Alliance alliance, Board board){
        super(KING_CHECKER_PIECE, tileNumber, alliance, board);
    }
    @Override
    public ArrayList<Move> calculateNotTakeMoves() {
        return null;
    }

    @Override
    public ArrayList<Move> calculateTakeMoves() {
        return null;
    }

    @Override
    public Board executeMove(Move move, Board board) {
        return null;
    }


}
