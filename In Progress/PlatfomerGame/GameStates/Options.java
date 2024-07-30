package PlatfomerGame.GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import PlatfomerGame.Game;
import PlatfomerGame.UI.PausedOverlay;

public class Options implements gameStateMethods {
    private PausedOverlay pausedOverlay;
    private boolean Paused = true;

    public Options(Game game){
        super();
        initializeClasses();
    }

    private void initializeClasses() {
        pausedOverlay = new PausedOverlay(null);
    }

    @Override
    public void update() {
        pausedOverlay.update(); 
    }

    @Override
    public void draw(Graphics g) {
        pausedOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Paused){
            pausedOverlay.mousePressed(e);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(Paused){
            pausedOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(Paused){
            pausedOverlay.mouseMoved(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}