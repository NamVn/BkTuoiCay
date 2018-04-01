package namvn.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vois")
public class Voi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mavoi;
    @NotNull
    @Size(max = 30)
    private String ten;
    @NotNull
    @Size(max = 40)
    @Column(unique = true)
    private String toado;
    @OneToMany(mappedBy = "voi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VoiPhien> voiPhiens = new HashSet<>();
    public Integer getMavoi() {
        return mavoi;
    }

    public void setMavoi(Integer mavoi) {
        this.mavoi = mavoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getToado() {
        return toado;
    }

    public void setToado(String toado) {
        this.toado = toado;
    }
}