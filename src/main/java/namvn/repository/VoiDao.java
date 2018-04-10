package namvn.repository;

import namvn.model.Cay;
import namvn.model.Voi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoiDao extends JpaRepository<Voi,Long> {
    @Query(value = "select * from vois v where v.ten=?1 and v.toado=?2",nativeQuery = true)
    Voi findAllByTenAndToado(String ten,String toado);
}
