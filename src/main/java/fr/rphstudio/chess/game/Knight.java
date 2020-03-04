package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {

    /**
     * Get possible movements of Knight.
     * @param p Actual position of the piece.
     * @param board box setting (color, type, empty or not).
     * @return
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, Board board) {
        List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
        for (int dx = -2; dx <= 2; dx++){
            for (int dy = -2; dy <= 2; dy++){
                if (Math.abs(dx) + Math.abs(dy) == 3){
                    IChess.ChessPosition pos = new IChess.ChessPosition(p.x + dx, p.y + dy);
                    list.addAll(MoveUtil.PossibleMoveKnight(p, pos, board));
                }
            }
        }
        return list;
    }
}
