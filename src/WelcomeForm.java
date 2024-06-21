import javax.swing.*;

public class WelcomeForm extends JFrame {
    private JPanel panelWelcome;
    private JProgressBar progressBar1;
    private JLabel lblProszeCzekac;
    private int width = 500, height = 500;

    public WelcomeForm() {
        super("Baza danych pojazdów");
        this.setContentPane(this.panelWelcome);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        progression();
    }

    private void progression() {
        int counter = 0;
        while (counter <= 100) {
            lblProszeCzekac.setText("Proszę czekać...");
            progressBar1.setValue(counter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 5;
        }
        dispose();
        SelectUser selectUser = new SelectUser();
        selectUser.setVisible(true);
    }
}
