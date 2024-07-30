package PlatfomerGame.Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import PlatfomerGame.Game;
import PlatfomerGame.utils.SaveandLoad;
import PlatfomerGame.Levels.Level;

public class LevelManaging {
    private Game game;
    private BufferedImage[] lvSprite;
    private Level levelone;
    

    public LevelManaging(Game game){
        this.game = game;
        importGroundSprites();
        levelone = new Level(SaveandLoad.RecieveLevelData());
    }

    private void importGroundSprites() {
        BufferedImage img = SaveandLoad.GetSpriteAtlas(SaveandLoad.LEVEL_ATLAS);
        int spriteWidth = 32;
        int spriteHeight = 32;

        lvSprite = new BufferedImage[15];
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 5; x++){
                int index = y * 5 + x;
                lvSprite[index] = img.getSubimage(x * spriteWidth,y * spriteHeight, spriteWidth, spriteHeight);
            }
        }
    }

    public void draw(Graphics g, int lvlxview, int lvlyview){
        //This for loop detects the length and height of the small image
        for(int y = 0; y < levelone.getLvlData().length; y++){
            for(int x = 0; x < levelone.getLvlData()[0].length; x++){
                int index = levelone.getspriteidx(x, y);
                
                //int value = lvSprite[index].getRGB(0, 0) & 0xFF;
                //System.out.println("index: " + index + " | value: " + value);
                if(index >= 0 && index < lvSprite.length){
                    g.drawImage(lvSprite[index], Game.TILES_SIZE * x - lvlxview, Game.TILES_SIZE * y - lvlyview, Game.TILES_SIZE, Game.TILES_SIZE, null);
                } else{
                    System.out.println("Index out of bounds: " + index);
                }
                //g.drawImage(lvSprite[index], Game.TILES_SIZE * x, Game.TILES_SIZE * y, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }

        
    }

    public void update(){

    }

    public Level getCurrentLevel(){
        return levelone;
    }
}