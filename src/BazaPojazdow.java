import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BazaPojazdow extends JFrame {
    private JPanel panelBazaPojazdow;
    private JTable tablePojazdy;
    private JButton wsteczButton;
    private JRadioButton wszystkiePojazdyRadioButton;
    private JRadioButton motoryRadioButton;
    private JRadioButton pojazdyOsoboweRadioButton;
    private JTextArea szczegolyTextArea;
    private DefaultTableModel tableModel;
    private int width = 1000, height = 500;

    public BazaPojazdow() {
        super("Baza pojazdów");
        this.setContentPane(this.panelBazaPojazdow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        szczegolyTextArea.setEditable(false);
        szczegolyTextArea.setSize(10, 50);

        tableModel = new DefaultTableModel(new Object[]{"Numer VIN", "Marka", "Model", "Typ"}, 0);
        tablePojazdy.setModel(tableModel);

        wczytajPojazdy("wszystkie");

        wszystkiePojazdyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wczytajPojazdy("wszystkie");
            }
        });
        pojazdyOsoboweRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wczytajPojazdy("osobowy");
            }
        });
        motoryRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wczytajPojazdy("motor");
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

        tablePojazdy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int wybranyWierz = tablePojazdy.getSelectedRow();
                    if (wybranyWierz != -1) {
                        String numerVinStr = tableModel.getValueAt(wybranyWierz, 0).toString();
                        long numerVin = Long.parseLong(numerVinStr);
                        wyswietlSzczegolyPojazdu(numerVin);
                    }
                }
            }
        });
    }

    private void wyswietlSzczegolyPojazdu(long numerVin) {
        PojazdyDAO pojazdyDAO = new PojazdyDAO();
        Pojazd pojazd = pojazdyDAO.znajdzPojazd(numerVin);

        if (pojazd != null) {
            StringBuilder szczegoly = new StringBuilder();
            szczegoly.append("Numer VIN: ").append(pojazd.getNumerVin()).append("\n");
            szczegoly.append("Marka: ").append(pojazd.getMarka()).append("\n");
            szczegoly.append("Model: ").append(pojazd.getModel()).append("\n");
            szczegoly.append("Typ: ").append(pojazd.getTyp()).append("\n");

            if (pojazd instanceof Motor) {
                float pojemnoscSilnika = ((Motor) pojazd).getPojemnoscSilnika();
                szczegoly.append("Pojemność silnika: ").append(pojemnoscSilnika).append("\n");
            } else if (pojazd instanceof Osobowy) {
                boolean klimatyzacja = ((Osobowy) pojazd).isKlimatyzacja();
                int iloscMiejsc = ((Osobowy) pojazd).getLiczbaMiejsc();
                szczegoly.append("Klimatyzacja: ").append(klimatyzacja ? "Tak" : "Nie").append("\n");
                szczegoly.append("Ilość miejsc: ").append(iloscMiejsc).append("\n");
            }

            szczegolyTextArea.setText(szczegoly.toString());
        } else {
            szczegolyTextArea.setText("Nie znaleziono szczegółowych informacji o pojeździe.");
        }
    }

    private void wczytajPojazdy(String typ) {
        PojazdyDAO pojazdyDAO = new PojazdyDAO();
        List<Pojazd> pojazdyList = pojazdyDAO.wszystkiePojazdy();

        tableModel.setRowCount(0);

        for (Pojazd pojazd : pojazdyList) {
            if (typ.equals("wszystkie") || pojazd.getTyp().equalsIgnoreCase(typ)) {
                tableModel.addRow(new Object[]{pojazd.getNumerVin(), pojazd.getMarka(), pojazd.getModel(), pojazd.getTyp()});
            }
        }
    }
}
