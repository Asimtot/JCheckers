package ASCII;

import board.Board;
import board.BoardUtils;
import board.Tile;
import move.Move;
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

        do{
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

        Scanner scan = new Scanner(System.in);
        ArrayList<Move> moveList = new ArrayList<>();

        int numberOfMoves = 0;
        System.out.println(board.getTile(tileCoordinate).getPieceOnTile().getLegalMoves());
        for(Move move : board.getTile(tileCoordinate).getPieceOnTile().getLegalMoves()){
            System.out.println("Move " + numberOfMoves + " is: " + move.getCurrentCoordinate() + ":" + move.getDestinationCoordinate());
            moveList.add(move);
            numberOfMoves++;
        }

        if(moveList.isEmpty()){
            return ;
        }

        NormalCheckerPiece piece = (NormalCheckerPiece)board.getTile(tileCoordinate).getPieceOnTile();
        System.out.println("Pick One");
        int choice = scan.nextInt();
        board = piece.executeMove(moveList.get(choice), board);
    }

}
