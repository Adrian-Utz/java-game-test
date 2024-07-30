package PlatfomerGame.utils;


import java.awt.geom.Rectangle2D;

import PlatfomerGame.Game;
import PlatfomerGame.entinys.Player;

public class HelpMethods{

    public static boolean player_Can_move_here(float x, float y, float width, float height, int[][] lvlData){
        if(!solid_Ground(x, y, lvlData)){
            if(!solid_Ground(x + width, y + height, lvlData)){
                if(!solid_Ground(x + width, y, lvlData)){
                    if(!solid_Ground(x, y + height, lvlData)){
                        if(!solid_Ground(x + width, y + 32, lvlData)){
                            if(!solid_Ground(x, y + 32, lvlData)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean solid_Ground(float x, float y, int[][] lvlData){
        int levelmaxWidth = lvlData[0].length * Game.TILES_SIZE;
        int levelmaxHeight = lvlData.length * Game.TILES_SIZE;

        if(x < 0 || x >= levelmaxWidth){
            return true;
        }
        if(y < 0 || y >= levelmaxHeight){
            return true;
        }

        float xidx = x / (Game.DEFAULT_TILE_SIZE * Game.SCALE);
        float yidx = y / (Game.DEFAULT_TILE_SIZE * Game.SCALE);

        int value = lvlData[(int)yidx][(int)xidx];

        if(value >= 10 || value != 9 || value < 0){
            return true;
        }else{
            return false;
        }        
    }

    public static float getEntityPositionXwall(Rectangle2D.Float hitboxRectangle, Float xSpeed){
        int currentTile = (int)hitboxRectangle.x / Game.TILES_SIZE;
        if(xSpeed > 0){
            //Right
            int xTilepos = currentTile * Game.TILES_SIZE;
            int xDifference = (int)(Game.TILES_SIZE - hitboxRectangle.width);
            return xTilepos + xDifference -1;
        } else{
           //Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityRooforFloorPos(Rectangle2D.Float hitboxRectangle, Float player_air_speed){
        int currentTile = (int)hitboxRectangle.y / Game.TILES_SIZE;
        if(player_air_speed > 0){
            //Falling or touching floor
            int yTilepos = currentTile * Game.TILES_SIZE;
            int yDifference = (int)(Game.TILES_SIZE - hitboxRectangle.height);
            return yTilepos - yDifference  + 11;
        } else{
            //Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean isPlayerOnFloor(Rectangle2D.Float hitboxRectangle, int[][] lvlData){
        // Pixel check
        // Bottom left & bottom right
        if(!solid_Ground(hitboxRectangle.x, hitboxRectangle.y + hitboxRectangle.height + 1, lvlData)){
            if(!solid_Ground(hitboxRectangle.x + hitboxRectangle.width, hitboxRectangle.y + hitboxRectangle.height + 1, lvlData)){
                return false;
            }
        }
        return true;
    }
}