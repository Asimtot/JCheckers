package gui;

import board.Board;
import board.BoardUtils;
import move.AttackMove;
import move.Move;
import move.NormalMove;
import piece.CheckerPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BoardTile extends JPanel {

    private static final Dimension TILE_DIMENSION = new Dimension(40,40);
    private static final Color DARK_COLOR = new Color(0, 0, 0);
    private static final Color LIGHT_COLOR = new Color(200, 200, 200);
    private final int tileNumber;
    private BoardPanel boardPanel;
    private Board board;
    private CheckerPiece tileCheckerPiece;

    public BoardTile(int tileNumber, BoardPanel boardPanel, Board board){
        tileCheckerPiece = board.getTile(tileNumber).getPieceOnTile();
        this.boardPanel = boardPanel;
        this.board = board;
        this.tileNumber = tileNumber;
        setSize(TILE_DIMENSION);
        setTileColor(tileNumber);
        addMouseListener(new MouseActionListener());
    }

    private void setTileColor(int tileNumber){

        if(((BoardUtils.EIGHTH_ROW[tileNumber]
                || BoardUtils.SIXTH_ROW[tileNumber]
                || BoardUtils.FOURTH_ROW [tileNumber]
                || BoardUtils.SECOND_ROW[tileNumber])
                && tileNumber % 2 == 1)
                || (BoardUtils.SEVENTH_ROW[tileNumber]
                || BoardUtils.FIFTH_ROW[tileNumber]
                || BoardUtils.THIRD_ROW[tileNumber]
                || BoardUtils.FIRST_ROW[tileNumber])
                && tileNumber % 2 == 0){

            setBackground(DARK_COLOR);
        }

        else{
            setBackground(LIGHT_COLOR);
        }

    }

    public class MouseActionListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {

            if(SwingUtilities.isLeftMouseButton(e)){

                System.out.println("Selected piece is: " + boardPanel.getSelectedCheckerPiece());
                System.out.println("tile number is : " + tileNumber);
                System.out.println("Checker piece on tile is: " + board.getTile(tileNumber).getPieceOnTile());



                if(tileCheckerPiece != null && boardPanel.getSelectedCheckerPiece() == null){
                    boardPanel.setSelectedCheckerPiece(tileCheckerPiece);
                }

                else if(boardPanel.getSelectedCheckerPiece() != null){

                    System.out.println("Else if method entered");

                    CheckerPiece checkerPiece = boardPanel.getSelectedCheckerPiece();

                    ArrayList<Move> legalMoves = checkerPiece.getLegalMoves();


                    for(Move move : legalMoves){

                        if(move instanceof AttackMove){

                            System.out.println("AttackMove instance of method entered");

                            move = (AttackMove) move;

                            if(move.equals(new AttackMove(boardPanel.getSelectedCheckerPiece().getCurrentCoordinate()
                                    , tileNumber, boardPanel.getSelectedCheckerPiece(), ((AttackMove) move).getTakenPieceCoordinate()))){
                                board = boardPanel.getSelectedCheckerPiece().executeMove( move, board);
                                break;
                            }

                            else{
                                System.out.println("Invalid move");
                            }

                        }

                        else if(move instanceof NormalMove){

                            System.out.println("Move instance of method entered");

                            move = (NormalMove) move;

                            if(move.equals(new NormalMove(boardPanel.getSelectedCheckerPiece().getCurrentCoordinate()
                                    , tileNumber
                                    , boardPanel.getSelectedCheckerPiece()
                                    , boardPanel.getSelectedCheckerPiece().getAlliance() ))){

                                board = boardPanel.getSelectedCheckerPiece().executeMove( move ,board);
                                break;
                            }

                        }
                    }

                    /*
                        TODO Tile has no idea what the fuck is going. Forget the method
                             which updates pieceOnBoardTile. So we cannot
                    */

                    System.out.println(Color.GREEN + " Setting board panel " + Color.GREEN);
                    boardPanel.setBoard(board);
                    boardPanel.setPieces();
                    boardPanel.setSelectedCheckerPiece(null);
                    boardPanel.start();
                    System.out.println(Color.GREEN + " Setting board panel " + Color.GREEN);
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
    }
}
