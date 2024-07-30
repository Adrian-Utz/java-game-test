package PlatfomerGame.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import static PlatfomerGame.utils.Constants.UI.SoundButtonDimentions.*;
import PlatfomerGame.utils.SaveandLoad;

public class SoundButtons extends PausedButtons{

    private BufferedImage[][] soundButtons;
    private boolean mouseIsOver;
    private boolean mousePressed;
    private boolean muted;
    private int rowIndex;
    private int Terry;

    public SoundButtons(int x, int y, int width, int height) {
        super(x, y, width, height);
        
        soundButtonsLoad();
    }

    private void soundButtonsLoad() {
        BufferedImage temp = SaveandLoad.GetSpriteAtlas(SaveandLoad.PAUSED_SOUND_BUTTONS);
        soundButtons = new BufferedImage[2][3];
        for(int x = 0; x < soundButtons.length; x++){
            for(int y = 0; y < soundButtons[x].length; y++){
                soundButtons[x][y] = temp.getSubimage(y * SOUND_BUTTON_DEFAULT_SIZE, x * SOUND_BUTTON_DEFAULT_SIZE, SOUND_BUTTON_DEFAULT_SIZE, SOUND_BUTTON_DEFAULT_SIZE);
            }
        }
    }

    public void update(){
        if(muted){
            rowIndex = 1;
        }else{
            rowIndex = 0;
        }
        
        Terry = 0;
        if(mouseIsOver){
            Terry = 1;
        }
        if(mousePressed){
            Terry = 2;
        }
    }

    public void resetBooleans(){
        mouseIsOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g){
        g.drawImage(soundButtons[rowIndex][Terry], x, y, width, height,null);
        
    }

    public boolean isMouseIsOver() {
        return mouseIsOver;
    }

    public void setMouseIsOver(boolean mouseIsOver) {
        this.mouseIsOver = mouseIsOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    
    
}
