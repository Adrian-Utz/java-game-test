package PlatfomerGame.entinys;

import static PlatfomerGame.utils.Constants.Directions.DOWN;
import static PlatfomerGame.utils.Constants.Directions.LEFT;
import static PlatfomerGame.utils.Constants.Directions.RIGHT;
import static PlatfomerGame.utils.Constants.Directions.UP;
import static PlatfomerGame.utils.Constants.PlayerConstants.ATTACK_GROUND;
import static PlatfomerGame.utils.Constants.PlayerConstants.IDLE;
import static PlatfomerGame.utils.Constants.PlayerConstants.JUMP;
import static PlatfomerGame.utils.Constants.PlayerConstants.RUNNING;
import static PlatfomerGame.utils.Constants.PlayerConstants.RecieveSpriteAmnt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import PlatfomerGame.Game;
import PlatfomerGame.utils.SaveandLoad;
import PlatfomerGame.utils.Constants.PlayerConstants;

import static PlatfomerGame.utils.HelpMethods.*;


public class Player extends entity{

    private BufferedImage[][] animations;
    private int aniTic;
    private int aniSpeed = 15;
    private int aniSpeedJump = 20;
    private int aniSpeedSideAttack = 10;
    private int aniSpeedIdle = 50;
    private int aniSpeedDownAttack = 20;
    private int aniSpeedRunning = 10;
    private int aniIndex;
    private int player_action = PlayerConstants.IDLE;
    private int player_direction = -1;
    private int[][] lvlData;
    private boolean isMoving = false;
    private boolean isRunning = false; 
    private boolean isAttackingSide = false;
    private boolean isAttackingDown = false;
    private boolean isJumping = false;
    private boolean left;
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean inAir = false; 
    private float player_speed = (Game.SCALE / 2);
    private float player_running_speed = (Game.SCALE / 4);
    private float player_air_speed = 0f;
    private float player_gravity = 0.026f * Game.SCALE;
    private float player_jump_speed = -2.0f * Game.SCALE;
    private float player_air_speed_after_coll = 0.2f * Game.SCALE;
    private float player_hitbox_x = 8 * Game.SCALE;
    private float player_hitbox_y = 2 * Game.SCALE;


    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnim();
        hitboxInitialize(x, y, (int)(8 * Game.SCALE), (int)(22 * Game.SCALE));
        
    }

    public void update(){
        positionUpdate();
        updateAni();
        setAni();
        //hitboxUpdate();
    }

    public void render(Graphics g, int lvlxview, int lvlyview){
        g.drawImage(animations[player_action][aniIndex], (int)(hitboxRectangle.x - player_hitbox_x) - lvlxview, (int)(hitboxRectangle.y - player_hitbox_y) - lvlyview, (int)(25 * Game.SCALE), (int)(25 * Game.SCALE), null);
        //hitboxDraw(g); // Here is the hitbox for the player
    }

    private void updateAni(){
      aniTic++;
      int currentAniSpeed = aniSpeed; // Default

      switch(player_action){
            case PlayerConstants.IDLE:
                currentAniSpeed = aniSpeedIdle;
                break;
            case PlayerConstants.JUMP:
                currentAniSpeed = aniSpeedJump;
                break;
            case PlayerConstants.ATTACK_GROUND:
                currentAniSpeed = aniSpeedSideAttack;
                break;
            case PlayerConstants.ATTACK_AIR:
                currentAniSpeed = aniSpeedDownAttack;
                break;
            case PlayerConstants.RUNNING:
                if(isRunning){
                    currentAniSpeed = aniSpeedRunning;
                }
            default:
                break;
        } 

        if(aniTic >= currentAniSpeed){
            aniTic = 0;
            aniIndex++;
            if(aniIndex >= PlayerConstants.RecieveSpriteAmnt(player_action)){
                aniIndex = 0;
                isAttackingSide = false;
            }
        }
        
    }

    private void setAni(){

        int animationStart = player_action;


        if(isMoving && !inAir){
            player_action = PlayerConstants.RUNNING;
            
        } else {
            player_action = PlayerConstants.IDLE;
        }
        
        if(inAir && isMoving && player_air_speed > 0){
            player_action = PlayerConstants.FALLING;
        }

        if(isAttackingSide){
            player_action = PlayerConstants.ATTACK_GROUND;

        }

        if(isJumping){
            player_action = PlayerConstants.JUMP;
        }
        
        if(isAttackingDown && inAir){
            player_action = PlayerConstants.ATTACK_AIR;
        }

        if(animationStart != player_action){
            resetAniTic();
        }
    }

    private void resetAniTic(){
        aniTic = 0;
        aniIndex = 0;
    }

    private void positionUpdate() {

        float xSpeed = 0;
        isMoving = false;
        
        if(isJumping){
            jump();
        }

        if(!left && !right && !inAir){
            return;
        }
        
        if(left){
            xSpeed -= player_speed; 
        }
        
        if(right){
            xSpeed += player_speed;
        }
        
        if(left && isRunning && !inAir){
            xSpeed -= player_running_speed;
        }
        
        if(right && isRunning && !inAir){
            xSpeed += player_running_speed;
        }
        
        if(!inAir){
            if(!isPlayerOnFloor(hitboxRectangle, lvlData)){
                inAir = true;
            }
        }

        if(inAir){

            if(player_Can_move_here(hitboxRectangle.x, hitboxRectangle.y + player_air_speed, hitboxRectangle.width, hitboxRectangle.height, lvlData)){
                hitboxRectangle.y += player_air_speed;
                player_air_speed += player_gravity;
                xPositionUpdate(xSpeed);
            }else{
                hitboxRectangle.y = getEntityRooforFloorPos(hitboxRectangle, player_air_speed);
                if(player_air_speed > 0){
                    inAirReset();
                } else {
                    player_air_speed = player_air_speed_after_coll;
                }
                xPositionUpdate(xSpeed);
            }
        }else{
            xPositionUpdate(xSpeed);
        }
        isMoving = true;
    }

    private void xPositionUpdate(float xSpeed) {
        
        if(player_Can_move_here(hitboxRectangle.x + xSpeed, hitboxRectangle.y, hitboxRectangle.width, hitboxRectangle.height, lvlData)){
            hitboxRectangle.x += xSpeed;
            
        } else {
            hitboxRectangle.x = getEntityPositionXwall(hitboxRectangle, xSpeed);
        }
    }

    private void loadAnim(){

            BufferedImage img = SaveandLoad.GetSpriteAtlas(SaveandLoad.PLAYER_ATLAS);

            animations = new BufferedImage[8][6];

            for(int j = 0; j < animations.length; j++){
                for(int i = 0; i < animations[j].length; i++){
                    animations[j][i] = img.getSubimage( i * 32, j * 32, 32,32);
                }
            }
    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
        if(!isPlayerOnFloor(hitboxRectangle, lvlData)){
            inAir = true;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }   

    public boolean isRunning(){
        return isRunning;
    }

    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        player_air_speed = player_jump_speed;
    }

    public void setJump(boolean isJumping){
        this.isJumping = isJumping;
    }

    public void resetDirectionBools() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    private void inAirReset() {
        inAir = false;
        player_air_speed = 0;
    }

    public void sideAttack(boolean isAttackingSide){
        this.isAttackingSide = isAttackingSide;
    }

    public void downAttack(boolean isAttackingDown){
        this.isAttackingDown = isAttackingDown;
    }

    public void playerRunning(boolean isRunning){
        this.isRunning = isRunning;
    }

}