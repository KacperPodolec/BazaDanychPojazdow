public class Pojazd {
    private long numer_vin;
    private String marka;
    private String model;
    private String typ;

    public Pojazd(long numer_vin, String marka, String model, String typ) {
        this.numer_vin = numer_vin;
        this.marka = marka;
        this.model = model;
        this.typ = typ;
    }

    public long getNumer_vin() {
        return numer_vin;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public String getTyp() {
        return typ;
    }
}
