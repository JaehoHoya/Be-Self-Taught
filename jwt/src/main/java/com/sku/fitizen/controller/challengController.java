package com.sku.fitizen.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("challenge")
public class challengController {

    @GetMapping("")
    public String challengeForm()
    {
        return "challenge/challenge";
    }

    @GetMapping("add")
    public String addForm(Model model)
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("name", name);

        return "challenge/challengeAdd";

    }

    @GetMapping("list")
    public  String listForm()
    {

        return "challenge/challengeList";
    }

    @GetMapping("mychall")
    public String  mychall(Model model)
    {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("name", name);
        return "challenge/myChallenge";
    }


}
