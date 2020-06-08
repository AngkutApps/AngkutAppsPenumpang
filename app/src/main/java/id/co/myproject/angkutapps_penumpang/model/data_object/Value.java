package id.co.myproject.angkutapps_penumpang.model.data_object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("value")
    @Expose
    private int value;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("no_hp")
    @Expose
    private String noHpUser;

    @SerializedName("id_usulan")
    @Expose
    private int idUsulan;


    public Value(int value, String message, String noHpUser, int idUsulan) {
        this.value = value;
        this.message = message;
        this.noHpUser = noHpUser;
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

    public String getNoHpUser() {
        return noHpUser;
    }

    public void setNoHpUser(String noHpUser) {
        this.noHpUser = noHpUser;
    }

    public int getIdUsulan() {
        return idUsulan;
    }

    public void setIdUsulan(int idUsulan) {
        this.idUsulan = idUsulan;
    }
}
