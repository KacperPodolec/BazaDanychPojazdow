import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajPojazd extends JFrame {
    private JPanel panelDodawania;
    private JButton dodajButton;
    private JButton wsteczButton;
    private JTextField markaTextField;
    private JTextField modelTextField;
    private JTextField typTextField;
    private JTextField vinTextField;

    private int width = 500, height = 500;

    public DodajPojazd() {
        super("Dodawanie pojazdu");
        this.setContentPane(this.panelDodawania);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numerVin = vinTextField.getText();
                String marka = markaTextField.getText();
                String model = modelTextField.getText();
                String typ = typTextField.getText();

                if (numerVin.isEmpty() || marka.isEmpty() || model.isEmpty() || typ.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Wszystkie pola muszą być wypełnione", "Błąd dodawania", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        PojazdyDAO pojazdyDAO = new PojazdyDAO();
                        Pojazd pojazd = new Pojazd(Long.parseLong(numerVin), marka, model, typ);
                        pojazdyDAO.dodajPojazd(pojazd);

                        JOptionPane.showMessageDialog(null, "Pojazd został dodany", "Sukces", JOptionPane.INFORMATION_MESSAGE);

                        vinTextField.setText("");
                        markaTextField.setText("");
                        modelTextField.setText("");
                        typTextField.setText("");
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(null, "Numer VIN powinien być liczbą całkowitą", "Bład - VIN", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas dodawania pojazdu", "Błąd dodawania", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
