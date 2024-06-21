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

    public long getNumerVin() {s
        return numer_vin;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
