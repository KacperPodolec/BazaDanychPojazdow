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
    private DefaultTableModel tableModel;
    private int width = 500, height = 500;

    public GuestMenu() {
        super("Baza pojazdów");
        this.setContentPane(this.panelGuestMenu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Marka", "Model", "Typ"}, 0);
        tablePojazdy.setModel(tableModel);

        wczytajPojazdy();

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

    private void wczytajPojazdy() {
        PojazdyDAO pojazdyDAO = new PojazdyDAO();
        List<Pojazd> pojazdyList = pojazdyDAO.wszystkiePojazdy();

        for (Pojazd pojazd : pojazdyList) {
            tableModel.addRow(new Object[]{pojazd.getNumer_vin(), pojazd.getMarka(), pojazd.getModel(), pojazd.getTyp()});
        }
    }
}
