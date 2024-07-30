package PlatfomerGame.GameStates;

import java.awt.event.MouseEvent;

import PlatfomerGame.Game;
import PlatfomerGame.UI.MenuButtons;

public class States {
 
    protected Game game;
    public States(Game game){
        this.game = game;
    }

    public boolean isPlayerInButton(MouseEvent e, MenuButtons mbuttons){
        return mbuttons.getButtonBounds().contains(e.getX(), e.getY());
    }

    public Game getGame(){
        return game;
    }
}
