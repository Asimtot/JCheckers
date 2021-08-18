package board;

import piece.CheckerPiece;

public class Tile {

    private CheckerPiece pieceOnTile;
    private int tileCoordinate;

    /**
     *
     * @param tileCoordinate
     * @param pieceOnTile
     *
     *  Setting up the
     */
    public Tile(int tileCoordinate, CheckerPiece pieceOnTile){
        this.pieceOnTile = pieceOnTile;
        this.tileCoordinate = tileCoordinate;
    }

    public boolean isTileEmpty(){
        return pieceOnTile == null ? true : false;
    }

    public int getTileCoordinate(){
        return tileCoordinate;
    }

    public CheckerPiece getPieceOnTile(){
        return pieceOnTile;
    }

    @Override
    public String toString(){
        return "Tile number is tile number: " + tileCoordinate
                + " Piece On the tile is : " + pieceOnTile ;

    }

    public void setPieceOnTile(CheckerPiece piece){
        pieceOnTile = piece;
    }
}
