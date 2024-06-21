import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JPanel panelLogin;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton zalogujSieButton;
    private JButton wyjscieButton;
    private int width = 500, height = 500;

    public LoginForm() {
        super("Okno logowania");
        this.setContentPane(this.panelLogin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        zalogujSieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = usernameTextField.getText();
                String passwordInput = new String(passwordField.getPassword());

                UserVerification userVerification = new UserVerification();
                boolean isAdmin = userVerification.verifyAdminLogin(usernameInput, passwordInput);

                if (isAdmin) {
                    dispose();
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Logowanie powiodło się", "Status logowania", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Logowanie nie powiodło się", "Status logowania", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        wyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
