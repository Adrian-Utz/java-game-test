package PlatfomerGame.UI;

import static PlatfomerGame.utils.Constants.UI.SoundButtonDimentions.SOUND_BUTTON_SIZE;
import static PlatfomerGame.utils.Constants.UI.UnpauseMenuHomeButtons.UMH_BUTTON_SIZE;
import static PlatfomerGame.utils.Constants.UI.PausedSlider.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;

import PlatfomerGame.Game;
import PlatfomerGame.GameStates.GameState;
import PlatfomerGame.GameStates.Playing;
import PlatfomerGame.utils.SaveandLoad;

public class PausedOverlay {

    private BufferedImage backgroundImage;
    private int PbackX;
    private int PbackY;
    private int PbackWidth;
    private int PbackHeight;
    private Playing playing;
    private SoundButtons musicButton;
    private SoundButtons sfxButton;
    private UnpauseMenuHomeButtons unpauseButton;
    private UnpauseMenuHomeButtons menuButton;
    private UnpauseMenuHomeButtons homeButton;
    private VolumeSlider volumeSlider;
    
    
    public PausedOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        makeSoundButtons();
        makeUMHButtons();
        makeVolumeSlider();
    }

    private void makeVolumeSlider() {
        int sliderX = (int)(125 * Game.SCALE);
        int sliderY = (int)(158 * Game.SCALE);
        volumeSlider = new VolumeSlider(sliderX, sliderY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void makeUMHButtons() {
        int menuX = (int) (120 * Game.SCALE);
        int homeX = (int) (192 * Game.SCALE);
        int unpauseX = (int) (264 * Game.SCALE);
        int UMHbuttonsY = (int) (175 * Game.SCALE);

        unpauseButton = new UnpauseMenuHomeButtons(unpauseX, UMHbuttonsY, UMH_BUTTON_SIZE, UMH_BUTTON_SIZE, 0);
        menuButton = new UnpauseMenuHomeButtons(menuX, UMHbuttonsY, UMH_BUTTON_SIZE, UMH_BUTTON_SIZE, 1);
        homeButton = new UnpauseMenuHomeButtons(homeX, UMHbuttonsY, UMH_BUTTON_SIZE, UMH_BUTTON_SIZE, 2);

    }

    private void makeSoundButtons() {
        int soundX = (int)(235 * Game.SCALE);
        int soundFXY = (int)(97 * Game.SCALE);
        int musicY = (int)(66 * Game.SCALE);
        musicButton = new SoundButtons(soundX, musicY, SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);
        sfxButton = new SoundButtons(soundX, soundFXY, SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);
    }



    private void loadBackground() {
        backgroundImage = SaveandLoad.GetSpriteAtlas(SaveandLoad.PAUSED_BACKGROUND);
        PbackWidth = (int)(backgroundImage.getWidth() * Game.SCALE);
        PbackHeight = (int)(backgroundImage.getHeight() * Game.SCALE);
        PbackX = Game.GAME_WIDTH / 2 - PbackWidth / 2;
        PbackY = (int)(25 * Game.SCALE);
    }

    public void update(){
        //button updates
        musicButton.update();
        sfxButton.update();

        menuButton.update();
        homeButton.update();
        unpauseButton.update();

        volumeSlider.update();
    }

    public void draw(Graphics g){
        //background
        g.drawImage(backgroundImage, PbackX, PbackY, PbackWidth, PbackHeight,null);

        //buttons
        musicButton.draw(g);
        sfxButton.draw(g);

        menuButton.draw(g);
        homeButton.draw(g);
        unpauseButton.draw(g);

        volumeSlider.draw(g);
    }
  
    public void mousePressed(MouseEvent e) {
        if(inBounds(e, musicButton)){
            musicButton.setMousePressed(true);
        }else if(inBounds(e, sfxButton)){
            sfxButton.setMousePressed(true);
        }else if(inBounds(e, menuButton)){
            menuButton.setMousePressed(true);
        }else if(inBounds(e, homeButton)){
            homeButton.setMousePressed(true);
        }else if(inBounds(e, unpauseButton)){
            unpauseButton.setMousePressed(true);
        }else if(inBounds(e, volumeSlider)){
            volumeSlider.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e){
        if(inBounds(e, musicButton)){
            if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }
        }else if(inBounds(e, sfxButton)){
            if(sfxButton.isMousePressed()){
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        }else if(inBounds(e, menuButton)){
            if(menuButton.isMousePressed()){
                GameState.state = GameState.MENU;
                playing.UnpauseGame();
            }
        }else if(inBounds(e, unpauseButton)){
            if(unpauseButton.isMousePressed()){
                playing.UnpauseGame();
            }
        }else if(inBounds(e, homeButton)){
            if(homeButton.isMousePressed()){
                GameState.state = GameState.MAINMENU;
            }
        }

        musicButton.resetBooleans();
        sfxButton.resetBooleans();
        menuButton.resetBooleans();
        unpauseButton.resetBooleans();
        homeButton.resetBooleans();
        volumeSlider.resetBooleans();
    }

    public void mouseDragged(MouseEvent e){
        if(volumeSlider.isMousePressed()){
            volumeSlider.changeSBX(e.getX());
        }
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseIsOver(false);
        sfxButton.setMouseIsOver(false);

        menuButton.setMouseIsOver(false);
        homeButton.setMouseIsOver(false);
        unpauseButton.setMouseIsOver(false);

        volumeSlider.setMouseIsOver(false);

        if(inBounds(e, musicButton)){
            musicButton.setMouseIsOver(true);
        }else if(inBounds(e, sfxButton)){
            sfxButton.setMouseIsOver(true);
        }else if(inBounds(e, menuButton)){
            menuButton.setMouseIsOver(true);
        }else if(inBounds(e, homeButton)){
            homeButton.setMouseIsOver(true);
        }else if(inBounds(e, unpauseButton)){
            unpauseButton.setMouseIsOver(true);
        }else if(inBounds(e, volumeSlider)){
            volumeSlider.setMouseIsOver(true);
        }
    }


    private boolean inBounds(MouseEvent e, PausedButtons b){
        return b.getBounds().contains(e.getX(), e.getY());
    }
}