package namvn.repository;

import namvn.model.PhanHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhanHoiDao extends JpaRepository<PhanHoi,Long> {
    /*
    Tim kiem cac phan hoi trong ngay,thang,nam
     */
    @Query(value = "select * from phanhois c where c.date like  %?1%", nativeQuery = true)
    public List<PhanHoi> findAllByDateContaining(String date);
    /*
    Tim kiem phan hoi theo ten tk
     */
    @Query(value = "select * from phanhois c where c.taikhoan_id=?1", nativeQuery = true)
    public List<PhanHoi> findAllByTaiKhoan(Integer taikhoan_id);
    /*
    Cap nhat trang thai
     */
    @Modifying
    @Query(value =  "select * from phanhois c where c.trangthai=?1 AND c.taikhoan_id=?2",nativeQuery = true)
    List<PhanHoi> findAllByByTrangthaiTaiKhoan(String trangthai,Integer taikhoan_id);
}
