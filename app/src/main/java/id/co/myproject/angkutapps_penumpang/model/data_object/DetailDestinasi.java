package id.co.myproject.angkutapps_penumpang.model.data_object;

public class DetailDestinasi {
    private String idDestinasi;
    private String address;
    private String city;
    private String biaya;
    private String fromLocation;
    private String fromKota;
    private JumlahOrang jumlahOrang;
    private String tgl_keberangkatan;
    private String jumlahBarang;

    public DetailDestinasi() {
    }

    public DetailDestinasi(String idDestinasi, String address, String city, String biaya, String fromLocation, String fromKota, JumlahOrang jumlahOrang, String tgl_keberangkatan, String jumlahBarang) {
        this.idDestinasi = idDestinasi;
        this.address = address;
        this.city = city;
        this.biaya = biaya;
        this.fromLocation = fromLocation;
        this.fromKota = fromKota;
        this.jumlahOrang = jumlahOrang;
        this.tgl_keberangkatan = tgl_keberangkatan;
        this.jumlahBarang = jumlahBarang;
    }

    public String getFromKota() {
        return fromKota;
    }

    public String getTgl_keberangkatan() {
        return tgl_keberangkatan;
    }

    public String getIdDestinasi() {
        return idDestinasi;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getBiaya() {
        return biaya;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public JumlahOrang getJumlahOrang() {
        return jumlahOrang;
    }

    public String getJumlahBarang() {
        return jumlahBarang;
    }
}
