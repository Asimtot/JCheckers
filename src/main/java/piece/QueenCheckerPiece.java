package piece;

import board.Board;
import board.BoardUtils;
import move.AttackMove;
import move.Move;
import move.NormalMove;

import java.util.ArrayList;
import java.util.List;

public class QueenCheckerPiece extends CheckerPiece {

    private static final CheckerPieceType QUEEN_CHECKER_PIECE = CheckerPieceType.QUEEN_CHECKER_PIECE_TYPE;
    private static final int [] LEGAL_MOVE_OFFSET = new int [] {-9, -7, 7, 9};

    public QueenCheckerPiece(int tileNumber, Alliance alliance, Board board) {

        super(QUEEN_CHECKER_PIECE, tileNumber, alliance, board);
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

                    if (board.getTile(destinationCoordinate).isTileEmpty()) {

                        result.add(new NormalMove(currentCoordinate, destinationCoordinate, this)); // TODO Move class will be done later

                    }
                }
            }

        }

        else {
            result.addAll(allTakeMoves);
        }
        return result;

    }

    @Override
    public ArrayList<Move> calculateTakeMoves(){

        ArrayList<Move> result = new ArrayList<>();

        for(int offset : LEGAL_MOVE_OFFSET){

            offset = offset * alliance.getDirection();

            int neighbourTiles = currentCoordinate + offset;

            if(!((BoardUtils.FIRST_COLUMN[currentCoordinate] && (offset == 9 || offset == -7) )
                    || (BoardUtils.EIGHTH_COLUMN[currentCoordinate] && (offset == 7 || offset == -9)))){

                CheckerPiece holderPiece = board.getTile(neighbourTiles).getPieceOnTile();

                if(holderPiece.checkerPieceType != this.checkerPieceType){

                    int holderPieceCoordinate = holderPiece.getCurrentCoordinate();

                    if(!((BoardUtils.FIRST_COLUMN[holderPieceCoordinate] && (offset == 9 || offset == -7) )
                            || (BoardUtils.EIGHTH_COLUMN[holderPieceCoordinate] && (offset == 7 || offset == -9)))
                            && board.getTile(holderPieceCoordinate).isTileEmpty()){

                        int destinationCoordinate = holderPieceCoordinate + offset;

                        result.add(new NormalMove(currentCoordinate, destinationCoordinate, this));
                    }
                }
            }
        }
        return new ArrayList<>(0);
    }
}
