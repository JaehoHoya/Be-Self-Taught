package com.sku.fitizen.controller.test;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("admin")
public class AdminController
{

    @GetMapping("")
    public  String admin()
    {




        return "관리자 전용 페이지";
    }
}
