package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matk;
    @NotNull
    @Size(max = 30)
    @Column(unique = true)
    private String tentk;
    @NotNull
    @Size(max = 20)
    private String  matkhau;
    @NotNull
    @Size(max = 30)
    private String gmail;
    @NotNull
    @Size(max = 20)
    private String sdt;

    public Integer getMatk() {
        return matk;
    }

    public void setMatk(Integer matk) {
        this.matk = matk;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
