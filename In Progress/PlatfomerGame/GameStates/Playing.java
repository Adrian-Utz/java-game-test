package PlatfomerGame.GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import PlatfomerGame.Game;
import PlatfomerGame.Levels.LevelManaging;
import PlatfomerGame.UI.PausedOverlay;
import PlatfomerGame.entinys.Player;
import PlatfomerGame.utils.SaveandLoad;

public class Playing extends States implements gameStateMethods{
    private Player player;
    private LevelManaging levelManaging;
    private PausedOverlay pausedOverlay;
    private boolean Paused = false;
    private int lvlxview;
    private int lvlyview;
    private int LeftBorder = (int)(0.4 * Game.GAME_WIDTH);
    private int RightBorder = (int)(0.6 * Game.GAME_WIDTH);
    private int TopBorder = (int)(0.4 * Game.GAME_HEIGHT);
    private int BottomBorder = (int)(0.5 * Game.GAME_HEIGHT);
    private int maxlvlWidth = SaveandLoad.RecieveLevelData()[0].length;
    private int maxlvlHeight = SaveandLoad.RecieveLevelData().length;
    private int maxSpriteXOffset = maxlvlWidth - Game.NUMBER_TILES_WIDTH;
    private int maxSpriteWidthOffset = maxSpriteXOffset * Game.TILES_SIZE;
    private int maxSpriteYOffset = maxlvlHeight - Game.NUMBER_TILES_HEIGHT;
    private int maxSpriteHeightOffset = maxSpriteYOffset * Game.TILES_SIZE;
 
    public Playing(Game game) {
        super(game);
        initialClasses();
    }

    private void initialClasses() {
        levelManaging = new LevelManaging(game);
        player = new Player(200, 500, (int)(32 * Game.SCALE),(int)(32 * Game.SCALE));
        player.loadLvlData(levelManaging.getCurrentLevel().getLvlData());
        pausedOverlay = new PausedOverlay(this);
    }

    public Player getPlayer(){
        return player;
    }

    public void lostWindowFocus() {
        player.resetDirectionBools();
    }

    public void UnpauseGame(){
        Paused = false;
    }

    @Override
    public void update() {
        if(!Paused){
            levelManaging.update();
            player.update();
            playerPositionIRTborder();
        }else{
            pausedOverlay.update();
        } 
    }

    private void playerPositionIRTborder() {
        int playerX = (int)player.gethitbox().x;
        int playerY = (int)player.gethitbox().y;
        int xDiff = playerX - lvlxview;
        int yDiff = playerY - lvlyview;

        //Border X position 
        if(xDiff > RightBorder){
            lvlxview += xDiff - RightBorder;
        }else if (xDiff < LeftBorder){
            lvlxview += xDiff - LeftBorder;
        }

        //Border Y position
        if(yDiff > BottomBorder){
            lvlyview += yDiff - BottomBorder;
        }else if(yDiff < TopBorder){
            lvlyview += yDiff - TopBorder;
        }

        //Clamp the view within the lvl boundaries
        if(lvlxview > maxSpriteWidthOffset){
            lvlxview = maxSpriteWidthOffset;
        }else if(lvlxview < 0){
            lvlxview = 0;
        }

        if(lvlyview > maxSpriteHeightOffset){
            lvlyview = maxSpriteHeightOffset;
        }else if(lvlyview < 0){
            lvlyview = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManaging.draw(g, lvlxview, lvlyview);
        player.render(g, lvlxview, lvlyview);
        
        if(Paused){
            pausedOverlay.draw(g);
        }
    }

    public void mouseDragged(MouseEvent e){
        if(Paused){
            pausedOverlay.mouseDragged(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.sideAttack(true);
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            
            player.downAttack(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(Paused){
            pausedOverlay.mousePressed(e);
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(Paused){
            pausedOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(Paused){
            pausedOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                Paused = !Paused;
                break;
            case KeyEvent.VK_CONTROL:
                if(!player.isRunning()){
                    player.playerRunning(true);
                }else{
                    player.playerRunning(false);
                }
                break;
        }    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }    
    }
}
