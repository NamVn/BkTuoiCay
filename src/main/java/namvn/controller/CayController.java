package namvn.controller;

import namvn.model.Cay;
import namvn.repository.CayDao;
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
        List<Cay> cayList = mCayDao.findAllByKhuVucAndTruong(khuvuc, truong);
        if (cayList.size()> 0) return cayList;
        else return null;
    }

    //-------Khi nguoi dung bam vao cay thieu nuoc thi no se Tra lai nhu cau nuoc cua moi cay
    @PostMapping(path = "/nhucau",produces = "application/json")
    public @ResponseBody
    String getNhucau(@RequestBody Cay cay) {
        Cay tempCay = mCayDao.findCayByToaDo(cay.getToado());
        if (tempCay != null) return tempCay.getNhucau();
        else return CAY_ERROR_TOADO;
    }
    @PostMapping(path = "/duongdi")
    public @ResponseBody
    String getDuongdi(@RequestParam String toado) {
        List<Cay> cayList = mCayDao.findCayByTrangThaiStartingWith("thieu ");
        if (cayList.size() >0){
            //Thuat toan de tinh toan duong di o day no phu thuoc vao toa do cua nguoi lao con
            return "Duong di ";
        }
        else return CAY_ERROR_TOADO;

    }
    //--------Tra lai danh sach cac cay da tuoi nuoc roi OK
    @GetMapping(path = "/datuoi")
    public @ResponseBody
    List<Cay> getCayDaTuoi() {
        List<Cay> cayList = mCayDao.findCayByTrangThai("du nuoc");
        if (cayList.size()> 0) return cayList;
        else return null;
    }

    //-------Tra lai danh sach cac cay dang thieu nuoc OK
    @GetMapping(path = "/thieunuoc")
    public @ResponseBody
    List<Cay> getCayChuaduNuoc() {
        List<Cay> cayList = mCayDao.findCayByTrangThaiStartingWith("thieu ");
        if (cayList != null) return cayList;
        else return null;
    }

    //---------Cap nhat trang thai cay con thieu bao nhieu nuoc OK
    @PutMapping(path = "/trangthai",produces = "application/json")
    public String updateTrangThai(@RequestBody Cay cay) {
        int tempCay = mCayDao.setFixedTrangThaiFor(cay.getTrangthai(), cay.getToado(), cay.getKhuvuc(), cay.getTruong());
        if (tempCay != 0) return CAY_UPDATE_SUCESS;
        else return CAY_UPDATE_NO_SUCESS;

    }
}
