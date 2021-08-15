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

    private CheckerPiece pieceOnTile(){
        return pieceOnTile;
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
}
