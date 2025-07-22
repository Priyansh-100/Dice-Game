package Dice_Game_Simulation;

import java.util.ArrayList;
import java.util.Scanner;

public class Executor {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Give the number of players : ");
        int n = sc.nextInt();
        ArrayList<Player>list = new ArrayList<>();
        for (int i = 0;i<n;i++){
            System.out.println("Player "+(i+1)+" name");
            String name = sc.next();
            list.add(new Player(name));
        }

        GameEngine ge = new GameEngine(list);

        while (true){
            System.out.println("you want to play the game ?(1/0) :");
            int ip = sc.nextInt();
            if(ip == 1){
                ge.playRound();
                ge.showResults();
            }else return;
        }
    }
}
