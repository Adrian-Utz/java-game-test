package PlatfomerGame.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import PlatfomerGame.Game;

public class SaveandLoad {
    //player images
    public static final String PLAYER_ATLAS = "res/Running_guy.png";
    public static final String PLAYER_KNIFE = "res/Throwing-Knives.png";

    //level 1 images
    public static final String LEVEL_ATLAS = "res/Platform-1.png";
    public static final String LEVEL_ONE_LONGER = "res/Level-1-longer.png";
    public static final String LEVEL_ONE_BACKGROUND = "res/TestBackground1.png";

    //level 2 images

    //enemy images

    //world entity images
    public static final String CHEST_SPRITE = "res/Chest_Sprite.png";
    
    //paused overlay images
    public static final String MENU_BUTTONS = "res/MenuButtons.png";
    public static final String MENU_BACKGROUND = "res/MenuBackground.png";
    public static final String PAUSED_BACKGROUND = "res/PausedBackground.png";
    public static final String PAUSED_BUTTONS = "res/PausedButtons.png";
    public static final String PAUSED_SOUND_BUTTONS = "res/SoundVolume.png";
    public static final String PAUSED_SOUND_SLIDER = "res/VolumeSlider.png";
    public static final String UNPAUSE_REDO_HOME_BUTTONS = "res/OptionsButtons.png";

    public static BufferedImage GetSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream is = SaveandLoad.class.getResourceAsStream("/" + fileName);
    
        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
    public static int[][] RecieveLevelData(){

        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_LONGER);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Color color = new Color(img.getRGB(x, y));
                int value = color.getRed();
                //System.out.println("x: " + x + ", y: " + y + ", value: " + value);
                if(value >= 15){
                    switch(value){
                        case 59:
                            lvlData[y][x] = 0;
                            break;
                        case 100:
                            lvlData[y][x] = 1;
                            break;
                        case 89:
                            lvlData[y][x] = 2;
                            break;
                        case 77:
                            lvlData[y][x] = 3;
                            break;
                        case 36:
                            lvlData[y][x] = 4;
                            break;
                        case 132:
                            lvlData[y][x] = 5;
                            break;
                        case 151:
                            lvlData[y][x] = 6;
                            break;
                        case 94:
                            lvlData[y][x] = 7;
                            break;
                        case 116:
                            lvlData[y][x] = 8;
                            break;
                        case 208:
                            lvlData[y][x] = 10;
                            break;
                        case 189:
                            lvlData[y][x] = 11;
                            break;
                        case 66:
                            lvlData[y][x] = 12;
                            break;
                        case 76:
                            lvlData[y][x] = 13;
                            break;
                        default:
                            lvlData[y][x] = 9;
                            break;
                    }
                }else{
                    lvlData[y][x] = 9;
                }
            }
        }
        return lvlData;
    }
}

