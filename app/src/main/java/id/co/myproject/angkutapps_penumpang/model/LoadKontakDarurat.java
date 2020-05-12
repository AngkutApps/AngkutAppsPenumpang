package id.co.myproject.angkutapps_penumpang.model;

public class LoadKontakDarurat {

    private String nama, hubungan, nomor_telepon;

    public LoadKontakDarurat(String nama, String hubungan, String nomor_telepon) {
        this.nama = nama;
        this.hubungan = hubungan;
        this.nomor_telepon = nomor_telepon;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public String getNomor_telepon() {
        return nomor_telepon;
    }

    public void setNomor_telepon(String nomor_telepon) {
        this.nomor_telepon = nomor_telepon;
    }
}
