package namvn.repository;

import namvn.model.Admin;
import namvn.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminDao extends JpaRepository<Admin,Long>{
    @Query(value = "select * from admins c where c.token = ?1", nativeQuery = true)
    public Admin findByToken(String token);
    /*
   Kiem tra dang nhap OK
    */
    @Query(value = "select * from admins c where c.tentk = ?1 and c.matkhau = ?2", nativeQuery = true)
    public Admin findByTentkAndMatkhau(String tentk, String matkhau);

}
