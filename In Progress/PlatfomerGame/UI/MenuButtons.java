package PlatfomerGame.UI;

import PlatfomerGame.GameStates.GameState;
import PlatfomerGame.utils.Constants;
import PlatfomerGame.utils.SaveandLoad;

import static PlatfomerGame.utils.Constants.UI.MenuButtonDimentions.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuButtons {
    
    private int index;
    private int PosX;
    private int PosY;
    private int rowIndex;
    private int CenterXOffset = BUTTON_WIDTH / 2;
    private boolean mouseIsOver = false;
    private boolean mousePressed = false;
    private Rectangle buttonBounds;



    private GameState state;
    private BufferedImage[] image;

    public MenuButtons(int PosX, int PosY, int rowIndex, GameState state){
        this.PosX = PosX;
        this.PosY = PosY;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
        initializeButtonBoundarys();
    }

    private void initializeButtonBoundarys() {
        buttonBounds = new Rectangle( PosX - CenterXOffset, PosY, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private void loadImages() {
        image = new BufferedImage[3];
        BufferedImage temp = SaveandLoad.GetSpriteAtlas(SaveandLoad.MENU_BUTTONS);

        for(int x = 0; x < image.length; x++){
            image[x] = temp.getSubimage(x * BUTTON_DEFAULT_WIDTH, rowIndex * BUTTON_DEFAULT_HEIGHT, BUTTON_DEFAULT_WIDTH, BUTTON_DEFAULT_HEIGHT); 
        
        
        }
    }

    public void draw(Graphics g){
        g.drawImage(image[index], PosX - CenterXOffset, PosY, BUTTON_WIDTH, BUTTON_HEIGHT, null );
    }

    public void update(){
        index = 0;
        if(mouseIsOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
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

    public Rectangle getButtonBounds() {
        return buttonBounds;
    }

    public void GameStateApplication(){
        GameState.state = state;
    }

    public void resetBooleans(){
        mouseIsOver = false;
        mousePressed = false;
    }

}
