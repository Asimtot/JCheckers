package board;

import move.Move;
import piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private ArrayList<Tile> gameBoard;
    private boolean isTakeMoveExist;
    private boolean isGameFinished;

    private Alliance playerToMakeTheMove;

    private ArrayList<Move> allLegalMovesOnTheBoard;

    public Board(){
        gameBoard = createBoard();
        isTakeMoveExist = false;
        isGameFinished = false;
        allLegalMovesOnTheBoard = new ArrayList<>();
        playerToMakeTheMove = Alliance.WHITE;
    }

    public Tile getTile(int tileNumber){
        return gameBoard.get(tileNumber);
    }

    public Alliance alliance (){
        return playerToMakeTheMove;
    }

    /**
     *  @version 8.15.2021
     *  This method is created for searching all the moves on the board
     */
    public void searchMovesInTheBoard(){

        /* TODO: this code block has errors
        for(Tile tile : gameBoard){
            if(tile.getPieceOnTile() != null ){
                List<Move> tempTakeList = tile.getPieceOnTile().calculateTakeMoves();
                allLegalMovesOnTheBoard.addAll(tempTakeList);
            }
        }

       FIXME: When the search Moves In The Board method first entered we are allLegalMovesOnTheBoard is empty
              So code reaches the second **for loop**. But once it is executed, all legal moves became not empty
              and there for we cannot reach the second **for loop**.
              ---
              Bug fix suggestion 1: In Every board state only call this method once.

        if(!allLegalMovesOnTheBoard.isEmpty()){
            return; // Terminates the method if there is any take move on the board
        }

        */

        for(Tile tile : gameBoard){
            if(tile.getPieceOnTile() != null ){
                List<Move> tempNotTakeList = tile.getPieceOnTile().calculateNotTakeMoves();
                allLegalMovesOnTheBoard.addAll(tempNotTakeList);
            }
        }
    }
        /**
         *  Creating the board
         *  @return
         */
        public ArrayList<Tile> createBoard(){
            ArrayList<Tile> result = new ArrayList<>();
            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){
                Tile holder = createTile(a);
                result.add(holder);
            }
            return result;
        }

        /**
         *  This method sets create and configure table we are going to
         *  @param tileNumber
         *  @return
         */
        private Tile createTile(int tileNumber){

            /*
            if(tileNumber == 3){
                return new Tile(tileNumber, new KingCheckerPiece(tileNumber, Alliance.BLACK)); // TODO Create Tile with Queen black
            }

            else if(tileNumber == 60){
                return new Tile(tileNumber, new KingCheckerPiece(tileNumber, Alliance.WHITE)); // TODO Create Tile with Queen white
            }

            TODO: Right now I only make normal checker pieces to test  8/15/2021
            */


            if((BoardUtils.EIGHTH_ROW[tileNumber] && tileNumber % 2 == 1)
                    || (BoardUtils.SEVENTH_ROW[tileNumber] && tileNumber % 2 == 0)
                    || (BoardUtils.SIXTH_ROW[tileNumber] && tileNumber % 2 == 1)){

                return new Tile(tileNumber, new NormalCheckerPiece(tileNumber, Alliance.BLACK, this ));
            }

            else if((BoardUtils.THIRD_ROW[tileNumber] && tileNumber % 2 == 0)
                    || (BoardUtils.SECOND_ROW[tileNumber] && tileNumber % 2 == 1)
                    || (BoardUtils.FIRST_ROW[tileNumber] && tileNumber % 2 == 0)){

                return new Tile(tileNumber, new NormalCheckerPiece(tileNumber, Alliance.WHITE, this));
            }

            return new Tile(tileNumber, null);
        }


    }

