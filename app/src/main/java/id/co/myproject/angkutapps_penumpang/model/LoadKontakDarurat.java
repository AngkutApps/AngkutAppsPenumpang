package id.co.myproject.angkutapps_penumpang.model;

import java.io.Serializable;

public class LoadKontakDarurat{

    private int id;
    private String nama;
    private String hubungan;
    private String nomor_telepon;

    public LoadKontakDarurat(String nama, String hubungan, String nomor_telepon) {
        this.nama = nama;
        this.hubungan = hubungan;
        this.nomor_telepon = nomor_telepon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
