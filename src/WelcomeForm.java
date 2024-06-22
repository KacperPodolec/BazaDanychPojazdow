import javax.swing.*;

public class WelcomeForm extends JFrame {
    private JPanel panelWelcome;
    private JProgressBar progressBar1;
    private int width = 300, height = 200;

    public WelcomeForm() {
        super("Uruchamianie");
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
            progressBar1.setValue(counter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter += 5;
        }
        dispose();
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}
