package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "voiphiens")
public class VoiPhien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Size(max = 100)
    private String date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voi_id", nullable = false,foreignKey = @ForeignKey(name = "VOIPHIEN_VOI_FK"))
    private Voi voi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taikhoan_id", nullable = false,foreignKey = @ForeignKey(name = "VOIPHIEN_TAIKHOAN_FK"))
    private TaiKhoan taiKhoan;
}
