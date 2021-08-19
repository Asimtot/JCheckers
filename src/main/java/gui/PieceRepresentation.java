/**
 *  @author Efe Can Tepe
 *  @version 08.16.2021
 *
 *  This class is created for common representation for the piece on the board GUI
*/


package gui;

import piece.Alliance;
import javax.swing.*;
import java.awt.*;

public class PieceRepresentation extends JPanel {

    public static final Color whitePiece = new Color(255,0,0);
    public static final Color blackPiece = new Color(0,0,255);

    public final int CIRCLE_RADIUS ;
    public final int CIRCLE_CENTER_X;
    public final int CIRCLE_CENTER_Y;

    private Alliance alliance;
    private BoardTile boardTile;


    public PieceRepresentation(BoardTile boardTile, Alliance alliance){

        CIRCLE_RADIUS = 40; //
        CIRCLE_CENTER_X = boardTile.getWidth() / 2;
        CIRCLE_CENTER_Y = boardTile.getHeight() / 2;

        this.boardTile = boardTile;
        this.alliance = alliance;

        setOpaque(false);

    }

   public void paint(Graphics g){
        super.paint(g);

       if(alliance == Alliance.WHITE){
           g.setColor(whitePiece);
       }

       else if (alliance == Alliance.BLACK){
           g.setColor(blackPiece);
       }

       g.drawOval(0,0, CIRCLE_RADIUS, CIRCLE_RADIUS);
       g.fillOval(0,0, CIRCLE_RADIUS, CIRCLE_RADIUS);

    }
}
