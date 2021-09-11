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

    /**
     *
     * @return
     *
     * Calculating not take moves
     */
    @Override
    public ArrayList<Move> calculateNotTakeMoves() {
        ArrayList<Move> result = new ArrayList<>();

        for(int offset : LEGAL_MOVE_OFFSET){
            offset *= alliance.getDirection();
            if(!((BoardUtils.FIRST_COLUMN[currentCoordinate] && offset == 7)
                    || (BoardUtils.FIRST_COLUMN[currentCoordinate] && offset == -9)
                    || (BoardUtils.EIGHTH_COLUMN[currentCoordinate] && offset == 9)
                    || (BoardUtils.EIGHTH_COLUMN[currentCoordinate] && offset == -7))) {


                int destinationCoordinate = currentCoordinate + offset ;

                if (!BoardUtils.inTheLimit(destinationCoordinate)) {
                    continue;
                }

                if (board.getTile(destinationCoordinate).isTileEmpty()) {
                    result.add(new NormalMove(currentCoordinate, destinationCoordinate, this, alliance)); // TODO Move class will be done later
                }
            }
        }

        legalMoves = result;
        return result;
    }

    @Override
    public ArrayList<Move> calculateTakeMoves(){

        ArrayList<Move> takeMoves = new ArrayList<>();

        for(int offset : LEGAL_MOVE_OFFSET){

            offset *= alliance.getDirection();

            int neighbourTile = currentCoordinate + offset;
            int destinationTile = currentCoordinate + 2 * offset;

            if(!(BoardUtils.inTheLimit(neighbourTile) && BoardUtils.inTheLimit(destinationTile))){
                continue;
            }

            if(board.getTile(neighbourTile).getPieceOnTile() != null
                    && board.getTile(neighbourTile).getPieceOnTile().getAlliance() != alliance
                    && board.getTile(destinationTile).isTileEmpty()
                    && !((BoardUtils.FIRST_COLUMN[neighbourTile] && offset == 7)
                    || (BoardUtils.FIRST_COLUMN[neighbourTile] && offset == -9)
                    || (BoardUtils.EIGHTH_COLUMN[neighbourTile] && offset == 9)
                    || (BoardUtils.EIGHTH_COLUMN[neighbourTile] && offset == -7))){

                takeMoves.add(new AttackMove(currentCoordinate
                        , destinationTile
                        , this
                        ,neighbourTile));
            }

        }
        legalMoves = takeMoves;
        return legalMoves;

    }
    @Override
    public String toString(){
        return (alliance.getAlliance() == Alliance.WHITE ? "K" : "k");
    }


    /**
     *  @param move
     *  @param board
     *  @return
     *
     *  Executes the move on the board and the returns new board
     */

    @Override
    public Board executeMove(Move move, Board board){
        boolean isThereAnyTakeMove = false;
        if(move instanceof AttackMove){

            AttackMove attackMove = (AttackMove) move;
            int destinationCoordinate = move.getDestinationCoordinate();
            Alliance alliance = board.getTile(move.getCurrentCoordinate()).getPieceOnTile().getAlliance();

            if(BoardUtils.EIGHTH_ROW[destinationCoordinate] || BoardUtils.FIRST_ROW[destinationCoordinate]){
                board.getTile(move.getDestinationCoordinate()).setPieceOnTile(new QueenCheckerPiece(destinationCoordinate, alliance, board));
            }

            else{
                board.getTile(move.getDestinationCoordinate()).setPieceOnTile(new NormalCheckerPiece(destinationCoordinate, alliance, board));
            }

            board.getTile(move.getCurrentCoordinate()).setPieceOnTile(null);
            board.setPieceOnTile(attackMove.getTakenPieceCoordinate(), null); // Terminates the board

            if(!board.getTile(destinationCoordinate).getPieceOnTile().calculateTakeMoves().isEmpty()){
                isThereAnyTakeMove = true;
            }

        }

        else if (move instanceof NormalMove){
            int destinationCoordinate = move.getDestinationCoordinate();
            Alliance alliance = board.getTile(move.getCurrentCoordinate()).getPieceOnTile().getAlliance();
            if(BoardUtils.EIGHTH_ROW[destinationCoordinate] || BoardUtils.FIRST_ROW[destinationCoordinate]){
                board.getTile(move.getDestinationCoordinate()).setPieceOnTile(new QueenCheckerPiece(destinationCoordinate, alliance, board));
            }
            else{
                board.getTile(move.getDestinationCoordinate()).setPieceOnTile(new NormalCheckerPiece(destinationCoordinate, alliance, board));

            }
            board.getTile(move.getCurrentCoordinate()).setPieceOnTile(null);
        }

        System.out.println(board);

        if(isThereAnyTakeMove){
            board.setAlliance(board.alliance().getAlliance());
        }

        else{
            board.setAlliance(board.alliance().getOpposite());
        }

        return board;

    }

}
