package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {

    /**
     * Get possible movements of Pawn.
     * Set movements matching with the color of the piece.
     * @param p Actual position of the piece.
     * @param board box setting (color, type, empty or not).
     * @return
     */
    @Override
    public List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition p, Board board) {
        IChess.ChessPosition pos;
        List<IChess.ChessPosition> list = new ArrayList<IChess.ChessPosition>();
        switch (board.getPiece(p).getColor()) {
            case CLR_WHITE:
                if (p.y == 6) {
                    int dy = -1;
                    pos = new IChess.ChessPosition(p.x, p.y + dy);
                    list.addAll(MoveUtil.MoveStartPawn(p, pos, board, dy));
                } else if (p.y < 6) {
                    int dy = -1;
                    pos = new IChess.ChessPosition(p.x, p.y + dy);
                    list.addAll(MoveUtil.MoveOtherPawn(p, pos, board, dy));
                }
                break;
            case CLR_BLACK:
                if (p.y == 1) {
                    int dy = 1;
                    pos = new IChess.ChessPosition(p.x, p.y + dy);
                    list.addAll(MoveUtil.MoveStartPawn(p, pos, board, dy));
                } else if (p.y > 1) {
                    int dy = 1;
                    pos = new IChess.ChessPosition(p.x, p.y + dy);
                    list.addAll(MoveUtil.MoveOtherPawn(p, pos, board, dy));
                }
                break;
        }
        return list;
    }
}
