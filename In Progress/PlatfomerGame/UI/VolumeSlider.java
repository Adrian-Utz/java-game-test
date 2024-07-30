package PlatfomerGame.UI;

import static PlatfomerGame.utils.Constants.UI.PausedSlider.SLIDER_DEFAULT_WIDTH;
import static PlatfomerGame.utils.Constants.UI.PausedSlider.VOLUME_DEFAULT_HEIGHT;
import static PlatfomerGame.utils.Constants.UI.PausedSlider.VOLUME_DEFAULT_WIDTH;
import static PlatfomerGame.utils.Constants.UI.PausedSlider.VOLUME_WIDTH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import PlatfomerGame.utils.SaveandLoad;
import PlatfomerGame.utils.Constants.UI.PausedSlider.*;

public class VolumeSlider extends PausedButtons{
    private BufferedImage image;
    private BufferedImage slider;
    private boolean mouseIsOver;
    private boolean mousePressed;
    private int Xbutton;
    private int SBmin;
    private int SBmax;

    public VolumeSlider(int x, int y, int width, int height) {
        super(x + width / 2, y, VOLUME_WIDTH, height);
        bounds.x -= VOLUME_WIDTH / 2;
        Xbutton = x + width / 2;
        this.x = x;
        this.width = width;
        SBmin = x;
        SBmax = x + width;
        loadImages();
    }
    
    private void loadImages() {
        BufferedImage temp = SaveandLoad.GetSpriteAtlas(SaveandLoad.PAUSED_SOUND_SLIDER);
        image = temp.getSubimage(0, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);

        slider = temp.getSubimage(VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
    }

    public void update(){
        
    }

    public void draw(Graphics g){
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(image, Xbutton - VOLUME_WIDTH / 2, y, VOLUME_WIDTH, height, null);

    }
    
    public void resetBooleans(){
        mouseIsOver = false;
        mousePressed = false;
    }

    public void changeSBX(int x){
        if(x < SBmin){
            Xbutton = SBmin;
        }else if(x > SBmax){
            Xbutton = SBmax;
        }else{
            Xbutton = x;
        }
        bounds.x = Xbutton - VOLUME_WIDTH / 2;
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
    

}
