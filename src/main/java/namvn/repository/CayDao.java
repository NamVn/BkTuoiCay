package namvn.repository;

import namvn.model.Cay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CayDao extends JpaRepository<Cay, Long> {
    //--------Tim cac cay theo khu vuc va truong hoc
    @Query(value = "select * from cays c where c.khuvuc = ?1 and c.truong=?2 ",nativeQuery = true)
    List<Cay> findAllByKhuVucAndTruong(String khuvuc, String truong);

    //--Tim cac cay theo trang thai da tuoi
    @Query(value = "select * from cays c where c.trangthai = ?1",nativeQuery = true)
    List<Cay> findCayByTrangThai(String trangthai);

    //CrudRepository
    //--------Tim cac cay theo trang thaithieu nuoc
    @Query(value = "select * from cays c where c.trangthai like %?1%",nativeQuery = true)
    List<Cay> findCayByTrangThaiStartingWith(String trangthai);

    /*Tim kiem nhu cau uong nuoc cua tung cay
     */
    @Query(value = "select * from cays c where c.toaDo = ?1",nativeQuery = true)
    Cay findCayByToaDo(String toado);

    /*
    Cap nhat trang thai cua cay
     */

    @Query(value = "select * from cays c where c.toaDo = ?1  and u.khuvuc=?2 and u.truong=?3",nativeQuery = true)
    List<Cay> findAllByToadoAndKhuvucAndTruong( String toado, String khuvuc, String truong);
}
