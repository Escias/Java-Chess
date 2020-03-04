package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess;

public class Timer {
    /**
     * whiteTimer:the thmer of the white player
     * blackTimer:the timer of the black player
     */
    private int whiteTimer=0;
    private int blackTimer=0;

    /**
     *
     * @param color the color of the timer you want to get
     * @param isPlaying if the player is playing, the timer increment
     * @return the timer based on the color you give
     */
    public int getTimer(IChess.ChessColor color, boolean isPlaying){
        if(color== IChess.ChessColor.CLR_WHITE){
            if(isPlaying){
                whiteTimer++;
                return whiteTimer*17;
            }
            else{
                return whiteTimer*17;
            }
        }
        else if(color== IChess.ChessColor.CLR_BLACK){
            if(isPlaying){
                blackTimer++;
                return blackTimer*17;
            }
            else{
                return blackTimer*17;
            }
        }
        return 0;
    }
}
