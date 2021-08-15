package piece;

import board.Board;
import move.Move;

import java.util.List;

public class KingCheckerPiece extends CheckerPiece{

    private static final CheckerPieceType KING_CHECKER_PIECE = CheckerPieceType.KING_CHECKER_PIECE_TYPE;

    public KingCheckerPiece(int tileNumber, Alliance alliance, Board board){
        super(KING_CHECKER_PIECE, tileNumber, alliance, board);
    }
    @Override
    public List<Move> calculateNotTakeMoves() {
        return null;
    }

    @Override
    public List<Move> calculateTakeMoves() {
        return null;
    }
}
