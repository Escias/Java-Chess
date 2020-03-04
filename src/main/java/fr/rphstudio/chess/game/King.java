package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {

    /**
     * Get possible movements of King.
     * Set horizontal, vertical and diagonal movements for the King (limited to one box).
     * @param p Actual position of the piece.
     * @param board box setting (color, type, empty or not).
     * @return
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, Board board) {
        List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
        list.addAll(MoveUtil.Diagonal(p, board, 1));
        list.addAll(MoveUtil.Horizontal(p, board, 1));
        return list;
    }
}
