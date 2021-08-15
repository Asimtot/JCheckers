package piece;

import move.Move;

import java.util.ArrayList;
import java.util.List;

public class NormalCheckerPiece extends CheckerPiece{

    final static private CheckerPieceType NORMAL_CHECKER_PIECE = CheckerPieceType.NORMAL_CHECKER_PIECE_TYPE;

    final static int [] LEGAL_MOVE_OFFSET = new int [] {7, 9};

    private ArrayList<Move> legalMoves;
    private Alliance alliance;

    public NormalCheckerPiece(int currentCoordinate, Alliance alliance){
        super(NORMAL_CHECKER_PIECE, currentCoordinate, alliance);
    }


    @Override
    public List<Move> calculateLegalMoves() {
        return null;
    }

    @Override
    public List<Move> calculateTakeMoves(){
        return null;
    }
}
