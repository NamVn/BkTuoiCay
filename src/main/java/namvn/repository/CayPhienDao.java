package namvn.repository;

import namvn.model.CayPhien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CayPhienDao extends JpaRepository<CayPhien, Long> {

    //    @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
//            countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
//            nativeQuery = true)
    @Query(value = "SELECT count(p.cay_id) FROM cayphiens p WHERE date = ?1 GROUP BY p.taikhoan_id", nativeQuery = true)
    long findAllByTaiKhoanAndDate(Integer id, String date);

    @Query(value = "SELECT sum(p.dapung) FROM cayphiens p WHERE date = ?1 GROUP BY p.cay_id", nativeQuery = true)
    long findAllByDateAndCay(String date, Integer cay_id);
}
