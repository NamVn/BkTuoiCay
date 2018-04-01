package namvn.controller;

import namvn.model.CayPhien;
import namvn.repository.CayPhienDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static namvn.util.Utils.PHIEN_ERROR_INSERT_DATA;
import static namvn.util.Utils.PHIEN_INSERT_DATA;

@Controller
@RequestMapping(path = "/phien")
public class PhienController {
    @Autowired
    private CayPhienDao mCayPhienDao;
    //---------Chen phien tuoi cay cua tung lao cong
    @PostMapping("/nhap")
    public String insertData(@RequestBody CayPhien phien){
        CayPhien tempPhien= mCayPhienDao.save(phien);
        if(tempPhien!=null) return PHIEN_INSERT_DATA;
        else return PHIEN_ERROR_INSERT_DATA;
    }
    //--------(ADMIN) Tinh so cay ma lao cong da tuoi trong 1 khoang thoi gian
    @GetMapping(path = "/congviec")
    public int countCayByUser(@PathVariable Integer id,@PathVariable String date){
        List<CayPhien> phienList= mCayPhienDao.findAllByTaiKhoanAndDate(id,date);
        return phienList.size();
    }
}
