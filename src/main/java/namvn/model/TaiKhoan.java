package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "taikhoans")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matk;
    @NotNull
    @Size(max = 30)
    @Column(unique = true)
    private String tentk;
    @NotNull
    @Size(max = 20)
    private String matkhau;
    @NotNull
    @Size(max = 30)
    private String gmail;
    @NotNull
    @Size(max = 20)
    private String sdt;
    @NotNull
    @Size(max = 30)
    private String token;

    public TaiKhoan(@NotNull @Size(max = 30) String tentk1, @NotNull @Size(max = 20) String matKhau1) {
        tentk = tentk1;
        matkhau = matKhau1;
    }

    public TaiKhoan(@NotNull @Size(max = 30) String tentk1, @NotNull @Size(max = 30) String gm, @NotNull @Size(max = 20) String sdt1) {
        tentk = tentk1;
        gmail = gm;
        sdt = sdt1;
    }

    public TaiKhoan() {
    }


    public TaiKhoan(@NotNull @Size(max = 30) String tentk, @NotNull @Size(max = 20) String matkhau, @NotNull @Size(max = 30) String gmail, @NotNull @Size(max = 20) String sdt, @NotNull @Size(max = 30) String token) {
        this.tentk = tentk;
        this.matkhau = matkhau;
        this.gmail = gmail;
        this.sdt = sdt;
        this.token = token;
    }

    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhanHoi> phanHois = new HashSet<>();
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CongViec> congViecs = new HashSet<>();
    //    @OneToOne(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "taiKhoan")
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CayPhien> cayPhiens = new HashSet<>();
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VoiPhien> voiPhiens = new HashSet<>();

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<PhanHoi> getPhanHois() {
        return phanHois;
    }

    public void setPhanHois(Set<PhanHoi> phanHois) {
        this.phanHois = phanHois;
    }


}