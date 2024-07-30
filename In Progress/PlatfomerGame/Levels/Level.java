package PlatfomerGame.Levels;

public class Level {
    private int[][] lvlData;

    public Level(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public int getspriteidx(int x, int y){
        int index = lvlData[y][x];
        //System.out.println("x: " + x + ", y: " + y + ", index: " + index);

        return index;
    }

    public int[][] getLvlData(){
        return lvlData;
    }
}