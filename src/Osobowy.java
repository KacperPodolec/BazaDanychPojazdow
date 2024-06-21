public class Osobowy extends Pojazd {
    private int liczbaMiejsc;
    private boolean klimatyzacja;

    public Osobowy(long numer_vin, String marka, String model, String typ, int liczbaMiejsc, boolean klimatyzacja) {
        super(numer_vin, marka, model, typ);
        this.liczbaMiejsc = liczbaMiejsc;
        this.klimatyzacja = klimatyzacja;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }

    public void setLiczbaMiejsc(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }

    public boolean isKlimatyzacja() {
        return klimatyzacja;
    }

    public void setKlimatyzacja(boolean klimatyzacja) {
        this.klimatyzacja = klimatyzacja;
    }
}
