package namvn.repository;

import namvn.model.CongViec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CongViecDao extends JpaRepository<CongViec,Long>{
    /*
    Admin giao viec cho nhan vien
     */
    @Query(value = "select * from congviecs c where c.date like  %?1%",nativeQuery = true)
    List<CongViec> findAllByDateContaining(String date);
    @Query(value = "select * from congviecs c where c.date like  %?1% and c.taikhoan_id=?2",nativeQuery = true)
    CongViec findByDateContainingAndTaiKhoan(String date ,Integer taikhoan_id);
}
