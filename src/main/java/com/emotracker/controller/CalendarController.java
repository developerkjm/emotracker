package com.emotracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String calendar(Principal principal, Model model) {
        model.addAttribute("activePage", "calendar"); // 메뉴 강조용
        return "calendar"; // templates/calendar.html
    }
}
