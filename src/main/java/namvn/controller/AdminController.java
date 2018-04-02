package namvn.controller;

import namvn.model.Admin;
import namvn.model.TaiKhoan;
import namvn.repository.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static namvn.util.Utils.AC_NO_SUCESS;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
    @Autowired
    private AdminDao mAdminDao;
    @PostMapping(path = "/login", produces = "application/json")
    public @ResponseBody
    String login(@RequestBody Admin taiKhoan) {
        Admin admin = mAdminDao.findByTentkAndMatkhau(taiKhoan.getTentk(), taiKhoan.getMatkhau());
        if (admin != null) return admin.getToken();
        else return AC_NO_SUCESS;
    }

}
