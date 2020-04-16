package id.co.myproject.angkutapps_penumpang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("value")
    @Expose
    private int value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("id_penumpang")
    @Expose
    private String idPenumpang;

    @SerializedName("id_usulan")
    @Expose
    private int idUsulan;


    public Value(int value, String message, String idPenumpang, int idUsulan) {
        this.value = value;
        this.message = message;
        this.idPenumpang = idPenumpang;
        this.idUsulan = idUsulan;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIdPenumpang() {
        return idPenumpang;
    }

    public void setIdPenumpang(String idPenumpang) {
        this.idPenumpang = idPenumpang;
    }

    public int getIdUsulan() {
        return idUsulan;
    }

    public void setIdUsulan(int idUsulan) {
        this.idUsulan = idUsulan;
    }
}
