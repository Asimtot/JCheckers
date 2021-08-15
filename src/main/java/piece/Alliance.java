package piece;

public enum Alliance {

    BLACK{
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Alliance getAlliance() {
            return BLACK;
        }

        @Override
        public Alliance getOpposite() {
            return WHITE;
        }
    },

    WHITE{
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Alliance getAlliance() {
            return WHITE;
        }

        @Override
        public Alliance getOpposite() {
            return BLACK;
        }
    };

    public abstract int getDirection();
    public abstract Alliance getAlliance();
    public abstract Alliance getOpposite();

}
