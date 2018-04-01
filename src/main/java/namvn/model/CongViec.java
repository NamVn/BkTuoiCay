package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "congviecs")
public class CongViec {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer macv;
    @NotNull
    @Size(max = 30)
    @Column(unique = true)
    private String tencv;
    @NotNull
    @Size(max = 1000)
    private String mota;
    @NotNull
    @Size(max = 50)
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taikhoan_id", nullable = false,foreignKey = @ForeignKey(name = "CONGVIEC_TAIKHOAN_FK"))
    private TaiKhoan taiKhoan;
}
