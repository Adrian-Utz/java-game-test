package PlatfomerGame.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.prefs.BackingStoreException;

import PlatfomerGame.Game;
import PlatfomerGame.UI.MenuButtons;
import PlatfomerGame.utils.SaveandLoad;

public class GameMenu extends States implements gameStateMethods{

    private MenuButtons[] Buttons = new MenuButtons[3];
    private BufferedImage BackgroundImage; 
    private int BackImgX;
    private int BackImgY;
    private int BackImgWidth;
    private int BackImgHeight;

    public GameMenu(Game game) {
        super(game);
        loadButtons();
        loadBackgroundImage();
    }

    private void loadBackgroundImage() {
        BackgroundImage = SaveandLoad.GetSpriteAtlas(SaveandLoad.MENU_BACKGROUND);
        BackImgWidth = (int)(BackgroundImage.getWidth() * Game.SCALE);
        BackImgHeight = (int)(BackgroundImage.getHeight() * Game.SCALE);
        BackImgX = Game.GAME_WIDTH / 2 - BackImgWidth / 2;
        BackImgY = (int)(25 * Game.SCALE);
    }

    public void loadButtons(){
        Buttons[0] = new MenuButtons(Game.GAME_WIDTH / 2, (int)(50 * Game.SCALE), 0, GameState.PLAYING);
        Buttons[1] = new MenuButtons(Game.GAME_WIDTH / 2, (int)(100 * Game.SCALE), 1, GameState.OPTIONS);
        Buttons[2] = new MenuButtons(Game.GAME_WIDTH / 2, (int)(150 * Game.SCALE), 2, GameState.MAINMENU);

    }


    @Override
    public void update() {
        for(MenuButtons mbuttons : Buttons){
            mbuttons.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(BackgroundImage, BackImgX, BackImgY, BackImgWidth, BackImgHeight,null);
        for(MenuButtons mbuttons : Buttons){
            mbuttons.draw(g);
        }
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButtons mbuttons : Buttons){
            if(isPlayerInButton(e, mbuttons)){
                mbuttons.setMousePressed(true);
                break;
            }
        }    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButtons mbuttons : Buttons){
            if(isPlayerInButton(e, mbuttons)){
                if(mbuttons.isMousePressed()){
                    mbuttons.GameStateApplication();
                    break;
                }
            }
        }
        resetButtons();
        
    }

    private void resetButtons() {
        for(MenuButtons mbuttons : Buttons){
            mbuttons.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButtons mbuttons : Buttons){
            mbuttons.setMouseIsOver(false);
        }    

        for(MenuButtons mbuttons : Buttons){
            if(isPlayerInButton(e, mbuttons)){
                mbuttons.setMouseIsOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            GameState.state = GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    

}
