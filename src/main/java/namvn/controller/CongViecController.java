package namvn.controller;

import namvn.model.CongViec;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import namvn.repository.CongViecDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static namvn.util.Utils.CONGVIEC_ERROR_GIAOVIEC;
import static namvn.util.Utils.CONGVIEC_GIAOVIEC_SUCESS;

@Controller
@RequestMapping(path = "/congviec")
public class CongViecController {
//    @Autowired
//    private AdminDao mAdminDao;
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private CongViecDao mCongViecDao;

    /*
    Admin phan cong cong viec cho user
     */
    @PostMapping(path = "/phancong")
    public @ResponseBody String sendCongviec(@RequestParam String tentk,@RequestBody CongViec congViec) {
       // if (mAdminDao.findByToken(token) != null) {
            TaiKhoan taiKhoan=mTaiKhoanDao.findByTentk(tentk);
            if(taiKhoan!=null) {
                congViec.setTaiKhoan(taiKhoan);
                mCongViecDao.save(congViec);
                return CONGVIEC_GIAOVIEC_SUCESS;
            }
        else return CONGVIEC_ERROR_GIAOVIEC;
    }

    /*
    Admin xem danh sach cac cong viec da giao trong ngay
     */
    @GetMapping(path = "/list")
    public @ResponseBody List<CongViec> getListCongViec( @RequestParam String date) {
       // if (mAdminDao.findByToken(token) != null) {
            List<CongViec> congViecs = mCongViecDao.findAllByDateContaining(date);
            if (congViecs.size() > 0) {
                for (int i = 0; i < congViecs.size(); i++) {
                    congViecs.get(i).setTaiKhoan(null);
                }
                return congViecs;
            }
            else return null;
       // } else return null;
    }
    /*
    Lao cong xem cong viec dc giao
     */
    @GetMapping(path = "/see")
    public @ResponseBody String selectCongViec(@RequestHeader(value = "au-token")String token, @RequestParam String date){
        TaiKhoan taiKhoan=mTaiKhoanDao.findByToken(token);
        if(taiKhoan!=null){
            CongViec congViec=mCongViecDao.findByDateContainingAndTaiKhoan(date,taiKhoan.getId());
            if(congViec!=null) return congViec.getMota();
            else return null;
        }
        else return null;
    }

}
