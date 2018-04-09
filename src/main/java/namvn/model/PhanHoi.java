package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "phanhois")
public class PhanHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(max = 200)
    private String mota;
    @NotNull
    @Size(max = 150)
    private String date;
    @NotNull
    private String trangthai;

    @JoinColumn(name = "taikhoan_id", nullable = false,foreignKey = @ForeignKey(name = "PHANHOI_TAIKHOAN_FK"))
    @ManyToOne(fetch = FetchType.LAZY)
    private TaiKhoan taiKhoan;
    public PhanHoi(@NotNull @Size(max = 200) String mota, @NotNull @Size(max = 150) String date) {
        this.mota = mota;
        this.date = date;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
