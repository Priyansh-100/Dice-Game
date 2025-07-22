package Dice_Game_Simulation;

import java.util.ArrayList;

public class GameEngine {
    ArrayList<Player>arr;
    Dice dice;
    public GameEngine(ArrayList<Player>list){
        arr = list;
        dice = new Dice();
    }

    public void playRound(){
        int highestOut = 0;
        Player winPlayer = null;
        for(Player P : arr){
            int n = dice.roll();
            if (n > highestOut){
                highestOut = n;
                winPlayer = P;
            } else if(n == highestOut) {
                winPlayer = null;
            }
        }
        if (winPlayer != null){
            System.out.println("The player who won this round is "+winPlayer.showName() + " ,the output"+winPlayer.showName() +" got is "+highestOut);
            winPlayer.incrementWin();
        }
    }

    public void showResults(){
        for(Player P:arr){
            System.out.println(P.showName()+" have won "+P.showWins()+" rounds");
        }
    }
}
