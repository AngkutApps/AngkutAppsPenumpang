package id.co.myproject.angkutapps_penumpang.model;

public class InformasiKeuntungan {

    private int number;
    private String informasi;

    public InformasiKeuntungan(int number, String informasi) {
        this.number = number;
        this.informasi = informasi;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getInformasi() {
        return informasi;
    }

    public void setInformasi(String informasi) {
        this.informasi = informasi;
    }
}
