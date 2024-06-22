public class Motor extends Pojazd {
    private float pojemnoscSilnika;

    public Motor(long numer_vin, String marka, String model, String typ, float pojemnoscSilnika) {
        super(numer_vin, marka, model, typ);
        this.pojemnoscSilnika = pojemnoscSilnika;
    }

    public float getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }
}
