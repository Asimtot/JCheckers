package gui;

import board.Board;
import board.BoardUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Table {

    private static final Color darkColor = new Color(0, 0, 0);
    private static final Color lightColor = new Color(100, 100, 100);
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 400);
    private static final Dimension TILE_DIMENSION = new Dimension(40, 40);

    private JFrame mainFrame;

    public Board board;

    public Table(){

        board = new Board(new Board.BoardBuilder());

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

        }

        public void createTile(){

            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){
                BoardTile tileHolder = new BoardTile(a);
                boardTileList.add(tileHolder);
                add(tileHolder);
            }
        }

        public void setPieces(){

            for(int a = 0; a < BoardUtils.TILES_NUMBER_IN_BOARD; a++){

            }


        }

    }

    public class BoardTile extends JPanel{

        public BoardTile(int tileNumber){

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

        }

    }

}

