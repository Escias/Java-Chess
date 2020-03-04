package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Undo {
    /**
     * sourcePosition:the source position of the piece
     * finalPosition:the final position the piece has been moved at
     * removedPiece:the piece that had been eaten
     * pieceToMove:the piece that had been moved
     */
    private IChess.ChessPosition sourcePosition;
    private IChess.ChessPosition finalPosition;
    private Piece removedPiece;
    private Piece pieceToMove;

    /**
     * set the properties of the undo object
     * @param p0 the source position of the piece
     * @param p1 he final position the piece has been moved at
     * @param removedPiece the piece that had been eaten
     * @param pieceToUndo the piece that had been moved
     */
    public Undo(IChess.ChessPosition p0,IChess.ChessPosition p1, Piece removedPiece,Piece pieceToUndo){
        this.sourcePosition =p0;
        this.finalPosition =p1;
        this.removedPiece=removedPiece;
        this.pieceToMove=pieceToUndo;
    }

    /**
     * @return the piece that had been moved
     */
    public Piece getPieceToMove(){
        return pieceToMove;
    }

    /**
     * @return the final position the piece has been moved at
     */
    public IChess.ChessPosition getFinalPosition(){
        return finalPosition;
    }

    /**
     * @return the source position of the piece
     */
    public IChess.ChessPosition getSourcePosition(){
        return sourcePosition;
    }

    /**
     * @return the piece that had been eaten
     */
    public Piece getRemovedPiece(){
        return removedPiece;
    }
}
