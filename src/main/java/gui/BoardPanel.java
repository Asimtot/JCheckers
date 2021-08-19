package gui;

import board.Board;
import board.BoardUtils;
import piece.CheckerPiece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    private static final Dimension FRAME_DIMENSION = new Dimension(400, 400);
    private Board board;
    private ArrayList<BoardTile> boardTileList; // FIXME: array list must be empty on every time new board comes.
    private CheckerPiece selectedCheckerPiece;

    public BoardPanel(){
        board = new Board();
        boardTileList = new ArrayList<>();
        setLayout(new GridLayout(8,8));
        setSize(FRAME_DIMENSION);
        createTiles();
        setPieces();
        start();
    }

    private void createTiles() {
        for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){
            BoardTile boardTile = new BoardTile(a, this, board);
            add(boardTile);
            boardTileList.add(boardTile);
        }
    }

    public void setPieces(){
        for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){

            if(board.getTile(a).getPieceOnTile() == null){
                continue;
            }
            CheckerPiece checkerPiece = board.getTile(a).getPieceOnTile();
            if(checkerPiece != null){
                BoardTile boardTile = boardTileList.get(a);
                PieceRepresentation pieceRepresentation = new PieceRepresentation(boardTile, checkerPiece.getAlliance());
                boardTile.add(pieceRepresentation);
            }
        }

        repaint();
    }

    public void setBoard(Board board){
        this.board = board;
    }

    public void setSelectedCheckerPiece(CheckerPiece selectedCheckerPiece){
        this.selectedCheckerPiece = selectedCheckerPiece;
    }

    public CheckerPiece getSelectedCheckerPiece(){
        return selectedCheckerPiece;
    }

    public void start(){
        board.searchMovesInTheBoard();
    }

}
