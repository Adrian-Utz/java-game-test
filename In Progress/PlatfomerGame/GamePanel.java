package PlatfomerGame;

import java.awt.*;
import javax.swing.*;

import PlatfomerGame.Controls.KeyBoardInputs;
import PlatfomerGame.Controls.MouseInputs;
import PlatfomerGame.entinys.Player;
import PlatfomerGame.GameStates.Playing;
import static PlatfomerGame.Game.GAME_HEIGHT;
import static PlatfomerGame.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

   
    private MouseInputs mouseInputs;
    private Game game;
    private Playing playing;

    GamePanel(Game game){
        this.game = game;
        this.mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);   
    }

    private void setPanelSize(){
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
        System.out.println("Size: " + GAME_WIDTH + ": " + GAME_HEIGHT);
    }

    public void drawPlayer(Graphics g, Player player){
        player.render(g);
    }

    public void setGame(Game game){
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
