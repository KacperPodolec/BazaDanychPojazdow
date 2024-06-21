import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajPojazd extends JFrame {
    private JPanel panelDodawania;
    private JButton dodajButton;
    private JButton wsteczButton;
    private JTextField markaTextField;
    private JTextField modelTextField;
    private JTextField vinTextField;
    private JTextField liczbaMiejscTextField;
    private JTextField pojemnoscSilnikaTextField;
    private JCheckBox klimatyzacjaCheckBox;
    private JComboBox typComboBox;

    private int width = 500, height = 500;

    public DodajPojazd() {
        super("Dodawanie pojazdu");
        this.setContentPane(this.panelDodawania);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        typComboBox.setSelectedItem("Osobowy");

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
                try {
                    long numerVin = Long.parseLong(vinTextField.getText());
                    String marka = markaTextField.getText();
                    String model = modelTextField.getText();
                    String typ = (String) typComboBox.getSelectedItem();

                    if (typ.equals("Osobowy")) {
                        int liczbaMiejsc = Integer.parseInt(liczbaMiejscTextField.getText());
                        boolean klimatyzacja = klimatyzacjaCheckBox.isSelected();

                        Osobowy osobowy = new Osobowy(numerVin, marka, model, typ, liczbaMiejsc, klimatyzacja);
                        PojazdyDAO pojazdyDAO = new PojazdyDAO();
                        pojazdyDAO.dodajOsobowy(osobowy);
                    } else if (typ.equals("Motor")) {
                        float pojemnoscSilnika = Float.parseFloat(pojemnoscSilnikaTextField.getText());

                        Motor motor = new Motor(numerVin, marka, model, typ, pojemnoscSilnika);
                        PojazdyDAO pojazdyDAO = new PojazdyDAO();
                        pojazdyDAO.dodajMotor(motor);
                    } else {
                        Pojazd pojazd = new Pojazd(numerVin, marka, model, typ);
                        PojazdyDAO pojazdyDAO = new PojazdyDAO();
                    }

                    JOptionPane.showMessageDialog(null,
                            "Pojazd został dodany", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Numer VIN, liczba miejsc i pojemność silnika muszą być liczbami", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        typComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String wybranyTyp = (String) typComboBox.getSelectedItem();
                if ("Osobowy".equals(wybranyTyp)) {
                    liczbaMiejscTextField.setEnabled(true);
                    klimatyzacjaCheckBox.setEnabled(true);
                    pojemnoscSilnikaTextField.setEnabled(false);
                } else if ("Motor".equals(wybranyTyp)) {
                    liczbaMiejscTextField.setEnabled(false);
                    klimatyzacjaCheckBox.setEnabled(false);
                    pojemnoscSilnikaTextField.setEnabled(true);
                } else {
                    liczbaMiejscTextField.setEnabled(false);
                    klimatyzacjaCheckBox.setEnabled(false);
                    pojemnoscSilnikaTextField.setEnabled(false);
                }
            }
        });
    }
}
