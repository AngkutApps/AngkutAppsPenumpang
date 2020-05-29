package id.co.myproject.angkutapps_penumpang.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("id_user")
    @Expose
    private String idUser;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nama_user")
    @Expose
    private String nama;

    @SerializedName("jenis_kelamin")
    @Expose
    private String jk;

    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("no_hp")
    @Expose
    private String noHp;

    public User() {
    }

    public User(String idUser, String email, String nama, String jk, String foto, String alamat, String noHp) {
        this.idUser = idUser;
        this.email = email;
        this.nama = nama;
        this.jk = jk;
        this.foto = foto;
        this.alamat = alamat;
        this.noHp = noHp;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idUser);
        dest.writeString(this.email);
        dest.writeString(this.nama);
        dest.writeString(this.jk);
        dest.writeString(this.foto);
        dest.writeString(this.alamat);
        dest.writeString(this.noHp);
    }

    protected User(Parcel in) {
        this.idUser = in.readString();
        this.email = in.readString();
        this.nama = in.readString();
        this.jk = in.readString();
        this.foto = in.readString();
        this.alamat = in.readString();
        this.noHp = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
