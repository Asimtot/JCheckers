package piece;

public class QueenCheckerPiece extends CheckerPiece {

    private static final CheckerPieceType QUEEN_CHECKER_PIECE = CheckerPieceType.QUEEN_CHECKER_PIECE_TYPE;

    public QueenCheckerPiece(int tileNumber, Alliance alliance) {

        super(QUEEN_CHECKER_PIECE, tileNumber, alliance);
    }

    @Override
    public List<Move> calculateLegalMoves() {
        return null;
    }

    @Override
    public List<Move> calculateTakeMoves() {
        return null;
    }
}
