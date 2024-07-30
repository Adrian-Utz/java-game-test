package PlatfomerGame.Controls;

import java.awt.event.*;

import PlatfomerGame.GamePanel;
import PlatfomerGame.GameStates.GameState;
import PlatfomerGame.entinys.Player;
import static PlatfomerGame.utils.Constants.Directions.*;

public class KeyBoardInputs implements KeyListener{
    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getGameMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getGameMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}