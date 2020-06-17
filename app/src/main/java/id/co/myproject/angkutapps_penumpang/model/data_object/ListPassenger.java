package id.co.myproject.angkutapps_penumpang.model.data_object;

public class ListPassenger {
    private String id_destinasi;
    private String id_list;
    private String no_ho_user;
    private String tanggal;

    public ListPassenger() {
    }

    public String getId_destinasi() {
        return id_destinasi;
    }

    public void setId_destinasi(String id_destinasi) {
        this.id_destinasi = id_destinasi;
    }

    public String getId_list() {
        return id_list;
    }

    public void setId_list(String id_list) {
        this.id_list = id_list;
    }

    public String getNo_ho_user() {
        return no_ho_user;
    }

    public void setNo_ho_user(String no_ho_user) {
        this.no_ho_user = no_ho_user;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
