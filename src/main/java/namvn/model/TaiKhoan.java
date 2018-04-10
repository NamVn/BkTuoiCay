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
    private Integer id;
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
    @NotNull
    @Size(max = 30)
    private String cmt;
    @NotNull
    @Size(max = 20)
    private String typeuser;
    private String anh;

    public TaiKhoan(@NotNull @Size(max = 30) String tentk, @NotNull @Size(max = 20) String matkhau, @NotNull @Size(max = 30) String gmail, @NotNull @Size(max = 20) String sdt, @NotNull @Size(max = 30) String cmt, @NotNull @Size(max = 20) String typeuser, String anh) {
        this.tentk = tentk;
        this.matkhau = matkhau;
        this.gmail = gmail;
        this.sdt = sdt;
        this.cmt = cmt;
        this.typeuser = typeuser;
        this.anh = anh;
    }

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

    @OneToMany (fetch = FetchType.LAZY,mappedBy = "taiKhoan")
    private Set<PhanHoi> phanHois;
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CongViec> congViecs = new HashSet<>();
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ThongBao> thongBaos = new HashSet<>();
    //    @OneToOne(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL,
//            mappedBy = "taiKhoan")
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CayPhien> cayPhiens = new HashSet<>();
    @OneToMany(mappedBy = "taiKhoan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VoiPhien> voiPhiens = new HashSet<>();

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Set<ThongBao> getThongBaos() {
        return thongBaos;
    }

    public void setThongBaos(Set<ThongBao> thongBaos) {
        this.thongBaos = thongBaos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CongViec> getCongViecs() {
        return congViecs;
    }

    public void setCongViecs(Set<CongViec> congViecs) {
        this.congViecs = congViecs;
    }

    public Set<CayPhien> getCayPhiens() {
        return cayPhiens;
    }

    public void setCayPhiens(Set<CayPhien> cayPhiens) {
        this.cayPhiens = cayPhiens;
    }

    public Set<VoiPhien> getVoiPhiens() {
        return voiPhiens;
    }

    public void setVoiPhiens(Set<VoiPhien> voiPhiens) {
        this.voiPhiens = voiPhiens;
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
