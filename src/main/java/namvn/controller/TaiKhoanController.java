package namvn.controller;


import namvn.model.Admin;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import namvn.repository.TaiKhoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

import static namvn.util.Utils.*;

@Controller
@RequestMapping(path = "/account")
public class TaiKhoanController {
    @Autowired
    private TaiKhoanDao mTaiKhoanDao;
    @Autowired
    private AdminDao mAdminDao;

    @PostMapping(path = "/login", produces = "application/json")
    public @ResponseBody
    String login(@RequestBody TaiKhoan taiKhoan) {
        TaiKhoan tempTaiKhoan = mTaiKhoanDao.findByTentkAndMatkhau(taiKhoan.getTentk(), taiKhoan.getMatkhau());
        if (tempTaiKhoan != null) return tempTaiKhoan.getToken();
        else return AC_NO_SUCESS;
    }

    /*
    Admin dang ki tai khoan cho lao cong
     */
    @PostMapping(path = "/register")
    public @ResponseBody
    String registerUser(@RequestHeader String token, @RequestBody TaiKhoan user) {
        Admin admin = mAdminDao.findByToken(token);
        if (admin != null) {
            TaiKhoan taiKhoan = mTaiKhoanDao.save(new TaiKhoan(user.getTentk(), user.getMatkhau(), user.getGmail(), user.getSdt(), ""));
            if (taiKhoan != null) return AC_REGISTER_SUCESS;
            else return AC_REGISTER_NO_SUCESS;
        } else return AC_REGISTER_NO_SUCESS;
    }

    //-----ADMIN xoa tai khoan lao cong
    @PostMapping(path = "/delete", produces = "application/json")
    public @ResponseBody
    String deleteUser(@RequestHeader String token, @RequestBody TaiKhoan user) {
        Admin admin = mAdminDao.findByToken(token);
        if (admin != null) {
            TaiKhoan tk = mTaiKhoanDao.findByTentkAndSdtAndGmail(user.getTentk(), user.getSdt(), user.getGmail());
            if (tk != null) {
                mTaiKhoanDao.delete(tk);
                return AC_DELETE_SUCESS;
            } else return AC_DELETE_NO_SUCESS;
        } else return AC_DELETE_NO_SUCESS;
    }


}


