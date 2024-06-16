import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BazaPojazdow extends JFrame {
    private JPanel panelBazaPojazdow;
    private JTable tablePojazdy;
    private JButton wsteczButton;
    private DefaultTableModel tableModel;
    private int width = 500, height = 500;

    public BazaPojazdow() {
        super("Baza pojazdów");
        this.setContentPane(this.panelBazaPojazdow);
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
                Menu menu = new Menu();
                menu.setVisible(true);
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
