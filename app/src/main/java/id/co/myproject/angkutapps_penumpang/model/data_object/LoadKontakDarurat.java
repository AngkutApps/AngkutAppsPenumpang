package id.co.myproject.angkutapps_penumpang.model.data_object;

import java.io.Serializable;

public class LoadKontakDarurat{

    private String nama_kontak;
    private String hubungan_kontak;
    private String nomor_kontak_darurat;

    public LoadKontakDarurat(String nama_kontak, String hubungan_kontak, String nomor_kontak_darurat) {
        this.nama_kontak = nama_kontak;
        this.hubungan_kontak = hubungan_kontak;
        this.nomor_kontak_darurat = nomor_kontak_darurat;
    }

    public String getNama_kontak() {
        return nama_kontak;
    }

    public void setNama_kontak(String nama_kontak) {
        this.nama_kontak = nama_kontak;
    }

    public String getHubungan_kontak() {
        return hubungan_kontak;
    }

    public void setHubungan_kontak(String hubungan_kontak) {
        this.hubungan_kontak = hubungan_kontak;
    }

    public String getNomor_kontak_darurat() {
        return nomor_kontak_darurat;
    }

    public void setNomor_kontak_darurat(String nomor_kontak_darurat) {
        this.nomor_kontak_darurat = nomor_kontak_darurat;
    }
}
