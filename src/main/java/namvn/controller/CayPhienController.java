package namvn.controller;

import namvn.model.Cay;
import namvn.model.CayPhien;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import namvn.repository.CayDao;
import namvn.repository.CayPhienDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static namvn.util.Utils.PHIEN_ERROR_INSERT_DATA;
import static namvn.util.Utils.PHIEN_INSERT_DATA;

@Controller
@RequestMapping(path = "/cayphien")
public class CayPhienController {
    @Autowired
    private CayPhienDao mCayPhienDao;
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private CayDao mCayDao;

    //---------Chen phien tuoi cay cua tung lao cong
    @PostMapping("/nhap")
    public @ResponseBody
    String insertData(@RequestHeader(value = "au-token") String token, @RequestParam String toado, @RequestBody CayPhien phien) {
        phien.setTaiKhoan(mTaiKhoanDao.findByToken(token));
        phien.setCay(mCayDao.findCayByToaDo(toado));
        CayPhien tempPhien = mCayPhienDao.save(phien);
        if (tempPhien != null) return PHIEN_INSERT_DATA;
        else return PHIEN_ERROR_INSERT_DATA;
    }

    /*
    (ADMIN) Tinh so cay ma lao cong da tuoi trong 1 khoang thoi gian
    CHAM CONG
     */
    @GetMapping(path = "/chamcong")
    public @ResponseBody
    int countCayByUser(@RequestParam String tentk, @RequestParam String date) {
        //if (mAdminDao.findByToken(token) != null) {
        TaiKhoan taiKhoan = mTaiKhoanDao.findByTentk(tentk);
        if (taiKhoan != null) {
            return (int) mCayPhienDao.findAllByTaiKhoanAndDate(taiKhoan.getId(), date);
//                List<CayPhien> phienList = mCayPhienDao.findAllByTaiKhoanAndDate(taiKhoan.getId(), date);
//                if (phienList.size() > 0) return phienList.size();
//                else return 0;
        } else return 0;
        // }
        // else return 0;
    }
    /*
    Admin kiem tra cay chet do ung nuoc
     */
//    @PostMapping(path = "/ungnuoc")
//    public List<Cay> getListCay(@RequestHeader String token){
//        if (mAdminDao.findByToken(token) != null) {
//            List<Cay> cays=mCayDao.findAll();
//            for (int i=0;i<cays.size();i++){
//                mCayPhienDao.findAllByDateAndCay(cays.get(i).getId();
//            }
//
//        }
//        else
//    }
}
