package id.co.myproject.angkutapps_penumpang.model.data_object;

public class loadView_rw_perjalanan_user {

    int biaya;
    String transportasi, dari, alamat_dari, tujuan, alamat_tujuan, tgl_berangkat, hari_keberangkatan;
    int penumpang_dewasa, penumpang_anak;
    String tgl_sampai, nama_driver, plat_mobil, merk_mobil, warna_kendaraan;
    int id_pembayaran;

    public loadView_rw_perjalanan_user(int biaya, String transportasi, String dari, String alamat_dari, String tujuan, String alamat_tujuan, String tgl_berangkat, String hari_keberangkatan, int penumpang_dewasa, int penumpang_anak, String tgl_sampai, String nama_driver, String plat_mobil, String merk_mobil, String warna_kendaraan, int id_pembayaran) {
        this.biaya = biaya;
        this.transportasi = transportasi;
        this.dari = dari;
        this.alamat_dari = alamat_dari;
        this.tujuan = tujuan;
        this.alamat_tujuan = alamat_tujuan;
        this.tgl_berangkat = tgl_berangkat;
        this.hari_keberangkatan = hari_keberangkatan;
        this.penumpang_dewasa = penumpang_dewasa;
        this.penumpang_anak = penumpang_anak;
        this.tgl_sampai = tgl_sampai;
        this.nama_driver = nama_driver;
        this.plat_mobil = plat_mobil;
        this.merk_mobil = merk_mobil;
        this.warna_kendaraan = warna_kendaraan;
        this.id_pembayaran = id_pembayaran;
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

    public String getAlamat_dari() {
        return alamat_dari;
    }

    public void setAlamat_dari(String alamat_dari) {
        this.alamat_dari = alamat_dari;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getAlamat_tujuan() {
        return alamat_tujuan;
    }

    public void setAlamat_tujuan(String alamat_tujuan) {
        this.alamat_tujuan = alamat_tujuan;
    }

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }

    public String getHari_keberangkatan() {
        return hari_keberangkatan;
    }

    public void setHari_keberangkatan(String hari_keberangkatan) {
        this.hari_keberangkatan = hari_keberangkatan;
    }

    public int getPenumpang_dewasa() {
        return penumpang_dewasa;
    }

    public void setPenumpang_dewasa(int penumpang_dewasa) {
        this.penumpang_dewasa = penumpang_dewasa;
    }

    public int getPenumpang_anak() {
        return penumpang_anak;
    }

    public void setPenumpang_anak(int penumpang_anak) {
        this.penumpang_anak = penumpang_anak;
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

    public String getPlat_mobil() {
        return plat_mobil;
    }

    public void setPlat_mobil(String plat_mobil) {
        this.plat_mobil = plat_mobil;
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

    public int getId_pembayaran() {
        return id_pembayaran;
    }

    public void setId_pembayaran(int id_pembayaran) {
        this.id_pembayaran = id_pembayaran;
    }
}
