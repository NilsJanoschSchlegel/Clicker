import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Clicker implements ActionListener {
    JFrame frame = new JFrame();
    JFrame shopFrame = new JFrame();
    JButton clickButton = new JButton();
    JButton shopButton = new JButton();
    JButton x2multi = new JButton();
    JLabel scoreLabel = new JLabel();
    JLabel timepassedLabel = new JLabel();
    JLabel avgCPSlabel = new JLabel();
    Font myFont = new Font("RUBIK", Font.BOLD, 40);
    int score;
    int plusScore;
    int avgCPSint;
    int cps = 0;
    int bought = 1;
    int twoxprice;



    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            cps++;
            update();
        }
    };

    public void clicker() {
        frame.setSize(420, 530);
        frame.setTitle("Clicker");
        frame.getContentPane().setBackground(Color.lightGray);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setAlwaysOnTop(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        timer.scheduleAtFixedRate(task, 1000, 1000);

        plusScore = 1;

        clickButton();
        scoreLabel();
        avgCPS();
        timepassedLabel();
        shop();

        frame.add(timepassedLabel);
        frame.add(scoreLabel);
        frame.add(clickButton);
        frame.add(shopButton);
        frame.add(avgCPSlabel);
    }

    public void clickButton() {
        clickButton.setText("");
        clickButton.setBackground(Color.GRAY);
        clickButton.setBounds(52, 150, 300, 300);
        clickButton.addActionListener(this);
    }

    public void scoreLabel() {
        scoreLabel.setText("Score: " + score);
        scoreLabel.setBounds(52, 30, 300, 50);
        scoreLabel.setFont(myFont);
    }

    public void timepassedLabel() {
        timepassedLabel.setText("Time passed: " + cps);
        timepassedLabel.setFont(new Font("RUBIK", Font.BOLD, 20));
        timepassedLabel.setBounds(52, 80, 300, 20);
    }

    public void avgCPS() {
        avgCPSlabel.setText("Average CPS: " + avgCPSint);
        avgCPSlabel.setFont(new Font("RUBIK", Font.BOLD, 20));
        avgCPSlabel.setBounds(52, 100, 300, 25);
    }

    public void update() {
        avgCPSint = score / cps;
        scoreLabel.setText("Score: " + score);
        timepassedLabel.setText("Time passed: " + cps);
        avgCPSlabel.setText("Average CPS: " + avgCPSint);
        x2multi.setText("2X Multiplier    " + twoxprice);
        twoxprice = bought * bought * 400;
    }

    public void shop() {
        shopButton.setText("Shop");
        shopButton.setBackground(Color.gray);
        shopButton.setBounds(252, 80, 100, 41);
        shopButton.setFont(new Font("RUBIK", Font.BOLD, 20));
        shopButton.setBorderPainted(false);
        shopButton.setBorder(BorderFactory.createRaisedBevelBorder());
        shopButton.setFocusable(false);
        shopButton.addActionListener(this);
    }

    public void shopFrame() {
        shopFrame.setVisible(true);
        shopFrame.setResizable(false);
        shopFrame.setSize(420, 500);
        shopFrame.setAlwaysOnTop(true);
        shopFrame.setTitle("Shop");
        shopFrame.setLayout(null);

        x2multi.setBounds(0, 0, 420, 100 );
        x2multi.setFont(new Font("RUBIK", Font.BOLD, 40));
        x2multi.setText("2X Multiplier    " + twoxprice);
        x2multi.addActionListener(this);

        shopFrame.add(x2multi);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clickButton) {
            score = score + plusScore;
            update();
        }

        if (e.getSource() == shopButton) {
            shopFrame();
        }

        if (e.getSource() == x2multi) {
            if (score > twoxprice) {
                plusScore = plusScore * 2;
                System.out.println(plusScore);
                score = score - twoxprice;
                x2multi.setText("Bought");
                bought = bought + 1;
            } else {
                x2multi.setText("Not enough Score");
            }
        }
    }
}
