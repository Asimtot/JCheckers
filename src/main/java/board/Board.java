package board;

import move.Move;
import piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private ArrayList<Tile> gameBoard;
    private boolean isTakeMoveExist;
    private boolean isGameFinished;

    private ArrayList<Move> allLegalMovesOnTheBoard;

    public Board(){
        gameBoard = createBoard();
        isTakeMoveExist = false;
        isGameFinished = false;
        allLegalMovesOnTheBoard = new ArrayList<>();

    }

    public Tile getTile(int tileNumber){
        return gameBoard.get(tileNumber);
    }

    /**
     *  @version 8.15.2021
     *  This method is created for searching all the moves on the board
     */
    public void searchMovesInTheBoard(){

        System.out.println("Search in the board method entered--------------");

        for(Tile tile : gameBoard){
            if(tile.getPieceOnTile() != null){
                List<Move> tempTakeList = tile.getPieceOnTile().calculateTakeMoves();
                allLegalMovesOnTheBoard.addAll(tempTakeList);
            }
        }

        if(!allLegalMovesOnTheBoard.isEmpty()){
            return; // Terminates the method if there is any take move on the board
        }

        for(Tile tile : gameBoard){

            if(tile.getPieceOnTile() != null){
                List<Move> tempNotTakeList = tile.getPieceOnTile().calculateNotTakeMoves();
                allLegalMovesOnTheBoard.addAll(tempNotTakeList);
            }
        }

        System.out.println(allLegalMovesOnTheBoard);

    }
        /**
         *  Creating the board
         *  @return
         */
        public ArrayList<Tile> createBoard(){

            ArrayList<Tile> result = new ArrayList<>();

            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){

                Tile holder = createTile(a);

                System.out.println(holder);

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

