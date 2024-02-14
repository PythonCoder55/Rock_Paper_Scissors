import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame implements ActionListener {
    private JButton rockButton, paperButton, scissorsButton, quitButton;
    private JLabel playerWinsLabel, computerWinsLabel, tiesLabel;
    private JTextArea resultsTextArea;
    private int playerWins, computerWins, ties;
    private Random random;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        // center frame in screen
        setSize((screenWidth / 4) , (screenHeight / 4)*2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Pick an action"));
        Font font = new Font("Arial", Font.BOLD, 20);
        rockButton = new JButton("Rock", new ImageIcon("src/rock.png"));
        paperButton = new JButton("Paper", new ImageIcon("src/paper.png"));
        scissorsButton = new JButton("Scissors", new ImageIcon("src/scissors.png"));
        quitButton = new JButton("Quit");
        rockButton.setFont(font);
        paperButton.setFont(font);
        scissorsButton.setFont(font);
        quitButton.setFont(font);
        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);
        quitButton.addActionListener(this);
        buttonsPanel.add(rockButton);
        buttonsPanel.add(paperButton);
        buttonsPanel.add(scissorsButton);
        buttonsPanel.add(quitButton);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");
        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultsTextArea = new JTextArea(10, 30);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        resultsPanel.add(scrollPane);

        add(buttonsPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        playerWins = 0;
        computerWins = 0;
        ties = 0;
        random = new Random();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            System.exit(0);
        } else {
            String playerChoice = e.getActionCommand();

            String[] choices = {"Rock", "Paper", "Scissors"};
            String computerChoice = choices[random.nextInt(choices.length)];

            determineWinner(playerChoice, computerChoice);
        }
    }

    private void determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            resultsTextArea.append("Tie\n");
            ties++;
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            String outcome = determineOutcome(playerChoice);
            resultsTextArea.append(outcome + "(Player Wins)\n");
            playerWins++;
        } else {
            String outcome = determineOutcome(computerChoice);
            resultsTextArea.append(outcome + "(Computer Wins)\n");
            computerWins++;
        }
        updateLabels();
    }

    private String determineOutcome(String winningChoice) {
        switch (winningChoice) {
            case "Rock":
                return "Rock breaks Scissors ";
            case "Paper":
                return "Paper covers Rock ";
            case "Scissors":
                return "Scissors cuts Paper ";
            default:
                return "Unknown outcome ";
        }
    }

    private void updateLabels() {
        playerWinsLabel.setText("Player wins: " + playerWins);
        computerWinsLabel.setText("Computer wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

}