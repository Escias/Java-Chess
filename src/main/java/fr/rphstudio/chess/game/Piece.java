package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.List;

public class Piece {
    /**
     * color:color of a piece
     * type:the type of a piece
     * position: the position of a piece
     * imove: interface used to get the move of a piece
     */
    private IChess.ChessColor color;
    private IChess.ChessType type;
    private IChess.ChessPosition position;
    private IMove imove;

    /**
     * sert a piece and all his property
     * @param ChessColor color of the piece
     * @param ChessType type of the piece
     * @param position position of the piece
     * @param move move type of the piece
     */
    public Piece(IChess.ChessColor ChessColor, IChess.ChessType ChessType, IChess.ChessPosition position, IMove move) {
        this.color = ChessColor;
        this.type = ChessType;
        this.position = position;
        this.imove = move;
    }

    /**
     * @return the position of the piece
     */
    public IChess.ChessPosition getPosition() {
        return position;
    }

    /**
     * @return the type of the piece
     */
    public IChess.ChessType getType() {
        return type;
    }

    /**
     * @return the color of the piece
     */
    public IChess.ChessColor getColor() {

        return color;
    }

    /**
     * set the position of the piece
     * @param p1 position the piece is been moved at
     */
    public void setPosition(IChess.ChessPosition p1) {
        this.position.x = p1.x;
        this.position.y = p1.y;
    }

    /**
     * get all the possibles moves of a piece
     * @param board the chess board
     * @return the list of possible moves
     */
    public List<IChess.ChessPosition> getPossibleMoves(Board board) {
        try {
            return imove.getPieceMoves(position, board);
        } catch (OutOfBoardException e) {

        }
        return null;
    }

}
