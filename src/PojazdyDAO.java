import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PojazdyDAO {

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

    public Pojazd znajdzPojazd(long numerVin) {
        String sql = "SELECT * FROM pojazdy LEFT JOIN motor ON pojazdy.numer_vin = motor.numer_vin LEFT JOIN osobowy ON pojazdy.numer_vin = osobowy.numer_vin WHERE pojazdy.numer_vin = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, numerVin);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String marka = rs.getString("marka");
                    String model = rs.getString("model");
                    String typ = rs.getString("typ");

                    if (typ.equalsIgnoreCase("Motor")) {
                        float pojemnoscSilnika = rs.getFloat("pojemnosc_silnika");
                        return new Motor(numerVin, marka, model, typ, pojemnoscSilnika);
                    } else if (typ.equalsIgnoreCase("Osobowy")) {
                        boolean klimatyzacja = rs.getBoolean("klimatyzacja");
                        int iloscMiejsc = rs.getInt("liczba_miejsc");
                        return new Osobowy(numerVin, marka, model, typ, iloscMiejsc, klimatyzacja);
                    } else {
                        return new Pojazd(numerVin, marka, model, typ);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //dodawanie pojazdu
    public void dodajOsobowy(Osobowy osobowy) {
        String sqlPojazd = "INSERT INTO pojazdy (numer_vin, marka, model, typ) VALUES (?, ?, ?, ?)";
        String sqlOsobowy = "INSERT INTO osobowy (numer_vin, liczba_miejsc, klimatyzacja) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPojazd)) {
                pstmt.setLong(1, osobowy.getNumerVin());
                pstmt.setString(2, osobowy.getMarka());
                pstmt.setString(3, osobowy.getModel());
                pstmt.setString(4, osobowy.getTyp());
                pstmt.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sqlOsobowy)) {
                pstmt.setLong(1, osobowy.getNumerVin());
                pstmt.setInt(2, osobowy.getLiczbaMiejsc());
                pstmt.setBoolean(3, osobowy.isKlimatyzacja());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodajMotor(Motor motor) {
        String sqlPojazd = "INSERT INTO pojazdy (numer_vin, marka, model, typ) VALUES (?, ?, ?, ?)";
        String sqlMotor = "INSERT INTO motor (numer_vin, pojemnosc_silnika) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPojazd)) {
                pstmt.setLong(1, motor.getNumerVin());
                pstmt.setString(2, motor.getMarka());
                pstmt.setString(3, motor.getModel());
                pstmt.setString(4, motor.getTyp());
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sqlMotor)) {
                pstmt.setLong(1, motor.getNumerVin());
                pstmt.setFloat(2, motor.getPojemnoscSilnika());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //usuwwanie pojazdu
    public boolean usunPojazd(long numerVin) throws SQLException {
        String sqlUsunOsobowy = "DELETE FROM osobowy WHERE numer_vin = ?";
        String sqlUsunMotor = "DELETE FROM motor WHERE numer_vin = ?";
        String sqlUsunPojazd = "DELETE FROM pojazdy WHERE numer_vin = ?";
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement pstmtOsobowy = conn.prepareStatement(sqlUsunOsobowy)) {
                pstmtOsobowy.setLong(1, numerVin);
                pstmtOsobowy.executeUpdate();
            }

            try (PreparedStatement pstmtMotor = conn.prepareStatement(sqlUsunMotor)) {
                pstmtMotor.setLong(1, numerVin);
                pstmtMotor.executeUpdate();
            }

            try (PreparedStatement pstmtPojazd = conn.prepareStatement(sqlUsunPojazd)) {
                pstmtPojazd.setLong(1, numerVin);
                int rowsAffected = pstmtPojazd.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
