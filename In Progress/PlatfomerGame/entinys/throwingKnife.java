package PlatfomerGame.entinys;

import PlatfomerGame.Game;

public class throwingKnife extends entity{
    private float kHitboxX = 2 * Game.SCALE;
    private float kHitboxY = 1 * Game.SCALE;
    private float knife_max_distance = 5 * Game.SCALE;

    

    public throwingKnife(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnim();
        intitializeHitbox();

    }


    private void intitializeHitbox() {
    
    }


    private void loadAnim() {

    }
    
}
