package namvn.controller;

import namvn.model.Admin;
import namvn.model.PhanHoi;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import namvn.repository.PhanHoiDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static namvn.util.Utils.*;

@Controller
@RequestMapping(path = "/phanhoi")
public class PhanHoiController {

    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private PhanHoiDao mPhanHoiDao;

    /*
    USER gui phan hoi
     */
    @PostMapping(path = "/send", produces = "application/json")
    public @ResponseBody
    String sendPhanHoi(@RequestHeader(value = "au-token") String token, @RequestBody PhanHoi phanHoi) {
        TaiKhoan taiKhoan = mTaiKhoanDao.findByToken(token);
        if (taiKhoan != null) {
            phanHoi.setTaiKhoan(taiKhoan);
            PhanHoi temPhanHoi = mPhanHoiDao.save(phanHoi);
            if (temPhanHoi != null) return PHANHOI_SEND_SUCESS;
            else return PHANHOI_ERROR_SEND_SUCESS;
        } else return PHANHOI_ERROR_SEND_SUCESS;
    }

    /*
    ADMIN cap nhat trang thai da xem tin phan hoi
     */
    @GetMapping(path = "/trangthai", produces = "application/json")
    public @ResponseBody
    String setTrangThai(@RequestParam String tentk) {
        //Kiem tra co phai admin khong
        //  Admin admin = mAdminDao.findByToken(token);
        // if (admin != null) {
        TaiKhoan user = mTaiKhoanDao.findByTentk(tentk);
        if (user != null) {
            List<PhanHoi> phanHois = mPhanHoiDao.findAllByByTrangthaiTaiKhoan("Khong", user.getId());
            if (phanHois.size() > 0) {
                for (int i = 0; i < phanHois.size(); i++) {
                    PhanHoi phanHoi = phanHois.get(i);
                    phanHoi.setTrangthai("Co");
                    mPhanHoiDao.save(phanHoi);
                }
                return PHANHOI_TRANGTHAI_SUCESS;
            } else return PHANHOI_TRANGTHAI_NO_SUCESS;
    }
        return PHANHOI_TRANGTHAI_NO_SUCESS;
    }

    /*
    ADMIN xem tung phan hoi
     */
    @GetMapping(path = "/detail")
    public @ResponseBody
    List<PhanHoi> getEachPhanHoi(@RequestParam String tentk) {
        // Admin admin = mAdminDao.findByToken(token);
        // if (admin != null) {
        TaiKhoan user = mTaiKhoanDao.findByTentk(tentk);
        if (user != null) {
            List<PhanHoi> list = mPhanHoiDao.findAllByTaiKhoan(user.getId());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setTaiKhoan(null);
            }
            return list;
        } else return null;
        //} else return null;
    }

    //------Xem phan hoi theo ngay
    @GetMapping(path = "/day")
    public @ResponseBody
    List<String> getListDay(@RequestParam String day) {
        return getListPhanHoi(day);
    }

    //--------ADMIN Xem phan hoi
    @GetMapping(path = "/week")
    public @ResponseBody
    List<String> getListWeek(@RequestParam String week) {
        return getListPhanHoi(week);
    }

    @GetMapping(path = "/month")
    public @ResponseBody
    List<String> getListMonth(@RequestParam String month) {
        return getListPhanHoi(month);
    }

    private List<String> getListPhanHoi(String datetime) {
        List<String> nameList = new ArrayList<>();
        List<PhanHoi> phanHoiList = mPhanHoiDao.findAllByDateContaining(datetime);
        if (phanHoiList.size() > 0) {
            for (int i = 0; i < phanHoiList.size(); i++) {
                nameList.add(phanHoiList.get(i).getTaiKhoan().getTentk());
            }
            return nameList;
        } else return null;
    }

}