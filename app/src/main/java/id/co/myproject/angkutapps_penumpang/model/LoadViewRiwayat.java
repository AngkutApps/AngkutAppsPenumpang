package id.co.myproject.angkutapps_penumpang.model;

public class LoadViewRiwayat {

    String harga, rute_perjalanan, hari, tanggal;

    public LoadViewRiwayat(String harga, String rute_perjalanan, String hari, String tanggal) {
        this.harga = harga;
        this.rute_perjalanan = rute_perjalanan;
        this.hari = hari;
        this.tanggal = tanggal;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getRute_perjalanan() {
        return rute_perjalanan;
    }

    public void setRute_perjalanan(String rute_perjalanan) {
        this.rute_perjalanan = rute_perjalanan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
