package ASCII;

import board.Board;
import board.BoardUtils;
import board.Tile;
import move.AttackMove;
import move.Move;
import move.NormalMove;
import piece.NormalCheckerPiece;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *  @author Efe Can Tepe
 *  This class is created for ASCII representation of the board and its piece
 */

public class ASCIIBoardRepresentation {

    private Board board;

    public ASCIIBoardRepresentation(){
        board = new Board(); // Creating the board
        start();
    }

    private void representBoard(){
        final StringBuilder builder = new StringBuilder();
        for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){

            final String tileText = tilePrint(board.getTile(a));

            builder.append(String.format("%3s", tileText));

            if((a + 1) % BoardUtils.TILES_NUMBER_IN_COLUMN == 0){
                builder.append("\n");
            }
        }
        System.out.println("Turn to Making the board " + board.alliance());
        System.out.println(builder.toString());
    }

    private String tilePrint(Tile tile){

        if(tile.getPieceOnTile() == null){
            return "-";
        }

        return tile.getPieceOnTile().toString();
    }

    private void start(){
        System.out.println("Welcome..");

        Scanner scan = new Scanner(System.in);

        int a;
        int result = 0;

        do{
            result = board.isGameFinished();

            if(result == 1){
                System.out.println("White wins");
                break;
            }

            else if(result == -1){
                System.out.println("Black Wins");
                break;
            }

            System.out.println("1- Making Move\n2- Exit");
            representBoard();
            a = scan.nextInt();

            if(a == 1){
               board.searchMovesInTheBoard();
               movePanel();
            }

        }while(a != 2);

    }

    private void movePanel() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please Enter The number of tile which your piece on");
        int tileCoordinate = scan.nextInt();
        showLegalMoves(tileCoordinate);
    }

    private void showLegalMoves(int tileCoordinate) {

        if(board.getTile(tileCoordinate).getPieceOnTile() == null){
            System.out.println("Null piece");
            return;
        }

        Scanner scan = new Scanner(System.in);
        ArrayList<Move> moveList = new ArrayList<>();

        int numberOfMoves = 0;
        System.out.println(board.getTile(tileCoordinate).getPieceOnTile().getLegalMoves());
        for(Move move : board.getTile(tileCoordinate).getPieceOnTile().getLegalMoves()){

            if(move instanceof NormalMove){
                System.out.println("Move " + numberOfMoves + " is: " + move.getCurrentCoordinate() + ":" + move.getDestinationCoordinate());
                moveList.add(move);
                numberOfMoves++;
            }

            else if(move instanceof AttackMove){
                System.out.println("Move "
                        + numberOfMoves
                        + " is: "
                        + move.getCurrentCoordinate()
                        + ":"
                        + move.getDestinationCoordinate()
                        + "\nTaken Piece coordinate is "
                        + ((AttackMove) move).getTakenPieceCoordinate());
                moveList.add(move);
                numberOfMoves++;
            }
        }

        if(moveList.isEmpty()){
            return ;
        }

        NormalCheckerPiece piece = (NormalCheckerPiece)board.getTile(tileCoordinate).getPieceOnTile();
        System.out.println("Pick One");

        int choice = scan.nextInt();

        if(choice >= 0 && choice < moveList.size()){
            board = piece.executeMove(moveList.get(choice), board);
            return;
        }

        board.setAllNotTakeLegalMoves(new ArrayList<>());
    }

    private void declareResult(int result){

        if(result == 1){
            System.out.println("White won");
            return;
        }

        System.out.println("Black Won");
    }
}
