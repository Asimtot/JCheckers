package gui;

import board.Board;
import board.BoardUtils;
import move.Move;
import piece.Alliance;
import piece.CheckerPiece;
import piece.NormalCheckerPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class Table {

    private static final Color darkColor = new Color(0, 0, 0);
    private static final Color lightColor = new Color(100, 100, 100);
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 400);
    private static final Dimension TILE_DIMENSION = new Dimension(40, 40);

    public CheckerPiece checkerPiece;

    private JFrame mainFrame;

    public Board board;


    public Table(){
        board = new Board();
        mainFrame = new JFrame("JChecker");
        mainFrame.setDefaultCloseOperation(3);
        mainFrame.setSize(FRAME_DIMENSION);
        mainFrame.add(new BoardPanel());
        mainFrame.setVisible(true);
    }




    public class BoardPanel extends JPanel {

        ArrayList<BoardTile> boardTileList;


        public BoardPanel(){
            setLayout(new GridLayout(8,8));
            boardTileList = new ArrayList<>();
            createTileGUI();
            updatePieces();
        }

        public ArrayList<BoardTile> getBoardTileList(){
            return boardTileList;
        }

        public void createTileGUI(){

            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){
                BoardTile tileHolder = new BoardTile(a, this);
                boardTileList.add(tileHolder);
                add(tileHolder);
            }
        }

        public void updatePieces(){
            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){

                if(board.getTile(a).getPieceOnTile() == null){
                    continue;
                }
                else if(board.getTile(a).getPieceOnTile().getAlliance() == Alliance.WHITE){
                    PieceRepresentation pieceImageHolder = new PieceRepresentation(boardTileList.get(a), Alliance.WHITE);
                    boardTileList.get(a).add(pieceImageHolder);
                }

                else if(board.getTile(a).getPieceOnTile().getAlliance() == Alliance.BLACK){
                    PieceRepresentation pieceImageHolder = new PieceRepresentation(boardTileList.get(a), Alliance.BLACK);
                    boardTileList.get(a).add(pieceImageHolder);
                }

            }
        }



    }

    public class BoardTile extends JPanel{
        public BoardTile(int tileNumber, BoardPanel boardPanel){

            setLayout(new GridLayout(1,1));

            if(((BoardUtils.FIRST_ROW[tileNumber]
                    || BoardUtils.THIRD_ROW[tileNumber]
                    || BoardUtils.FIFTH_ROW[tileNumber]
                    || BoardUtils.SEVENTH_ROW[tileNumber])
                    && tileNumber % 2 == 0)
                    || (((BoardUtils.SECOND_ROW[tileNumber]
                    || BoardUtils.FOURTH_ROW[tileNumber]
                    || BoardUtils.SIXTH_ROW[tileNumber]
                    || BoardUtils.EIGHTH_ROW[tileNumber])
                    && tileNumber % 2 == 1))){

                setBackground(darkColor);

            }

            else{
                setBackground(lightColor);
            }

            addMouseListener(new BoardTileChoose(tileNumber, boardPanel, this));
        }

        /**
         *  @author Efe Can Tepe
         *
         *  Class is created for listening action
         */
        public class BoardTileChoose implements MouseListener {

            private int tileCoordinate;
            private BoardPanel boardPanel;
            private BoardTile boardTile;

            public BoardTileChoose(int tileCoordinate, BoardPanel boardPanel, BoardTile boardTile){
                this.tileCoordinate = tileCoordinate;
                this.boardPanel = boardPanel;
                this.boardTile = boardTile;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    board.searchMovesInTheBoard();

                    if(checkerPiece == null){
                        checkerPiece = board.getTile(tileCoordinate).getPieceOnTile();
                        System.out.println("Tile Coordinate: " + tileCoordinate);
                    }

                    if (checkerPiece != null) {
                        ArrayList<Move> legalMoves = checkerPiece.getLegalMoves();
                        for (Move legalMove : legalMoves) {
                            if (legalMove.getCurrentCoordinate() == checkerPiece.getCurrentCoordinate()
                                    && legalMove.getDestinationCoordinate() == tileCoordinate) {

                                board.getTile(tileCoordinate).setPieceOnTile(new NormalCheckerPiece(tileCoordinate
                                        , checkerPiece.getAlliance(), board));

                                checkerPiece = null;
                                boardPanel.updatePieces();
                                boardPanel.repaint();
                            }
                        }
                        //else if(checkerPiece != null){
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            private void highLightMoveSquares(Move legalMove){
                boardPanel.getBoardTileList().get(legalMove.getDestinationCoordinate()).setBackground(Color.orange);
            }

            private void resetHighLight(BoardPanel panel){
                panel.updatePieces();
                panel.createTileGUI();
            }
        }
    }

}

