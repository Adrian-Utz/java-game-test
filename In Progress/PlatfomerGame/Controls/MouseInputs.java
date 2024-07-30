package PlatfomerGame.Controls;

import java.awt.event.*;

import PlatfomerGame.GamePanel;
import PlatfomerGame.GameStates.GameState;

public class MouseInputs implements MouseListener, MouseMotionListener{

    private GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch(GameState.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseDragged(e);
                break;
            default:
                break;
        } 
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().mouseClicked(e);
                break;
            case MENU:
                gamePanel.getGame().getGameMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().mouseClicked(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().mousePressed(e);
                break;
            case MENU:
                gamePanel.getGame().getGameMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameState.state){
            case MAINMENU:
                //gamePanel.getGame().getMainMenu().mouseReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getGameMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }
}