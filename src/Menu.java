import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel panelMenu;
    private JButton przegladajButton;
    private JButton usunPojazdButton;
    private JButton dodajPojazdButton;
    private JButton wyjscieButton;
    private JButton wylogujButton;
    private int width = 500, height = 500;

    public Menu() {
        super("Menu");
        this.setContentPane(this.panelMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        wylogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });

        wyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        przegladajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BazaPojazdow bazaPojazdow = new BazaPojazdow();
                bazaPojazdow.setVisible(true);
            }
        });

        dodajPojazdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DodajPojazd dodajPojazd = new DodajPojazd();
                dodajPojazd.setVisible(true);
            }
        });

        usunPojazdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                UsunPojazd usunPojazd = new UsunPojazd();
                usunPojazd.setVisible(true);
            }
        });
    }
}
