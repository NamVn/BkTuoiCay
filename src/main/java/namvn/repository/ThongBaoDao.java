package namvn.repository;

import namvn.model.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThongBaoDao extends JpaRepository<ThongBao,Long> {
    @Query(value = "select * from thongbaos t where  t.taikhoan_id=?1",nativeQuery = true)
    List<ThongBao> findAllByTaiKhoan(Integer taikhoan_id);
    @Query(value = "select * from thongbaos t where t.id=?1",nativeQuery = true)
    ThongBao findById(Integer id);
}
