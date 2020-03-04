package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.EmptyCellException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.OutOfBoardException;

import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {
    /**
     * instance: instance of the game
     * lostPieces:list of all the dead pieces
     * board:the board object
     * timer:the timer object
     */
    private static ChessModel instance = new ChessModel();
    private LostPieces lostPieces;
    private Board board;
    private Timer timer;


    /**
     * launch the game and init all object needed to play
     */
    private ChessModel() {
        reinit();
    }

    /**
     * used to get the instance of the game
     *
     * @return the instance
     */
    public static IChess getInstance() {
        return ChessModel.instance;
    }

    /**
     * re init all the object to restart a game
     */
    @Override
    public void reinit() {
        board = new Board();
        lostPieces = new LostPieces();
        timer = new Timer();
    }


    /**
     * used to get the type of a piece
     *
     * @param p position on the board where we want to get the piece type.
     * @return the type of the pieces
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        try {
            return board.getPiece(p).getType();
        } catch (NullPointerException npe) {
            throw new EmptyCellException();
        }

    }

    /**
     * used to get the color of a piece
     *
     * @param p x/y position on the board where we want to get the piece color.
     * @return the color of the piece
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        try {
            return board.getPiece(p).getColor();
        } catch (NullPointerException npe) {
            throw new EmptyCellException();
        }
    }

    /**
     * used to get the number of remaining pieces in a color
     *
     * @param color the requested color of the pieces to count.
     * @return the number of remaining pieces in this color
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return board.getRemainingPieces(color);
    }

    /**
     * used to get the possible moves of a piec
     *
     * @param p requested piece position.
     * @return the possible move of a piece
     */
    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        try {
            List<ChessPosition> possibleMoves = board.getPiece(p).getPossibleMoves(board);
            return board.checkMoves(p, possibleMoves);
        } catch (NullPointerException npe) {
            return new ArrayList<>();
        }

    }

    /**
     * move the piece
     *
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        Piece lostPiece = board.movePiece(p0, p1);
        if (lostPiece != null) {
            lostPieces.lostPieces(lostPiece);
        }
    }

    /**
     * used to get the state of the king
     *
     * @param color the requested king color.
     * @return the state of the king
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        return board.kingState(color);
    }

    /**
     * used to get the list of dead pieces in a color
     *
     * @param color color of the removed pieces
     * @return the list of the types of dead pieces in a color
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        if (lostPieces != null) {
            return lostPieces.getLostPiecesType(color);
        }
        return new ArrayList<ChessType>();
    }

    /**
     * undo the last move
     *
     * @return true if the move had been undo or false if not
     */
    @Override
    public boolean undoLastMove() {
        return board.moveUndo(lostPieces);
    }

    /**
     * get the timer of a player with his color
     * @param color     The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return the timer of the player using his color
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return timer.getTimer(color, isPlaying);
    }
}
