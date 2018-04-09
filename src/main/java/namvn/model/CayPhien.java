package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cayphiens")
public class CayPhien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(max = 30)
    private String dapung;
    @NotNull
    @Size(max = 100)
    private String date;
    //    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "taikhoan_id", nullable = false,foreignKey = @ForeignKey(name = "PHIEN_TAIKHOAN_FK"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taikhoan_id", nullable = false, foreignKey = @ForeignKey(name = "PHIEN_TAIKHOAN_FK"))
    private TaiKhoan taiKhoan;

    //    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cay_id", nullable = false, foreignKey = @ForeignKey(name = "PHIEN_CAY_FK"))
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cay_id", nullable = false, foreignKey = @ForeignKey(name = "PHIEN_CAY_FK"))
    private Cay cay;

    public CayPhien() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDapung() {
        return dapung;
    }

    public void setDapung(String dapung) {
        this.dapung = dapung;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Cay getCay() {
        return cay;
    }

    public void setCay(Cay cay) {
        this.cay = cay;
    }
}
