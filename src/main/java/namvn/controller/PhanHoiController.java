package namvn.controller;

import namvn.model.PhanHoi;
import namvn.model.TaiKhoan;
import namvn.repository.PhanHoiDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static namvn.util.Utils.PHANHOI_ERROR_SEND_SUCESS;
import static namvn.util.Utils.PHANHOI_SEND_SUCESS;

@Controller
@RequestMapping(path = "/phanhoi")
public class PhanHoiController {
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private PhanHoiDao mPhanHoiDao;

    //---------Gui phan hoi
    @PostMapping(path = "/send",produces = "application/json")
    public @ResponseBody
    String sendPhanHoi(@RequestHeader String token,@RequestBody PhanHoi phanHoi) {
        TaiKhoan taiKhoan=mTaiKhoanDao.findByToken(token);
        if(taiKhoan!=null) {
            phanHoi.setTaiKhoan(taiKhoan);
            PhanHoi temPhanHoi = mPhanHoiDao.save(phanHoi);
            if (temPhanHoi != null) return PHANHOI_SEND_SUCESS;
            else return PHANHOI_ERROR_SEND_SUCESS;
        }
        else return PHANHOI_ERROR_SEND_SUCESS;
    }

    //--------ADMIN Xem phan hoi
    @GetMapping(path = "/see")
    public @ResponseBody List<PhanHoi> getListPhanHoi(@RequestParam String datetime) {
        List<PhanHoi> phanHoiList = mPhanHoiDao.findAllByDateContaining(datetime);
        if (phanHoiList.size() > 0)
            return phanHoiList;
        else return null;
    }
}
