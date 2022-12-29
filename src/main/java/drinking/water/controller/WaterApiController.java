package drinking.water.controller;

import drinking.water.domain.Water;
import drinking.water.service.WaterService;
import drinking.web.WaterForm;
import drinking.web.WaterReq;
import drinking.web.WaterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public WaterRes join(@RequestBody WaterForm waterForm) {
        return waterService.join(waterForm);

    }

    @GetMapping("/water/drink")
    public String drinkForm(@ModelAttribute WaterReq waterReq) {
        return "/water/drink";
    }

    @ResponseBody
    @PostMapping("/water/apiDrink")
    public WaterRes drink(@RequestBody WaterReq waterReq) {
        return  waterService.update(waterReq);
    }
}
