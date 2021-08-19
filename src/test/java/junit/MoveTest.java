package junit;

import board.Board;
import board.Tile;
import move.AttackMove;
import move.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import piece.Alliance;
import piece.NormalCheckerPiece;


import java.util.ArrayList;

public class MoveTest {

    private Board board;

    @Test
    public void testingMoveOnBoard(){
        board = new Board();
        board.createBoard();

        board.setPieceOnTile(17, null);
        board.setPieceOnTile(19, null);
        board.setPieceOnTile(21, null);
        board.setPieceOnTile(23, null);
        board.setPieceOnTile(40, null);
        board.setPieceOnTile(42, null);
        board.setPieceOnTile(44, null);
        board.setPieceOnTile(46, null);
        board.setPieceOnTile(37, new NormalCheckerPiece(37, Alliance.WHITE, board));
        board.setPieceOnTile(28, new NormalCheckerPiece(28, Alliance.BLACK, board));
        board.searchMovesInTheBoard();

        Assertions.assertEquals(new AttackMove(37
                , 19, board.getTile(37).getPieceOnTile(), 28)
                , board.getTile(37).getPieceOnTile().getLegalMoves().get(0));
    }

}
