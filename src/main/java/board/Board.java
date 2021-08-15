package board;

import piece.*;

public class Board {

    private Tile[] gameBoard;
    private boolean isTakeMoveExist;
    private boolean isGameFinished;

    public Board(BoardBuilder boardBuilder){
        gameBoard = BoardBuilder.createBoard(this);
        isTakeMoveExist = false;
        isGameFinished = false;
    }

    public Tile getTile(int tileNumber){
        return gameBoard[tileNumber];
    }

    public static class BoardBuilder {

        /**
         *  Creating the board
         *  @return
         */
        public static Tile [] createBoard(Board board){

            Tile [] result = new Tile[BoardUtils.TILES_NUMBER_IN_BOARD];

            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){
                result[a] = createTile(a, board);
            }

            return result;
        }

        /**
         *  This method sets create and configure table we are going to
         *  @param tileNumber
         *  @return
         */
        private static Tile createTile(int tileNumber, Board board){

            if(tileNumber == 3){
                return new Tile(tileNumber, new KingCheckerPiece(tileNumber, Alliance.BLACK)); // TODO Create Tile with Queen black
            }

            else if(tileNumber == 60){
                return new Tile(tileNumber, new KingCheckerPiece(tileNumber, Alliance.WHITE)); // TODO Create Tile with Queen white
            }

            else if((BoardUtils.EIGHTH_ROW[tileNumber] && tileNumber % 2 == 1)
                    || (BoardUtils.SEVENTH_ROW[tileNumber] && tileNumber % 2 == 0)
                    || (BoardUtils.SIXTH_ROW[tileNumber] && tileNumber % 2 == 1)){

                return new Tile(tileNumber, new NormalCheckerPiece(tileNumber, Alliance.BLACK, board ));
            }

            else if((BoardUtils.THIRD_ROW[tileNumber] && tileNumber % 2 == 0)
                    || (BoardUtils.SECOND_ROW[tileNumber] && tileNumber % 2 == 1)
                    || (BoardUtils.FIRST_ROW[tileNumber] && tileNumber % 2 == 0)){

                return new Tile(tileNumber, new NormalCheckerPiece(tileNumber, Alliance.WHITE, board));
            }

            return new Tile(tileNumber, null);
        }
    }
}
