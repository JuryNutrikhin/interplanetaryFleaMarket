package com.example.interplanetaryfleamarket.controller.testController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("test")
public class TestController {

    @GetMapping("")
    public String test(){
        return "test/test";
    }

//    @GetMapping("")
//    public String test(@RequestParam String string ){
//
//       return string;
//    }
}
