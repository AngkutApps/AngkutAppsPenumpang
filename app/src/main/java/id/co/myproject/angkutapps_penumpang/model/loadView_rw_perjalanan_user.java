package id.co.myproject.angkutapps_penumpang.model;

public class loadView_rw_perjalanan_user {

    int biaya;
    String transportasi, dari, tujuan, hari, tgl_berangkat;
    int p_dewasa, p_anak;
    String tgl_sampai, nama_driver, nomor_plat, merk_mobil, warna_kendaraan;
    int id;

    public loadView_rw_perjalanan_user(int biaya, String transportasi, String dari, String tujuan, String hari, String tgl_berangkat, int p_dewasa, int p_anak, String tgl_sampai, String nama_driver, String nomor_plat, String merk_mobil, String warna_kendaraan, int id) {
        this.biaya = biaya;
        this.transportasi = transportasi;
        this.dari = dari;
        this.tujuan = tujuan;
        this.hari = hari;
        this.tgl_berangkat = tgl_berangkat;
        this.p_dewasa = p_dewasa;
        this.p_anak = p_anak;
        this.tgl_sampai = tgl_sampai;
        this.nama_driver = nama_driver;
        this.nomor_plat = nomor_plat;
        this.merk_mobil = merk_mobil;
        this.warna_kendaraan = warna_kendaraan;
        this.id = id;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public String getTransportasi() {
        return transportasi;
    }

    public void setTransportasi(String transportasi) {
        this.transportasi = transportasi;
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

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }

    public int getP_dewasa() {
        return p_dewasa;
    }

    public void setP_dewasa(int p_dewasa) {
        this.p_dewasa = p_dewasa;
    }

    public int getP_anak() {
        return p_anak;
    }

    public void setP_anak(int p_anak) {
        this.p_anak = p_anak;
    }

    public String getTgl_sampai() {
        return tgl_sampai;
    }

    public void setTgl_sampai(String tgl_sampai) {
        this.tgl_sampai = tgl_sampai;
    }

    public String getNama_driver() {
        return nama_driver;
    }

    public void setNama_driver(String nama_driver) {
        this.nama_driver = nama_driver;
    }

    public String getNomor_plat() {
        return nomor_plat;
    }

    public void setNomor_plat(String nomor_plat) {
        this.nomor_plat = nomor_plat;
    }

    public String getMerk_mobil() {
        return merk_mobil;
    }

    public void setMerk_mobil(String merk_mobil) {
        this.merk_mobil = merk_mobil;
    }

    public String getWarna_kendaraan() {
        return warna_kendaraan;
    }

    public void setWarna_kendaraan(String warna_kendaraan) {
        this.warna_kendaraan = warna_kendaraan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
