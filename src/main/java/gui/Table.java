package gui;

import board.Board;
import board.BoardUtils;
import piece.Alliance;

import javax.swing.*;
import java.awt.*;
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
            createTile();
            setPieces();
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

                if(board.getTile(a).getPieceOnTile() == null){
                    continue;
                }

                // If piece = null just do nothing

                else if(board.getTile(a).getPieceOnTile().getAlliance() == Alliance.WHITE){

                    PieceRepresentation pieceImageHolder = new PieceRepresentation(boardTileList.get(a), Alliance.WHITE);
                    pieceImageHolder.paint(new Graphics() {
                        @Override
                        public Graphics create() {
                            return null;
                        }

                        @Override
                        public void translate(int x, int y) {

                        }

                        @Override
                        public Color getColor() {
                            return null;
                        }

                        @Override
                        public void setColor(Color c) {

                        }

                        @Override
                        public void setPaintMode() {

                        }

                        @Override
                        public void setXORMode(Color c1) {

                        }

                        @Override
                        public Font getFont() {
                            return null;
                        }

                        @Override
                        public void setFont(Font font) {

                        }

                        @Override
                        public FontMetrics getFontMetrics(Font f) {
                            return null;
                        }

                        @Override
                        public Rectangle getClipBounds() {
                            return null;
                        }

                        @Override
                        public void clipRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void setClip(int x, int y, int width, int height) {

                        }

                        @Override
                        public Shape getClip() {
                            return null;
                        }

                        @Override
                        public void setClip(Shape clip) {

                        }

                        @Override
                        public void copyArea(int x, int y, int width, int height, int dx, int dy) {

                        }

                        @Override
                        public void drawLine(int x1, int y1, int x2, int y2) {

                        }

                        @Override
                        public void fillRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void clearRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                        }

                        @Override
                        public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                        }

                        @Override
                        public void drawOval(int x, int y, int width, int height) {

                        }

                        @Override
                        public void fillOval(int x, int y, int width, int height) {

                        }

                        @Override
                        public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                        }

                        @Override
                        public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                        }

                        @Override
                        public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void drawString(String str, int x, int y) {

                        }

                        @Override
                        public void drawString(AttributedCharacterIterator iterator, int x, int y) {

                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public void dispose() {

                        }
                    });
                    boardTileList.get(a).add(pieceImageHolder);
                }

                else if(board.getTile(a).getPieceOnTile().getAlliance() == Alliance.BLACK){

                    PieceRepresentation pieceImageHolder = new PieceRepresentation(boardTileList.get(a), Alliance.BLACK);
                    pieceImageHolder.paint(new Graphics() {
                        @Override
                        public Graphics create() {
                            return null;
                        }

                        @Override
                        public void translate(int x, int y) {

                        }

                        @Override
                        public Color getColor() {
                            return null;
                        }

                        @Override
                        public void setColor(Color c) {

                        }

                        @Override
                        public void setPaintMode() {

                        }

                        @Override
                        public void setXORMode(Color c1) {

                        }

                        @Override
                        public Font getFont() {
                            return null;
                        }

                        @Override
                        public void setFont(Font font) {

                        }

                        @Override
                        public FontMetrics getFontMetrics(Font f) {
                            return null;
                        }

                        @Override
                        public Rectangle getClipBounds() {
                            return null;
                        }

                        @Override
                        public void clipRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void setClip(int x, int y, int width, int height) {

                        }

                        @Override
                        public Shape getClip() {
                            return null;
                        }

                        @Override
                        public void setClip(Shape clip) {

                        }

                        @Override
                        public void copyArea(int x, int y, int width, int height, int dx, int dy) {

                        }

                        @Override
                        public void drawLine(int x1, int y1, int x2, int y2) {

                        }

                        @Override
                        public void fillRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void clearRect(int x, int y, int width, int height) {

                        }

                        @Override
                        public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                        }

                        @Override
                        public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

                        }

                        @Override
                        public void drawOval(int x, int y, int width, int height) {

                        }

                        @Override
                        public void fillOval(int x, int y, int width, int height) {

                        }

                        @Override
                        public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                        }

                        @Override
                        public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

                        }

                        @Override
                        public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

                        }

                        @Override
                        public void drawString(String str, int x, int y) {

                        }

                        @Override
                        public void drawString(AttributedCharacterIterator iterator, int x, int y) {
                            System.out.println("Draw String");
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                            return false;
                        }

                        @Override
                        public void dispose() {

                        }
                    });
                    boardTileList.get(a).add(pieceImageHolder);
                }

            }


        }

    }

    public class BoardTile extends JPanel{

        public BoardTile(int tileNumber){

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

        }

    }

}

