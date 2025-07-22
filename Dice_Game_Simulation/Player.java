package Dice_Game_Simulation;

public class Player {
    private String name;
    private  int wins;
    public Player(String name){
        this.name = name;
    }

    public int showWins(){
        return this.wins;
    }

    public String showName(){
        return this.name;
    }

    public void incrementWin(){
        this.wins++;
    }
}
