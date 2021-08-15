package piece;

public enum CheckerPieceType {

    NORMAL_CHECKER_PIECE_TYPE {
        @Override
        public CheckerPieceType getCheckerPieceType() {
            return NORMAL_CHECKER_PIECE_TYPE;
        }

        @Override
        public String toString() {
            return "N";
        }
    },

    KING_CHECKER_PIECE_TYPE{
        @Override
        public CheckerPieceType getCheckerPieceType() {
            return KING_CHECKER_PIECE_TYPE;
        }

        @Override
        public String toString() {
            return "K";
        }
    },

    QUEEN_CHECKER_PIECE_TYPE{
        @Override
        public CheckerPieceType getCheckerPieceType() {
            return QUEEN_CHECKER_PIECE_TYPE;
        }

        @Override
        public String toString() {
            return "Q";
        }
    };

    public abstract CheckerPieceType getCheckerPieceType();

    public abstract String toString();

}
