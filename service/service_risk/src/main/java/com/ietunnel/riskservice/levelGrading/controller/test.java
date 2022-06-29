package com.ietunnel.riskservice.levelGrading.controller;

public class test {
//    package com.ietunnel.riskservice.levelGrading.controller;
//
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.ietunnel.commonutils.R;
//import com.ietunnel.riskservice.levelGrading.entity.DCriteria;
//import com.ietunnel.riskservice.levelGrading.service.DCriteriaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//    /**
//     * <p>
//     * 风险等级D划分 前端控制器
//     * </p>
//     *
//     * @author wxm
//     * @since 2022-04-21
//     */
//    @RestController
//    @RequestMapping("/riskservice/levelGrading/d-criteria")
//    @CrossOrigin
//    public class DCriteriaController {
//
//        @Autowired
//        private DCriteriaService dCriteriaService;
//
//        @PostMapping("/addDCriteria")
//        public R addDCriteria(@RequestBody DCriteria dCriteria ){
//            boolean save = dCriteriaService.save(dCriteria);
//            if (save){
//                return R.ok();
//            }else {
//                return R.error();
//            }
//        }
//
//        @PostMapping("/findAllDCriteria/{current}/{limit}")
//        public R findAllDCriteria(@PathVariable Long current, @PathVariable Long limit){
//            Page<DCriteria> page = new Page<>(current,limit);
//            dCriteriaService.page(page,null);
//            long total = page.getTotal();
//            List<DCriteria> records = page.getRecords();
//            return R.ok().data("total",total).data("info",records);
//        }
//
//        @GetMapping("/findDCriteriaById/{id}")
//        public R findDCriteriaById(@PathVariable String id){
//            DCriteria dCriteria = dCriteriaService.getById(id);
//            return R.ok().data("dCriteria",dCriteria);
//        }
//
//        @PostMapping("/updateDCriteria")
//        public R updateDCriteria(@RequestBody DCriteria dCriteria){
//            boolean update = dCriteriaService.updateById(dCriteria);
//            if (update){
//                return R.ok();
//            }else {
//                return R.error();
//            }
//        }
//
//        @DeleteMapping("/removeDCriteria/{id}")
//        public R removeDCriteria(@PathVariable String id){
//            boolean remove = dCriteriaService.removeById(id);
//            if (remove){
//                return R.ok();
//            }else {
//                return R.error();
//            }
//        }
//
//    }


}
