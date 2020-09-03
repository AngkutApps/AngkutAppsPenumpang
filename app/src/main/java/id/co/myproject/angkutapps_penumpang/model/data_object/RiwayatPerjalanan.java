package id.co.myproject.angkutapps_penumpang.model.data_object;

import com.sinch.gson.annotations.Expose;
import com.sinch.gson.annotations.SerializedName;

public class RiwayatPerjalanan {

    @SerializedName("kode_driver")
    @Expose
    private String kodeDriver;

    @SerializedName("no_hp")
    @Expose
    private String noHpUser;

    @SerializedName("dari")
    @Expose
    private String dari;

    @SerializedName("tujuan")
    @Expose
    private String tujuan;

    @SerializedName("alamat_dari")
    @Expose
    private String alamatDari;

    @SerializedName("alamat_tujuan")
    @Expose
    private String alamatTujuan;

    @SerializedName("transportasi")
    @Expose
    private String transportasi;

    @SerializedName("p_anak")
    @Expose
    private String penumpangAnak;

    @SerializedName("p_dewasa")
    @Expose
    private String penumpangDewasa;

    @SerializedName("biaya")
    @Expose
    private String biaya;

    @SerializedName("tgl_keberangkatan")
    @Expose
    private String tglKeberangkatan;

    @SerializedName("tgl_sampai")
    @Expose
    private String tglSampai;

    public RiwayatPerjalanan() {
    }

    public RiwayatPerjalanan(String kodeDriver, String noHpUser, String dari, String tujuan, String alamatDari, String alamatTujuan, String transportasi, String penumpangAnak, String penumpangDewasa, String biaya, String tglKeberangkatan, String tglSampai) {
        this.kodeDriver = kodeDriver;
        this.noHpUser = noHpUser;
        this.dari = dari;
        this.tujuan = tujuan;
        this.alamatDari = alamatDari;
        this.alamatTujuan = alamatTujuan;
        this.transportasi = transportasi;
        this.penumpangAnak = penumpangAnak;
        this.penumpangDewasa = penumpangDewasa;
        this.biaya = biaya;
        this.tglKeberangkatan = tglKeberangkatan;
        this.tglSampai = tglSampai;
    }

    public String getKodeDriver() {
        return kodeDriver;
    }

    public void setKodeDriver(String kodeDriver) {
        this.kodeDriver = kodeDriver;
    }

    public String getNoHpUser() {
        return noHpUser;
    }

    public void setNoHpUser(String noHpUser) {
        this.noHpUser = noHpUser;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getAlamatDari() {
        return alamatDari;
    }

    public void setAlamatDari(String alamatDari) {
        this.alamatDari = alamatDari;
    }

    public String getAlamatTujuan() {
        return alamatTujuan;
    }

    public void setAlamatTujuan(String alamatTujuan) {
        this.alamatTujuan = alamatTujuan;
    }

    public String getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(String transportasi) {
        this.transportasi = transportasi;
    }

    public String getPenumpangAnak() {
        return penumpangAnak;
    }

    public void setPenumpangAnak(String penumpangAnak) {
        this.penumpangAnak = penumpangAnak;
    }

    public String getPenumpangDewasa() {
        return penumpangDewasa;
    }

    public void setPenumpangDewasa(String penumpangDewasa) {
        this.penumpangDewasa = penumpangDewasa;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getTglKeberangkatan() {
        return tglKeberangkatan;
    }

    public void setTglKeberangkatan(String tglKeberangkatan) {
        this.tglKeberangkatan = tglKeberangkatan;
    }

    public String getTglSampai() {
        return tglSampai;
    }

    public void setTglSampai(String tglSampai) {
        this.tglSampai = tglSampai;
    }
}
