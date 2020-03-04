package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

import java.util.ArrayList;
import java.util.List;

public class LostPieces {
    /**
     * list of the dead pieces
     */
    private List<Piece> lostPieces = new ArrayList<>();

    /**
     * init the list lostPieces
     */
    public LostPieces() {
        lostPieces = new ArrayList<Piece>();
    }

    /**
     * add a dead pieces
     * @param piece
     */
    public void lostPieces(Piece piece) {
        lostPieces.add(piece);
    }

    public List<IChess.ChessType> getLostPiecesType(IChess.ChessColor color) {
        List<IChess.ChessType> lostPiecesType = new ArrayList<>();
        for (Piece piece : lostPieces) {
            if (piece.getColor() == color) {
                lostPiecesType.add(piece.getType());
            }
        }
        return lostPiecesType;
    }

    /**
     * remove a piece in the deadPieces list
     * @param pieceToRemove piece to remove in the list
     */
    public void removePiece(Piece pieceToRemove){
        lostPieces.remove(pieceToRemove);
    }
}
