package namvn.controller;

import namvn.model.CayPhien;
import namvn.model.VoiPhien;
import namvn.repository.TaiKhoanDao;
import namvn.repository.VoiDao;
import namvn.repository.VoiPhienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static namvn.util.Utils.PHIEN_ERROR_INSERT_DATA;
import static namvn.util.Utils.PHIEN_INSERT_DATA;

@Controller
@RequestMapping(path = "/voiphien")
public class VoiPhienController {
    @Autowired
    private VoiDao mVoiDao;
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private VoiPhienDao mVoiPhienDao;
    //---------Chen phien tuoi cay cua tung lao cong
    @PostMapping("/nhap")
    public @ResponseBody
    String insertData(@RequestHeader(value = "au-token") String token,@RequestParam String ten, @RequestParam String toado, @RequestBody VoiPhien phien) {
        phien.setTaiKhoan(mTaiKhoanDao.findByToken(token));
        phien.setVoi(mVoiDao.findAllByTenAndToado(ten,toado));
        VoiPhien tempPhien = mVoiPhienDao.save(phien);
        if (tempPhien != null) return PHIEN_INSERT_DATA;
        else return PHIEN_ERROR_INSERT_DATA;
    }
}
