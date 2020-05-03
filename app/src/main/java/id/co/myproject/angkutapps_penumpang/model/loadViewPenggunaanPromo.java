package id.co.myproject.angkutapps_penumpang.model;

public class loadViewPenggunaanPromo {

    String tvNamaPromo, tvHariPromo, tvTanggalPromo;

    public loadViewPenggunaanPromo(String tvNamaPromo, String tvHariPromo, String tvTanggalPromo) {
        this.tvNamaPromo = tvNamaPromo;
        this.tvHariPromo = tvHariPromo;
        this.tvTanggalPromo = tvTanggalPromo;
    }

    public String getTvNamaPromo() {
        return tvNamaPromo;
    }

    public void setTvNamaPromo(String tvNamaPromo) {
        this.tvNamaPromo = tvNamaPromo;
    }

    public String getTvHariPromo() {
        return tvHariPromo;
    }

    public void setTvHariPromo(String tvHariPromo) {
        this.tvHariPromo = tvHariPromo;
    }

    public String getTvTanggalPromo() {
        return tvTanggalPromo;
    }

    public void setTvTanggalPromo(String tvTanggalPromo) {
        this.tvTanggalPromo = tvTanggalPromo;
    }
}
