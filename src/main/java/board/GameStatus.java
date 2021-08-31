package board;

public enum GameStatus {



    WHITE_WIN{

        public final int WHITE_WON = 1;

        public int getStatus(){
            return WHITE_WON;
        }

    },

    BLACK_WIN{

        public final int BLACK_WON = -1;

        @Override
        public int getStatus() {
            return BLACK_WON;
        }
    },

    GAME_IS_ON{

        public final int GAME_ON = 0;

        @Override
        public int getStatus() {
            return GAME_ON;
        }
    };


    public abstract int getStatus();
}
