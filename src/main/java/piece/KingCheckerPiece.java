package piece;

public class KingCheckerPiece extends CheckerPiece{

    private static final CheckerPieceType KING_CHECKER_PIECE = CheckerPieceType.KING_CHECKER_PIECE_TYPE;

    public KingCheckerPiece(int tileNumber, Alliance alliance){
        super(KING_CHECKER_PIECE, tileNumber, alliance);
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
