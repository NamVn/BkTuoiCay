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

import static namvn.util.Utils.*;

@Controller
@RequestMapping("/voi")
public class VoiController {
    //    @Autowired
//    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private VoiDao mVoiDao;

    /*
   Lay danh sach tat ca cac voi
    */
    @GetMapping(path = "/all")
    public @ResponseBody
    List<Voi> findAllVoi() {
        List<Voi> vois = mVoiDao.findAll();
        if (vois.size() > 0) {
            for (int i = 0; i < vois.size(); i++) {
                vois.get(i).setVoiPhiens(null);
            }
            return vois;
        }
        else return null;

    }

    /*
    Admin them voi nuoc moi vao
     */
    @PostMapping(path = "/makes")
    public @ResponseBody
    String makeNewVois(@RequestBody  List<Voi> vois) {
        if (vois.size() > 0) {
            for (int i = 0; i < vois.size(); i++) {
                mVoiDao.save(vois.get(i));
            }
            return VOI_MAKE_NEW_SUCESS;
        }
        else return VOI_MAKE_NEW_NO_SUCESS;

    }
    /*
    Admin xoa voi nuoc
     */
    @PostMapping(path = "/delete")
    public @ResponseBody String deleteVois(@RequestBody List<Voi> vois) {
        if (vois.size() > 0) {

            for (int i = 0; i < vois.size(); i++) {
                Voi voi=vois.get(i);
//                List<VoimVoiDao.findAllByTenAndToado(voi.getTen(),voi.getToado());
                mVoiDao.delete(voi);
            }
            return VOI_DELETE_SUCESS;
        } else return VOI_DELETE_NO_SUCESS;
    }
     /*
   Lay danh sach tat ca cac cay
    */
    //@PostMapping(path = "/create")

}
