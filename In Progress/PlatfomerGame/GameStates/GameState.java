package PlatfomerGame.GameStates;

public enum GameState {
   
    MAINMENU, PLAYING, MENU, OPTIONS;

    public static GameState state = MENU;
    public static GameState optionState = OPTIONS;
    public static GameState mainmenu = MAINMENU;
    public static GameState gamePlaying = PLAYING;

}
