import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BazaPojazdow extends JFrame {
    private JPanel panelBazaPojazdow;
    private JTable tablePojazdy;
    private JButton wsteczButton;
    private JRadioButton wszystkiePojazdyRadioButton;
    private JRadioButton motoryRadioButton;
    private JRadioButton pojazdyOsoboweRadioButton;
    private DefaultTableModel tableModel;
    private int width = 500, height = 500;

    public BazaPojazdow() {
        super("Baza pojazdów");
        this.setContentPane(this.panelBazaPojazdow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

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
