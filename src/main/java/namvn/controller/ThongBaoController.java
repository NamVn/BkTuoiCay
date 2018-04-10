package namvn.controller;

import namvn.model.TaiKhoan;
import namvn.model.ThongBao;
import namvn.repository.TaiKhoanDao;
import namvn.repository.ThongBaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static namvn.util.Utils.*;

@Controller
@RequestMapping(path = "/thongbao")
public class ThongBaoController {
    @Autowired
    private ThongBaoDao mThongBaoDao;
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;

    /*
    Lao cong xem co bn thong
     */
    @GetMapping(path = "/get")
    public @ResponseBody
    List<ThongBao> getThongBaos(@RequestHeader(value = "au-token") String token) {
        TaiKhoan taiKhoan = mTaiKhoanDao.findByToken(token);
        if (taiKhoan != null) {
            List<ThongBao> thongBaos = mThongBaoDao.findAllByTaiKhoan(taiKhoan.getId());
            for (int i = 0; i < thongBaos.size(); i++) {
                ThongBao thongBao = thongBaos.get(i);
                thongBao.setTaiKhoan(null);
                thongBao.setTrangthai(null);
            }
            return thongBaos;
        }
        return null;
    }
    /*
    Lao cong cap nhat da xem thong bao
     */
    @PostMapping(path = "/update")
    public @ResponseBody
    String updateThongBao(@RequestBody ThongBao thongBao) {
        ThongBao thongBao1=mThongBaoDao.findById(thongBao.getId());
        if(thongBao1!=null) {
            thongBao1.setTrangthai(SEE);
            mThongBaoDao.save(thongBao1);
            return NEW_FEED_SUCESS;
        }
        return NEW_FEED_NO_SUCESS;

    }
}
