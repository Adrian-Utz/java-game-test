package PlatfomerGame.utils;

import PlatfomerGame.Game;

public class Constants {

    public static class UI{
        
        public static class MenuButtonDimentions {
            public static final int BUTTON_DEFAULT_WIDTH = 64;
            public static final int BUTTON_DEFAULT_HEIGHT = 32;
            public static final int BUTTON_WIDTH = (int)(64 * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int)(32 * Game.SCALE);
        }

        public static class SoundButtonDimentions{
            public static final int SOUND_BUTTON_SIZE = (int)(32 * Game.SCALE);
            public static final int SOUND_BUTTON_DEFAULT_SIZE = 32;
        }

        public static class UnpauseMenuHomeButtons{
            public static final int UMH_BUTTON_DEFAULT_SIZE = 32;
            public static final int UMH_BUTTON_SIZE = (int)(UMH_BUTTON_DEFAULT_SIZE * Game.SCALE);
        }

        public static class PausedSlider{
            public static final int VOLUME_DEFAULT_WIDTH = 16;
            public static final int VOLUME_DEFAULT_HEIGHT = 16;
            public static final int SLIDER_DEFAULT_WIDTH = 160;
            
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
        }
    }
    
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int JUMP = 1;
        public static final int RUNNING = 2;
        public static final int ATTACK_GROUND = 3;
        public static final int ATTACK_AIR = 4;
        public static final int DAMAGE = 5;
        public static final int FALLING = 6;

        public static int RecieveSpriteAmnt(int player_action){

            switch(player_action){
                case RUNNING:
                    return 6;
                case IDLE:
                case JUMP:
                    return 4;
                case ATTACK_AIR:
                    return 3;
                case ATTACK_GROUND:
                    return 5;
                case DAMAGE:
                case FALLING:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
