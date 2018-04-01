package namvn.repository;

import namvn.model.PhanHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhanHoiDao extends JpaRepository<PhanHoi,Long>{
    /*
    Tim kiem cac phan hoi trong ngay
     */
    @Query(value = "select * from phanhois c where c.date like  %?1%",nativeQuery = true)
    public List<PhanHoi> findAllByDateContaining(String date);
}
