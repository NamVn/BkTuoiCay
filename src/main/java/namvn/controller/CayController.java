package namvn.controller;

import namvn.model.Cay;
import namvn.model.TaiKhoan;
import namvn.repository.CayDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static namvn.util.Utils.CAY_ERROR_TOADO;
import static namvn.util.Utils.CAY_UPDATE_NO_SUCESS;
import static namvn.util.Utils.CAY_UPDATE_SUCESS;

@Controller
@RequestMapping(path = "/cay")
public class CayController {
    @Autowired
    private CayDao mCayDao;

    //-------Tra lai danh sach cac cay de load len map OK
    @GetMapping(path = "/list")
    public @ResponseBody
    List<Cay> findAllCayByKhuVucAndTruong(@RequestParam String khuvuc, @RequestParam String truong) {
        //TaiKhoan taiKhoan=mTaiKhoanDao.findByToken(token);
        // if(taiKhoan!=null) {
        List<Cay> cayList = mCayDao.findAllByKhuVucAndTruong(khuvuc, truong);
        if (cayList.size() > 0) return cayList;
        else return null;
        //}
        //else return null;
    }

    /*
    Lay danh sach tat ca cac cay
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    List<Cay> findAllCay() {
        // TaiKhoan taiKhoan=mTaiKhoanDao.findByToken(token);
        //if(taiKhoan!=null) {
        List<Cay> cayList = mCayDao.findAll();
        return check(cayList);
        // }
        //else return null;
    }

    /*
    Lay thong tin luong nuoc can tuoi
     */
    @PostMapping(path = "/nhucau", produces = "application/json")
    public @ResponseBody
    String getNhucau(@RequestBody Cay cay) {
        //TaiKhoan taiKhoan=mTaiKhoanDao.findByToken(token);
        // if(taiKhoan!=null) {
        Cay tempCay = mCayDao.findCayByToaDo(cay.getToado());
        if (tempCay != null) return tempCay.getNhucau();
        else return CAY_ERROR_TOADO;
        //}
        // else return null;
    }

    /*
    Tra lai duong di ngan nhat
     */
    @PostMapping(path = "/duongdi")
    public @ResponseBody
    String getDuongdi(@RequestParam String toado) {
        List<Cay> cayList = mCayDao.findCayByTrangThaiStartingWith("thieu ");
        if (cayList.size() > 0) {
            //Thuat toan de tinh toan duong di o day no phu thuoc vao toa do cua nguoi lao con
            return "Duong di ";
        } else return CAY_ERROR_TOADO;

    }

    //--------Admin xem Tra lai danh sach cac cay da tuoi nuoc roi OK
    @GetMapping(path = "/datuoi")
    public @ResponseBody
    List<Cay> getCayDaTuoi() {
        List<Cay> cayList = mCayDao.findCayByTrangThai("du nuoc");
        return check(cayList);
    }

    private List<Cay> check(List<Cay> cayList) {
        if (cayList.size() > 0) {
            for (int i = 0; i < cayList.size(); i++) {
                cayList.get(i).setCayPhiens(null);
            }
            return cayList;
        } else return null;
    }

    //-------Admin Tra lai danh sach cac cay dang thieu nuoc OK
    @GetMapping(path = "/thieunuoc")
    public @ResponseBody
    List<Cay> getCayChuaduNuoc() {
        List<Cay> cayList = mCayDao.findCayByTrangThaiStartingWith("thieu ");
        return check(cayList);
    }

    //---------Cap nhat trang thai cay con thieu bao nhieu nuoc OK
    @PostMapping(path = "/trangthai", produces = "application/json")
    public @ResponseBody String updateTrangThai(@RequestBody Cay cay) {
      Cay cay1= mCayDao.findCayByToaDo(cay.getToado());
        if (cay1!=null) {

                cay1.setCayPhiens(null);
                cay1.setTrangthai(cay.getTrangthai());
                mCayDao.save(cay1);

            return "Cập nhật trạng thái thành công";
        } else return "Cập nhật trạng thái thành công\n Kiểm tra kết nối mạng";

    }
}
