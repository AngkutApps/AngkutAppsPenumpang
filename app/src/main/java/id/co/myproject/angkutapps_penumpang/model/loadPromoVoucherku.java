package id.co.myproject.angkutapps_penumpang.model;

public class loadPromoVoucherku {

    private int img, kondisi_sk;
    private String title, masa_berlaku;

    public loadPromoVoucherku(int img, int kondisi_sk, String title, String masa_berlaku) {
        this.img = img;
        this.kondisi_sk = kondisi_sk;
        this.title = title;
        this.masa_berlaku = masa_berlaku;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getKondisi_sk() {
        return kondisi_sk;
    }

    public void setKondisi_sk(int kondisi_sk) {
        this.kondisi_sk = kondisi_sk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMasa_berlaku() {
        return masa_berlaku;
    }

    public void setMasa_berlaku(String masa_berlaku) {
        this.masa_berlaku = masa_berlaku;
    }
}
