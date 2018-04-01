package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "phanhois")
public class PhanHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer maph;
    @NotNull
    @Size(max = 200)
    private String mota;
    @NotNull
    @Size(max = 150)
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taikhoan_id", nullable = false,foreignKey = @ForeignKey(name = "PHANHOI_TAIKHOAN_FK"))
    private TaiKhoan taiKhoan;
    public PhanHoi(@NotNull @Size(max = 200) String mota, @NotNull @Size(max = 150) String date) {
        this.mota = mota;
        this.date = date;
    }

    public Integer getMaph() {
        return maph;
    }

    public void setMaph(Integer maph) {
        this.maph = maph;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
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

    public PhanHoi() {
    }


}
