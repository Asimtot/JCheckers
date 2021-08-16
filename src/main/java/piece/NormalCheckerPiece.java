package piece;

import board.Board;
import board.BoardUtils;
import move.AttackMove;
import move.Move;
import move.NormalMove;

import java.util.ArrayList;
import java.util.List;

public class NormalCheckerPiece extends CheckerPiece{

    final static private CheckerPieceType NORMAL_CHECKER_PIECE = CheckerPieceType.NORMAL_CHECKER_PIECE_TYPE;
    final int [] LEGAL_MOVE_OFFSET = new int [] {7, 9};

    public NormalCheckerPiece(int currentCoordinate, Alliance alliance, Board board){
        super(NORMAL_CHECKER_PIECE, currentCoordinate, alliance, board);
        legalMoves = new ArrayList<>();
    }


    @Override
    public ArrayList<Move> calculateNotTakeMoves() {
        ArrayList<Move> result = new ArrayList<>();

        ArrayList<Move> allTakeMoves = calculateTakeMoves();

        if(allTakeMoves.isEmpty()){

            for(int offset : LEGAL_MOVE_OFFSET){

                offset = offset * alliance.getDirection();

                if(!((BoardUtils.FIRST_COLUMN[currentCoordinate] && (offset == 9 || offset == -7) )
                        || (BoardUtils.EIGHTH_COLUMN[currentCoordinate] && (offset == 7 || offset == -9)))){

                    int destinationCoordinate = currentCoordinate + offset * alliance.getDirection();

                    if(!BoardUtils.inTheLimit(destinationCoordinate)){
                        continue;
                    }

                    if (board.getTile(destinationCoordinate).isTileEmpty()) {

                        result.add(new NormalMove(currentCoordinate, destinationCoordinate, this)); // TODO Move class will be done later

                    }
                }
            }

        }

        legalMoves = result;
        return result;
    }

    @Override
    public ArrayList<Move> calculateTakeMoves(){

        ArrayList<Move> result = new ArrayList<>();

        for(int offset : LEGAL_MOVE_OFFSET){

            offset = offset * alliance.getDirection();

            int neighbourTiles = currentCoordinate + offset;

            if(!BoardUtils.inTheLimit(neighbourTiles)){
                continue;
            }

            if(!((BoardUtils.FIRST_COLUMN[currentCoordinate] && (offset == 9 || offset == -7) )
                    || (BoardUtils.EIGHTH_COLUMN[currentCoordinate] && (offset == 7 || offset == -9)))){

                CheckerPiece holderPiece = board.getTile(neighbourTiles).getPieceOnTile();

                if(holderPiece == null){
                    continue;
                }



                if(holderPiece.checkerPieceType != this.checkerPieceType){

                    int holderPieceCoordinate = holderPiece.getCurrentCoordinate();

                    if(!((BoardUtils.FIRST_COLUMN[holderPieceCoordinate] && (offset == 9 || offset == -7) )
                            || (BoardUtils.EIGHTH_COLUMN[holderPieceCoordinate] && (offset == 7 || offset == -9)))
                            && board.getTile(holderPieceCoordinate).isTileEmpty()){

                        int destinationCoordinate = holderPieceCoordinate + offset;

                        int takenPlaceCoordinate = currentCoordinate + offset;

                        result.add(new AttackMove(currentCoordinate, destinationCoordinate, this
                                , board.getTile(takenPlaceCoordinate).getPieceOnTile()));
                    }
                }
            }
        }
        legalMoves = result;
        return result;
    }
    @Override
    public String toString(){
        return alliance.getAlliance() == Alliance.WHITE ? "K" : "k";
    }
}
