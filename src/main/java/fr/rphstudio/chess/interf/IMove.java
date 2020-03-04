package fr.rphstudio.chess.interf;

import fr.rphstudio.chess.game.Board;

import java.util.List;

public interface IMove {

    List<IChess.ChessPosition> getPieceMoves(IChess.ChessPosition position, Board board) throws OutOfBoardException;
}
