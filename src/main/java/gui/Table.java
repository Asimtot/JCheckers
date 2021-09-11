package gui;

import board.Board;
import board.BoardUtils;
import board.GameStatus;
import board.Tile;
import move.AttackMove;
import move.Move;
import move.NormalMove;
import piece.Alliance;
import piece.CheckerPiece;
import piece.NormalCheckerPiece;
import piece.QueenCheckerPiece;
import timer.TimeControl;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private final static Color lightTileColor = new Color(180,180,180);
    private final static Color darkTileColor = new Color(90,80,80);

    public final static Dimension NOTATION_DIMENSION = new Dimension(200,200);
    private final static Dimension CLOCK_DIMENSION = new Dimension(200,200);
    private final static Dimension CLOCK_PANEL_DIMENSION = new Dimension(200,600);
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 600);
    private final static Dimension Board_PANEL_DIMENSION = new Dimension(400, 350);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10,10);

    private int moveCount;

    private NotationPanel notationPanel;
    private JPanel clockHolder;

    private ClockPanel whiteClock;
    private ClockPanel blackClock;

    private Board checkerBoard;

    private Tile sourceTile;
    private Tile destinationTile;
    private CheckerPiece humanMovedPiece;
    private JLabel playerToMove;

    private final JMenuBar menuBar;
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;


    public Table(){
        moveCount = 0;

        notationPanel = new NotationPanel();
        whiteClock = new ClockPanel(Alliance.WHITE, new TimeControl(0,5,10,0));
        blackClock = new ClockPanel(Alliance.WHITE, new TimeControl(0,5,10,0));

        whiteClock.setPreferredSize(CLOCK_DIMENSION);
        blackClock.setPreferredSize(CLOCK_DIMENSION);

        this.clockHolder = new JPanel();

        this.clockHolder.setLayout(new BorderLayout());

        this.clockHolder.add(blackClock, BorderLayout.NORTH);
        blackClock.setPreferredSize(new Dimension(100,100));
        this.clockHolder.add(notationPanel, BorderLayout.CENTER);
        this.clockHolder.add(whiteClock, BorderLayout.SOUTH);
        whiteClock.setPreferredSize(new Dimension(100,100));

        this.checkerBoard = new Board();
        this.gameFrame = new JFrame("JCheckers");
        this.menuBar = new JMenuBar();
        this.playerToMove = new JLabel();

        this.playerToMove.setText(checkerBoard.alliance().toString());

        menuBar.add(playerToMove);

        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.boardPanel = new BoardPanel();
        this.gameFrame.setJMenuBar(menuBar);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.add(this.clockHolder, BorderLayout.EAST);
        clockHolder.setPreferredSize(CLOCK_PANEL_DIMENSION);


        this.gameFrame.setVisible(true);

    }

    private class BoardPanel extends JPanel{
        final List<TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            this.setBorder(BorderFactory.createBevelBorder(0));

            for(int i = 0; i < BoardUtils.TILES_NUMBER_IN_BOARD; i++){
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }

            setPreferredSize(Board_PANEL_DIMENSION);
            validate();
        }

        public void drawBoard(final Board board){
            removeAll();
            playerToMove.setText(board.alliance().toString());
            for(final TilePanel tilePanel : boardTiles){
                tilePanel.drawTile(board);
                add(tilePanel);
            }

            validate();
            repaint();
        }

    }

    private class TilePanel extends JButton{

        private final int tileId;

        TilePanel(final BoardPanel boardPanel
                , final int tileId ){
            super();
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignTilePieceIcon(checkerBoard);

            addActionListener(e -> {
                if(sourceTile == null){
                    sourceTile = checkerBoard.getTile(tileId);
                    humanMovedPiece = sourceTile.getPieceOnTile();

                    if(humanMovedPiece == null){
                        sourceTile = null;
                    }
                }

                else{

                    System.out.println("Human Moved Piece is: \n" + humanMovedPiece);
                    Move move = null;
                    destinationTile = checkerBoard.getTile(tileId);
                    checkerBoard.searchMovesInTheBoard();

                    System.out.println("Human Moved Piece Legal Moves" + humanMovedPiece.getLegalMoves());

                    final List<Move> candidateMoveList = humanMovedPiece.getLegalMoves();
                    if(candidateMoveList != null && !candidateMoveList.isEmpty()){
                        if(candidateMoveList.get(0) instanceof NormalMove){
                            move = new NormalMove(sourceTile.getTileCoordinate()
                                    ,destinationTile.getTileCoordinate(), humanMovedPiece, humanMovedPiece.getAlliance() );

                        }

                        else if(candidateMoveList.get(0) instanceof AttackMove){

                            move = new AttackMove(sourceTile.getTileCoordinate()
                                    , destinationTile.getTileCoordinate(), humanMovedPiece
                                    , (sourceTile.getTileCoordinate() + destinationTile.getTileCoordinate()) / 2);

                        }

                        for( Move candidateMove : candidateMoveList){
                            if(candidateMove.equals(move)){
                                checkerBoard = humanMovedPiece.executeMove(move, checkerBoard);
                                notationPanel.update(move, moveCount / 2, moveCount % 2);
                                moveCount++;
                                break;
                            }

                        }
                    }

                    else{
                        System.out.println("Take moves");
                        for(Move legalMoves : checkerBoard.getAllTakeLegalMoves()){
                            System.out.println(legalMoves);
                        }

                        System.out.println("Not Take Moves");
                        for(Move legalMoves : checkerBoard.getNotAllTakeLegalMoves()){
                            System.out.println(legalMoves);
                        }

                    }

                    sourceTile = null;
                    destinationTile = null;
                    humanMovedPiece = null;

                }

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        if(checkerBoard.alliance() == Alliance.WHITE){
                            whiteClock.getClock().start();
                            blackClock.getClock().stop();
                        }

                        else if(checkerBoard.alliance() == Alliance.BLACK){
                            blackClock.getClock().start();
                            whiteClock.getClock().stop();
                        }

                        boardPanel.drawBoard(checkerBoard);

                        if(checkerBoard.isGameFinished() == GameStatus.WHITE_WIN.getStatus()){
                            JOptionPane.showMessageDialog(null, "White Win");
                        }

                        else if(checkerBoard.isGameFinished() == GameStatus.BLACK_WIN.getStatus()){
                            JOptionPane.showMessageDialog(null, "Black Win");
                        }

                    }
                });
            });

            validate();
        }

        public void drawTile(final Board board){
            assignTileColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final Board board) {
            this.removeAll();
            if (!board.getTile(this.tileId).isTileEmpty()) {

                try{
                    if(board.getTile(this.tileId).getPieceOnTile().getAlliance() == Alliance.WHITE
                            && board.getTile(this.tileId).getPieceOnTile() instanceof NormalCheckerPiece){
                        final BufferedImage image = ImageIO.read(new File("art/pieces/WP.gif"));
                        add(new JLabel(new ImageIcon(image)));
                    }

                    else if(board.getTile(this.tileId).getPieceOnTile().getAlliance() == Alliance.BLACK
                            && board.getTile(this.tileId).getPieceOnTile() instanceof NormalCheckerPiece){
                        final BufferedImage image = ImageIO.read(new File("art/pieces/BP.gif"));
                        add(new JLabel(new ImageIcon(image)));
                    }

                    else if(board.getTile(this.tileId).getPieceOnTile().getAlliance() == Alliance.BLACK
                            && board.getTile(this.tileId).getPieceOnTile() instanceof QueenCheckerPiece){
                        final BufferedImage image = ImageIO.read(new File("art/pieces/BQ.gif"));
                        add(new JLabel(new ImageIcon(image)));
                    }

                    else if(board.getTile(this.tileId).getPieceOnTile().getAlliance() == Alliance.WHITE
                            && board.getTile(this.tileId).getPieceOnTile() instanceof QueenCheckerPiece){
                        final BufferedImage image = ImageIO.read(new File("art/pieces/WQ.gif"));
                        add(new JLabel(new ImageIcon(image)));
                    }

                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }

            private void assignTileColor () {

                if (BoardUtils.FIRST_ROW[this.tileId]
                        || BoardUtils.THIRD_ROW[this.tileId]
                        || BoardUtils.FIFTH_ROW[this.tileId]
                        || BoardUtils.SEVENTH_ROW[this.tileId]) {
                    setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
                } else if (BoardUtils.SECOND_ROW[this.tileId]
                        || BoardUtils.FOURTH_ROW[this.tileId]
                        || BoardUtils.SIXTH_ROW[this.tileId]
                        || BoardUtils.EIGHTH_ROW[this.tileId]) {
                    setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
                }
            }

        }
}
