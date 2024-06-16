import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PojazdyDAO {

    //usuwwanie pojazdu
    public boolean usunPojazd(long numerVin) throws SQLException {
        String sql = "DELETE FROM pojazdy WHERE numer_vin = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, numerVin);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        }
    }

    //dodawanie pojazdu
    public void dodajPojazd(Pojazd pojazd) {
        String sql = "INSERT INTO pojazdy (numer_vin, marka, model, typ) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, pojazd.getNumer_vin());
            pstmt.setString(2, pojazd.getMarka());
            pstmt.setString(3, pojazd.getModel());
            pstmt.setString(4, pojazd.getTyp());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //wyswietlanie wszystkich pojazdow
    public List<Pojazd> wszystkiePojazdy() {
        List<Pojazd> pojazdy = new ArrayList<>();
        String sql = "SELECT * FROM pojazdy";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                long numer_vin = rs.getLong("numer_vin");
                String marka = rs.getString("marka");
                String model = rs.getString("model");
                String typ = rs.getString("typ");
                pojazdy.add(new Pojazd(numer_vin, marka, model, typ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pojazdy;
    }
}
