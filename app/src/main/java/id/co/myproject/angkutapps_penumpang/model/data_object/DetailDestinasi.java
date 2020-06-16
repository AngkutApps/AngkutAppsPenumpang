package id.co.myproject.angkutapps_penumpang.model.data_object;

public class DetailDestinasi {
    private String idDestinasi;
    private String address;
    private String city;
    private String biaya;
    private String fromLocation;
    private JumlahOrang jumlahOrang;
    private String jumlahBarang;

    public DetailDestinasi() {
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
