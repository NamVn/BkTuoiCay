package namvn.controller;


import namvn.model.TaiKhoan;
import namvn.model.Voi;
import namvn.repository.AdminDao;
import namvn.repository.TaiKhoanDao;
import namvn.repository.VoiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/voi")
public class VoiController {
//    @Autowired
//    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private VoiDao mVoiDao;

    /*
   Lay danh sach tat ca cac cay
    */
    @GetMapping(path = "/all")
    public @ResponseBody
    List<Voi> findAllVoi() {
       // TaiKhoan taiKhoan = mTaiKhoanDao.findByToken(token);
        //if (taiKhoan != null) {
            List<Voi> vois = mVoiDao.findAll();
            if (vois.size() > 0) return vois;
            else return null;
        //} else return null;
    }
}
