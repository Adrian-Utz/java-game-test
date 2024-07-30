package PlatfomerGame.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import PlatfomerGame.utils.SaveandLoad;
import static PlatfomerGame.utils.Constants.UI.UnpauseMenuHomeButtons.*;

public class UnpauseMenuHomeButtons extends PausedButtons{
    private BufferedImage[] image;
    private int rowIndex;
    private int index;
    private boolean mouseIsOver;
    private boolean mousePressed;

    public UnpauseMenuHomeButtons(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        BufferedImage temp = SaveandLoad.GetSpriteAtlas(SaveandLoad.UNPAUSE_REDO_HOME_BUTTONS);
        image = new BufferedImage[3];
        for(int i = 0; i < image.length; i++){
            image[i] = temp.getSubimage(i * UMH_BUTTON_DEFAULT_SIZE, rowIndex * UMH_BUTTON_DEFAULT_SIZE, UMH_BUTTON_DEFAULT_SIZE, UMH_BUTTON_DEFAULT_SIZE);
        }
    }

    public void update(){
        if(mouseIsOver){
            index = 1;
        }else if(mousePressed){
            index = 2;
        }else {
            index = 0;
        }


    }

    public void draw(Graphics g){
    g.drawImage(image[index], x, y, UMH_BUTTON_SIZE, UMH_BUTTON_SIZE, null);
    

    }
    
    public void resetBooleans(){
        mouseIsOver = false;
        mousePressed = false;
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
