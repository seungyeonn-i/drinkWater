//package drinking.water.controller;
//
//import drinking.water.domain.Water;
//import drinking.water.service.WaterService;
//import drinking.web.WaterForm;
//import drinking.web.WaterReq;
//import drinking.web.WaterRes;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class WaterController {
//
//    private final WaterService waterService;
//
////    @GetMapping("/water/join")
//    public String joinForm(@ModelAttribute WaterForm waterForm) {
//
//        return "/water/joinForm";
//    }
//
////    @PostMapping("/water/join")
//    public String join(WaterForm waterForm, HttpServletRequest request) {
//        log.info(String.valueOf(waterForm));
//
//        waterService.join(waterForm);
//        return "/main";
//    }
//
////    @GetMapping("/water/drink")
//    public String drinkForm(@ModelAttribute WaterReq waterReq) {
//        return "/water/drink";
//    }
//
////    @PostMapping("/water/drink")
//    public String drink(@ModelAttribute WaterReq waterReq, HttpServletRequest request) {
//        log.info(String.valueOf(waterReq));
//        WaterRes update = waterService.update(waterReq);
//        if (update != null) {
//            log.info(update.toString());
//            return "/main";
//        }else{
//            return "/water/done";
//        }
//    }
//}
