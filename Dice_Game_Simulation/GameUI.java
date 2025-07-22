
package Dice_Game_Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameUI extends JFrame {
    private JTextArea outputArea;
    private JButton playButton, autoPlayButton;
    private GameEngine engine;
    private ArrayList<Player> players;
    private JLabel diceImageLabel;
    private ImageIcon[] diceIcons;
    private  int roundCounter = 0;

    public GameUI() {
        setTitle("Dice Game Simulation");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        playButton = new JButton("Play Round");
        autoPlayButton = new JButton("Auto Play (5 Rounds)");
        bottomPanel.add(playButton);
        bottomPanel.add(autoPlayButton);
        add(bottomPanel, BorderLayout.SOUTH);

        diceImageLabel = new JLabel();
        diceImageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(diceImageLabel, BorderLayout.NORTH);

        loadDiceImages();
        setupPlayers();

        playButton.addActionListener(e -> playRoundAndDisplay());
        autoPlayButton.addActionListener(e -> autoPlayRounds(5));

        setVisible(true);
    }

    private void loadDiceImages() {
        diceIcons = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            diceIcons[i] = new ImageIcon(getClass().getResource("/Dice_Game_Simulation/images/dice" + (i + 1) + ".png"));
        }
    }

    private void setupPlayers() {
        int n = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter number of players:"));
        players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = JOptionPane.showInputDialog(this, "Enter name of Player " + (i + 1) + ":");
            players.add(new Player(name));
        }

        engine = new GameEngine(players);
        outputArea.append("Game initialized with " + n + " players.\n");
        for (Player p : players) {
            outputArea.append(p.showName() + " joined the game.\n");
        }
    }

    private void playRoundAndDisplay() {
        roundCounter ++;
        int highest = 0;
        Player winner = null;
        outputArea.append("\n--- Round "+roundCounter + "---\n");

        for (Player p : players) {
            int roll = new Dice().roll();
            outputArea.append(p.showName() + " rolled a " + roll + "\n");

            if (roll > highest) {
                highest = roll;
                winner = p;
            } else if (roll == highest) {
                winner = null; // tie
            }

            diceImageLabel.setIcon(diceIcons[roll - 1]);
            try{
                Thread.sleep(500);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if (winner != null) {
            winner.incrementWin();
            outputArea.append("=> Winner of Round "+roundCounter+": " +winner.showName() + " (Rolled " + highest + ")\n");
        } else {
            outputArea.append("=> Round "+roundCounter+": It's a tie! No winner this round.\n");
        }

        outputArea.append("\nCurrent Wins:\n");
        for (Player p : players) {
            outputArea.append(p.showName() + ": " + p.showWins() + " wins\n");
        }
    }

    private void autoPlayRounds(int rounds) {
        for (int i = 0; i < rounds; i++) {
            playRoundAndDisplay();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }
}
