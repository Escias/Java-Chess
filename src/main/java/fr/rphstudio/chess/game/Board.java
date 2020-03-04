package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.ChessException;
import fr.rphstudio.chess.interf.IChess;
import fr.rphstudio.chess.interf.IMove;

import java.util.ArrayList;
import java.util.List;

import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_BLACK;
import static fr.rphstudio.chess.interf.IChess.ChessColor.CLR_WHITE;

public class Board {
    /**
     * list of pieces on the board
     */
    private List<Piece> pieceList;
    /**
     * list of move that had been done
     */
    private List<Undo> undoList= new ArrayList<Undo>();

    /**
     * The constructor place all the pieces on the board at the beginning of a game
     */
    public Board() {
        IChess.ChessColor color;
        IChess.ChessType type = null;
        IMove move = new Pawn();
        pieceList = new ArrayList<Piece>();
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                IChess.ChessPosition cPosition = new IChess.ChessPosition(x, y);
                if (cPosition.y >= 6) {
                    color = CLR_WHITE;
                } else if (cPosition.y <= 1) {
                    color = CLR_BLACK;
                } else {
                    continue;
                }
                if (cPosition.y == 6 || cPosition.y == 1) {
                    move = new Pawn();
                    type = IChess.ChessType.TYP_PAWN;
                } else if (cPosition.x == 0 || cPosition.x == 7) {
                    move = new Rook();
                    type = IChess.ChessType.TYP_ROOK;
                } else if (cPosition.x == 1 || cPosition.x == 6) {
                    move = new Knight();
                    type = IChess.ChessType.TYP_KNIGHT;
                } else if (cPosition.x == 2 || cPosition.x == 5) {
                    move = new Bishop();
                    type = IChess.ChessType.TYP_BISHOP;
                } else if (cPosition.x == 4) {
                    move = new King();
                    type = IChess.ChessType.TYP_KING;
                } else if (cPosition.x == 3) {
                    move = new Queen();
                    type = IChess.ChessType.TYP_QUEEN;
                }
                pieceList.add(new Piece(color, type, (IChess.ChessPosition) cPosition, move));
            }
        }
    }

    /**
     * get a piece using his position
     * @param p position of the piece
     * @return the piece
     */
    public Piece getPiece(IChess.ChessPosition p) {
        for (Piece piece : pieceList) {
            if (piece != null) {
                if (piece.getPosition().equals(p)) {
                    return piece;
                }
            }
        }
        return null;
    }

    /**
     * call the movePiece method without the third argument
     * @param p0 initial position
     * @param p1 position the piece is being moved at
     * @return the piece that has been eaten (if so)
     */
    public Piece movePiece(IChess.ChessPosition p0, IChess.ChessPosition p1) {
        Piece piece = null;
        try {
            piece = this.movePiece(p0, p1, false);
        } catch (ChessException e) {

        }
        return piece;
    }

    /**
     * move the piece
     * can also move the piece and move the piece again to the original position to check if the king is in check in this position
     * @param p0 initial position
     * @param p1 position the piece is being moved at
     * @return the piece that has been eaten (if so)
     */
    public Piece movePiece(IChess.ChessPosition p0, IChess.ChessPosition p1, boolean isFakeMove) throws ChessException {
        Piece pieceToMove = getPiece(p0);
        Piece pieceToRemove = getPiece(p1);
        pieceToMove.setPosition(p1);
        if (getPiece(p1).getType()== IChess.ChessType.TYP_PAWN && (getPiece(p1).getPosition().y == 0 || getPiece(p1).getPosition().y == 7) && isFakeMove==false){
            if(getPiece(p1).getColor()==CLR_WHITE && getPiece(p1).getPosition().y == 0){
                pieceList.remove(getPiece(p1));
                IMove move = new Queen();
                pieceList.add(new Piece(CLR_WHITE, IChess.ChessType.TYP_QUEEN, (IChess.ChessPosition) p1, move));

            }
            else if(getPiece(p1).getColor()==CLR_BLACK && getPiece(p1).getPosition().y == 7){
                pieceList.remove(getPiece(p1));
                IMove move = new Queen();
                pieceList.add(new Piece(CLR_BLACK, IChess.ChessType.TYP_QUEEN, (IChess.ChessPosition) p1, move));
            }
        }
        if (pieceToRemove != null) {
            pieceList.remove(pieceToRemove);
        }
        if (isFakeMove) {
            IChess.ChessKingState status =kingState(pieceToMove.getColor());
            pieceToMove.setPosition(p0);
            if (pieceToRemove != null) {
                pieceList.add(pieceToRemove);
            }
            if (status == IChess.ChessKingState.KING_THREATEN) {
                throw new ChessException();
            }
        }
        else{
            undoList.add(new Undo(p0, p1, pieceToRemove,pieceToMove));
        }
        return pieceToRemove;
    }

    /**
     * undo the last move
     * @return true if the move is successfully undone, false if not
     */
    public boolean moveUndo(LostPieces lostPieces) {
        try{
            Undo undo=undoList.get(undoList.size()-1);
            undo.getPieceToMove().setPosition(undo.getSourcePosition());
            this.addPieces(undo.getRemovedPiece());
            undoList.remove(undo);
            lostPieces.removePiece(undo.getRemovedPiece());

            return true;
        } catch (Exception e) {
            System.out.println("je passe pas un catch");
            return false;
        }

    }

    /**
     * is used to get the number of pieces remaining in a color
     * @param color of pieces you want the remaining number
     * @return the number of pieces remaining in the color
     */
    public int getRemainingPieces(IChess.ChessColor color) {
        int count = 0;
        for (Piece piece : pieceList) {
            if (piece != null) {
                if (piece.getColor().equals(color)) {
                    count++;
                }
            }


        }
        return count;
    }

    /**
     * check if the king is in check
     * @param color color of the king you want to check
     * @return the state of the king
     */
    public IChess.ChessKingState kingState(IChess.ChessColor color) {
        IChess.ChessPosition kingPosition = new IChess.ChessPosition();
        for (Piece piece : pieceList) {
            if (piece.getType().equals(IChess.ChessType.TYP_KING) && piece.getColor().equals(color)) {
                kingPosition = piece.getPosition();
                break;
            }

        }
        for (Piece piece : pieceList) {
            if (piece != null) {
                if (piece.getColor() != color) {
                    List<IChess.ChessPosition> enemyList = piece.getPossibleMoves(this);
                    for (IChess.ChessPosition enemyPosition : enemyList) {
                        if (enemyPosition.equals(kingPosition)) {
                            return IChess.ChessKingState.KING_THREATEN;
                        }
                    }
                }
            }
        }
        return IChess.ChessKingState.KING_SAFE;

    }

    /**
     * check the possibles moves of a piece (delete a move if the king is in check when you do it)
     * @param p position of the piece
     * @param possibleMoves all possible moves before checking
     * @return the list of possible move without the king to be in check
     */
    public List<IChess.ChessPosition> checkMoves(IChess.ChessPosition p, List<IChess.ChessPosition> possibleMoves) {
        List<IChess.ChessPosition> realMoves = new ArrayList<IChess.ChessPosition>();
        for (IChess.ChessPosition position : possibleMoves) {
            try {
                movePiece(p, position, true);
                realMoves.add(position);
            } catch (ChessException e) {
            }
        }
        return realMoves;

    }

    /**
     * add a pieces to the board
     * @param piece piece to be added to the board
     */
    public void addPieces(Piece piece) {
        pieceList.add(piece);
    }
}
