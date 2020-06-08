package id.co.myproject.angkutapps_penumpang.model.data_object;

public class riwayatDana {

    private String media, tanggal, harga, status;

    public riwayatDana(String media, String tanggal, String harga, String status) {
        this.media = media;
        this.tanggal = tanggal;
        this.harga = harga;
        this.status = status;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
