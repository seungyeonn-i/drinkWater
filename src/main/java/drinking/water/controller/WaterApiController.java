package drinking.water.controller;

import drinking.water.service.WaterService;
import drinking.water.domain.waterweb.WaterForm;
import drinking.water.domain.waterweb.WaterReq;
import drinking.water.domain.waterweb.WaterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WaterApiController {

    private final WaterService waterService;

    @GetMapping("/water/apiJoin")
    public String joinForm(@ModelAttribute WaterForm waterForm) {

        return "water/joinForm";
    }

    @ResponseBody
    @PostMapping("/water/apiJoin")
    public ResponseEntity<WaterRes> join(@RequestBody WaterForm waterForm) {
        WaterRes join = waterService.join(waterForm);
        return new ResponseEntity<>(join,HttpStatus.OK);

    }

    @GetMapping("/water/drink")
    public String drinkForm(@ModelAttribute WaterReq waterReq) {
        return "/water/drink";
    }

    @ResponseBody
    @PostMapping("/water/apiDrink")
    public ResponseEntity<WaterRes> drink(@RequestBody WaterReq waterReq) {

        WaterRes update = waterService.update(waterReq);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
