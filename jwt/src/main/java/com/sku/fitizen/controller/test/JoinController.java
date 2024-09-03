package com.sku.fitizen.controller.test;


import com.sku.fitizen.dto.JoinDTO;
import com.sku.fitizen.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ResponseBody
public class JoinController {

    @Autowired
    JoinService joinService;

    @PostMapping("/join")
    public String join(JoinDTO dto)
    {
        joinService.join(dto);
        return "success";
    }

}
