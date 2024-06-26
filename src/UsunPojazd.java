import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsunPojazd extends JFrame {
    private JPanel panelUsunPojazd;
    private JTextField vinTextField2;
    private JButton usunButton;
    private JButton wyczyscButton;
    private JButton wsteczButton;
    private int width = 500, height = 500;

    public UsunPojazd() {
        super("Usuwanie pojazdu");
        this.setContentPane(this.panelUsunPojazd);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numerVin = vinTextField2.getText();

                if (numerVin.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Nie podano numeru VIN", "Uwaga", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        PojazdyDAO pojazdyDAO = new PojazdyDAO();
                        boolean success = pojazdyDAO.usunPojazd(Long.parseLong(numerVin));

                        if (success) {
                            JOptionPane.showMessageDialog(null,
                                    "Pojazd o numerze VIN: [" + numerVin + "] został usunięty", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Nie znaleziono pojazdu o numerze VIN: [" + numerVin + "]", "Błąd", JOptionPane.INFORMATION_MESSAGE);
                        }

                        vinTextField2.setText("");

                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null,
                                "Numer VIN powinien być liczbą całkowitą", "Błąd", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null,
                                "Wystąpił błąd podczas usuwania pojazdu", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        wyczyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vinTextField2.setText("");
            }
        });

        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
}
