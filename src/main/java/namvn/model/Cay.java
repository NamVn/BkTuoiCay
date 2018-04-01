package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cays")
public class Cay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer macay;
    @NotNull
    @Size(max = 100)

    private String toado;
    @NotNull
    @Size(max = 20)
    private String nhucau;
    @NotNull
    @Size(max = 20)
    private String trangthai;
    @NotNull
    @Size(max = 40)
    private String khuvuc;
    @NotNull
    @Size(max = 50)
    private String truong;

    @OneToMany(mappedBy = "cay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CayPhien> cayPhiens = new HashSet<>();


    public Cay() {
    }

    public Cay(@NotNull @Size(max = 100) String toado) {
        this.toado = toado;
    }

    public Integer getMacay() {
        return macay;
    }

    public void setMacay(Integer macay) {
        this.macay = macay;
    }

    public String getToado() {
        return toado;
    }

    public void setToado(String toado) {
        this.toado = toado;
    }

    public String getNhucau() {
        return nhucau;
    }

    public void setNhucau(String nhucau) {
        this.nhucau = nhucau;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getTruong() {
        return truong;
    }

    public void setTruong(String truong) {
        this.truong = truong;
    }


}
