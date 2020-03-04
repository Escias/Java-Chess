package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.List;

public class Bishop implements IMove{

    /**
     * Get possible movements of Bishop.
     * Set diagonal movements for the Bishop.
     * @param p Actual position of the piece.
     * @param board box setting (color, type, empty or not).
     * @return
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, Board board){
        return MoveUtil.Diagonal(p, board, 7);
    }
}
