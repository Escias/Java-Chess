package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Queen implements IMove {

    /**
     * Get possible movements of Queen.
     * Set horizontal, vertical and diagonal movements for the Queen.
     * @param p Actual position of the piece.
     * @param board box setting (color, type, empty or not).
     * @return
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, Board board) {
        List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
        list.addAll(MoveUtil.Diagonal(p, board, 7));
        list.addAll(MoveUtil.Horizontal(p, board, 7));
        return list;
    }
}
