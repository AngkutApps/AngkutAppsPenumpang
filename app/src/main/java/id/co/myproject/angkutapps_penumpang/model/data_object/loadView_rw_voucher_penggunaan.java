package id.co.myproject.angkutapps_penumpang.model.data_object;

public class loadView_rw_voucher_penggunaan {

//    String nama_voucher, foto_url, deskripsi;
//    int id_penggunaan_voucher;
//    String tgl_penggunaan, hari_penggunaan;
//    int biaya;
//    String transportasi, dari, tujuan, hari_keberangkatan, tgl_berangkat;

    String nama_voucher, foto_url, deskripsi, tgl_penggunaan, hari_penggunaan, transportasi, dari, tujuan, hari_keberangkatan, tgl_berangkat;
    int id_penggunaan_voucher, biaya;

    public loadView_rw_voucher_penggunaan(String nama_voucher, String foto_url, String deskripsi, int id_penggunaan_voucher, String tgl_penggunaan, String hari_penggunaan, int biaya, String transportasi, String dari, String tujuan, String hari_keberangkatan, String tgl_berangkat) {
        this.nama_voucher = nama_voucher;
        this.foto_url = foto_url;
        this.deskripsi = deskripsi;
        this.id_penggunaan_voucher = id_penggunaan_voucher;
        this.tgl_penggunaan = tgl_penggunaan;
        this.hari_penggunaan = hari_penggunaan;
        this.biaya = biaya;
        this.transportasi = transportasi;
        this.dari = dari;
        this.tujuan = tujuan;
        this.hari_keberangkatan = hari_keberangkatan;
        this.tgl_berangkat = tgl_berangkat;
    }

    public String getNama_voucher() {
        return nama_voucher;
    }

    public void setNama_voucher(String nama_voucher) {
        this.nama_voucher = nama_voucher;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getId_penggunaan_voucher() {
        return id_penggunaan_voucher;
    }

    public void setId_penggunaan_voucher(int id_penggunaan_voucher) {
        this.id_penggunaan_voucher = id_penggunaan_voucher;
    }

    public String getTgl_penggunaan() {
        return tgl_penggunaan;
    }

    public void setTgl_penggunaan(String tgl_penggunaan) {
        this.tgl_penggunaan = tgl_penggunaan;
    }

    public String getHari_penggunaan() {
        return hari_penggunaan;
    }

    public void setHari_penggunaan(String hari_penggunaan) {
        this.hari_penggunaan = hari_penggunaan;
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

    public String getHari_keberangkatan() {
        return hari_keberangkatan;
    }

    public void setHari_keberangkatan(String hari_keberangkatan) {
        this.hari_keberangkatan = hari_keberangkatan;
    }

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }
}
