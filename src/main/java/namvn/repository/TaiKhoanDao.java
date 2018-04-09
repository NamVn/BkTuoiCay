package namvn.repository;

import namvn.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaiKhoanDao extends JpaRepository<TaiKhoan, Long> {
    /*
    Kiem tra dang nhap OK
     */
    @Query(value = "select * from taikhoans c where c.tentk = ?1 and c.matkhau = ?2", nativeQuery = true)
    public TaiKhoan findByTentkAndMatkhau(String tentk, String matkhau);
    /*
    Kiem tra dang nhap OK
     */
    @Query(value = "select * from taikhoans c where c.tentk = ?1", nativeQuery = true)
    public TaiKhoan findByTentk(String tentk);

    //CrudRepository
    /*
    Xoa tai khoan theo ten tai khoan va so dien thoai
     */
    @Query(value = "select * from taikhoans c where c.tentk = ?1 and c.sdt = ?2", nativeQuery = true)
    public TaiKhoan findByTentkAndSdt(String tentk, String sdt);

    @Query(value = "select * from taikhoans c where c.token = ?1", nativeQuery = true)
    public TaiKhoan findByToken(String token);

}
