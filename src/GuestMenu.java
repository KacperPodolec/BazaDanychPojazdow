import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuestMenu extends JFrame {
    private JPanel panelGuestMenu;
    private JTable tablePojazdy;
    private JButton wyjscieButton;
    private JButton wsteczButton;
    private JRadioButton wszystkiePojazdyRadioButton;
    private JRadioButton pojazdyOsoboweRadioButton;
    private JRadioButton motoryRadioButton;
    private DefaultTableModel tableModel;
    private int width = 500, height = 500;

    public GuestMenu() {
        super("Baza pojazdów");
        this.setContentPane(this.panelGuestMenu);
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
                SelectUser selectUser = new SelectUser();
                selectUser.setVisible(true);
            }
        });

        wyjscieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
